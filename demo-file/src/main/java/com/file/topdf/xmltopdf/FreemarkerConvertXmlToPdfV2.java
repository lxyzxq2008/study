package com.file.topdf.xmltopdf;

import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.io.resource.UrlResource;
import cn.hutool.http.HtmlUtil;
import com.aspose.words.Document;
import com.aspose.words.License;
import com.aspose.words.SaveFormat;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.*;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * 使用Freemarker对XML的模板处理能力，将word文件作为模板进行数据更新并生成PDF文件，逻辑如下：
 * 1. 先将work模板文件转换为XML文件：通过MS Office另存文件为XML格式
 * 2. 将XML格式文件中的需要注入替换的内容修改为Freemarker的变量
 * 3. 通过Freemarker模板引擎进行内容的替换
 * 4. 将替换生成后的word文件转换成PDF文件，使用工具类Document进行xml文件到pdf文件的转换，
 *    该工具类只能转换ms office另存为的xml对象文件
 *
 * @title: FreemarkerConvertXmlToPdfV2.java
 * @package com.file.topdf.xmltopdf
 * @author sunroom
 * @date 2023/1/6 11:35 上午
*/
public class FreemarkerConvertXmlToPdfV2 {
    private Configuration configuration = null;

    public FreemarkerConvertXmlToPdfV2(){
        // 初始化模板引擎
        configuration = new Configuration();
        configuration.setDefaultEncoding("UTF-8");
    }

    public static void main(String[] args) {
        FreemarkerConvertXmlToPdfV2 test = new FreemarkerConvertXmlToPdfV2();
        test.initRes();
        test.createWord();
    }

    private boolean initRes() {
        try {
            // 这个地方是加载license的，res.xml，该文件来源网络，如有侵权请留言，将进行删除
            UrlResource resource = new ClassPathResource("res.xml");
            InputStream is = resource.getStream();
            License aposeLic = new License();
            aposeLic.setLicense(is);
            return true;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void createWord(){
        Map<String,Object> dataMap=new HashMap<String,Object>();
        getData(dataMap);
        // 设置resource根目录
        configuration.setClassForTemplateLoading(this.getClass(), "/");
        Template t=null;
        try {
            t = configuration.getTemplate("template_ms.ftl");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //生成文件的路径
        long time = System.currentTimeMillis();
        String outFile = "/Users/lixy/Documents/Workspace/privateWorkspace/temp/"+time+".docx";
        File pdfFile = new File("/Users/lixy/Documents/Workspace/privateWorkspace/temp/"+time+".pdf");

        try(Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)));
            OutputStream pdfOutputStream = new FileOutputStream(pdfFile)) {
            t.process(dataMap, out);
            out.close();

            Document document = new Document(outFile);
            document.save(pdfOutputStream, SaveFormat.PDF);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 这里赋值的时候需要注意,xml中需要的数据你必须提供给它,不然会报找不到某元素错的.
     * @param dataMap
     */
    private void getData(Map<String, Object> dataMap) {
        // 带有富文本标签的内容注入到模板文件时，需要进行处理
        String summary = "<p class=\"ql-align-justify\">亮点：</p><p class=\"ql-align-justify\">面对2022年复杂的外部经营环境，在风险中把握机遇、夯实基础、创新模式，实现学习成绩稳步增长。</p>";
        String newSummary = "";
        // 将段落进行转换，将换行和缩进展示出来
        for (String s : summary.split("</p>")) {
            // 该段内容是office工具支持的XML对象形式的代码段，用于进行换行缩进的描述，因为html中的<p>标签表示的段落
            String prefix = "<w:p>" +
                    "                            <w:pPr>" +
                    "                                <w:spacing w:line=\"500\" w:line-rule=\"exact\"/>" +
                    "                                <w:ind w:first-line=\"440\" w:first-line-chars=\"200\"/>" +
                    "                            </w:pPr>" +
                    "                            <w:r>" +
                    "                                <w:rPr>" +
                    "                                    <w:rFonts w:ascii=\"微软雅黑\" w:h-ansi=\"微软雅黑\" w:fareast=\"微软雅黑\" w:hint=\"fareast\"/>" +
                    "                                    <w:b w:val=\"off\"/>" +
                    "                                    <w:b-cs w:val=\"off\"/>" +
                    "                                    <w:color w:val=\"000000\"/>" +
                    "                                    <w:sz w:val=\"22\"/>" +
                    "                                    <w:sz-cs w:val=\"22\"/>" +
                    "                                    <w:lang w:val=\"EN-US\" w:fareast=\"ZH-CN\"/>" +
                    "                                </w:rPr>" +
                    "                                <w:t>";
            String suffix = "</w:t>" +
                    "                            </w:r>" +
                    "                        </w:p>";
            //  需要将原来内容中的html标签过滤掉
            newSummary += prefix + HtmlUtil.cleanHtmlTag(s) + suffix;
        }

        dataMap.put("summary", newSummary);
        dataMap.put("orgName", "测试组织");
        dataMap.put("initScore", "97.2");
        dataMap.put("finalScore", "98.1");
        dataMap.put("month", Calendar.getInstance().get(Calendar.MONTH)+1);
        dataMap.put("day", Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
    }
}
