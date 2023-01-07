package com.file.topdf.xmltopdf;

import cn.hutool.http.HtmlUtil;
import com.documents4j.api.DocumentType;
import com.documents4j.job.LocalConverter;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.*;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * 使用Freemarker对XML的模板处理能力，将word文件作为模板进行数据更新并生成PDF文件，逻辑如下：
 * 1. 先将work模板文件转换为XML文件：通过WPS/MS Office另存文件为XML格式
 * 2. 将XML格式文件中的需要注入替换的内容修改为Freemarker的变量
 * 3. 通过Freemarker模板引擎进行内容的替换
 * 4. 将替换生成后的word文件转换成PDF文件，使用工具类LocalConverter，但是该工具类限于Windows使用，
 *    该工具类对word和xml的对应关系有要求，例如：本地使用的wps工具，xml就需要是wps工具对word进行另存为出来的xml文件
 *    如果本地使用的是ms office，xml就需要时ms office工具对word进行另存为出来的xml文件。如果不对应会有解析问题。
 *
 * @title: FreemarkerConvertXmlToPdfV1.java
 * @package com.file.topdf.xmltopdf
 * @author sunroom
 * @date 2023/1/6 11:35 上午
*/
public class FreemarkerConvertXmlToPdfV1 {
    private Configuration configuration = null;

    public FreemarkerConvertXmlToPdfV1(){
        // 初始化模板引擎
        configuration = new Configuration();
        configuration.setDefaultEncoding("UTF-8");
    }

    public static void main(String[] args) {
        FreemarkerConvertXmlToPdfV1 test = new FreemarkerConvertXmlToPdfV1();
        test.createWord();
    }

    public void createWord(){
        Map<String,Object> dataMap=new HashMap<String,Object>();
        getData(dataMap);
        // 这个我是放到resource根目录了，根据实际目录做修改
        configuration.setClassForTemplateLoading(this.getClass(), "/");
        Template t=null;
        try {
            t = configuration.getTemplate("template_wps.ftl");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //生成文件的路径
        long time = System.currentTimeMillis();
        File outFile = new File("/Users/lixy/Documents/Workspace/privateWorkspace/temp/"+time+".docx");
        File pdfFile = new File("/Users/lixy/Documents/Workspace/privateWorkspace/temp/"+time+".pdf");

        try(Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)));
            InputStream docInputStream = new FileInputStream(outFile);
            OutputStream pdfOutputStream = new FileOutputStream(pdfFile)) {
            t.process(dataMap, out);
            // 关闭将内容写入word
            out.close();

            // LocalConverter类依赖windows环境并且本地安装的office工具类才可以使用，否则将无法正常打开使用word convert pdf的功能
            LocalConverter.builder().build()
                    .convert(docInputStream)
                    .as(DocumentType.DOCX)
                    .to(pdfOutputStream)
                    .as(DocumentType.PDF).execute();
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
        String summary = "<p class=\"ql-align-justify\">亮点：</p><p class=\"ql-align-justify\">面对2022年复杂的外部经营环境，能源板块在风险中把握机遇、夯实基础、创新模式，实现能源板块业绩稳定增长，并在以下六个方面取得突出进展：</p><p class=\"ql-align-justify\">一是在资源价格高企的情况下，全面落实大客户合同机制，通过数智产品加强客户需求预测，灵活调整产品组合和价格模式，守固客户稳定创值，实现91%的大客户优化供用气协议，零售气综合价差优于行业标杆。</p><p class=\"ql-align-justify\">二是通过接收站合作、船舶租赁等多种模式加强与三大油的生态合作，确保国内资源基本盘稳定，全年获取三大油合同量资源140.3亿方，超出90%合同量规则约10.7亿方，同时加大非常规资源获取力度，通过搭建多点上载多点下载托运网络，成为三大油以外唯一具备陆上规模性管输网络的托运商，全面获取非常规资源超过10亿方，市场占有率超过1/3。</p><p class=\"ql-align-justify\">三是积极应对国际资源价格变化，全场景协同，通过国内国际需求和资源的灵活统筹、国际LNG运力池的搭建，实现国内资源置换国际资源，长约资源全部如期交付，支撑了能源板块创值目标的达成。同时依托自主的ETMO等风控数智产品，快速识别市场、实货、纸货端风险，为实纸结合平抑价格波动风险支撑全场景最优创值奠定基础。</p><p class=\"ql-align-justify\">四是稳步推进舟山基础设施的重组上市，通过妥善解决海域使用权等历史遗留问题，并通过积极开拓三方资源，全年引流10船资源到舟山接卸，为释放舟山支点价值和多元化运营探索了可行的模式。</p><p class=\"ql-align-justify\">五是依托天然气全场景，完成好气网蓝图设计和主要产品功能开发，并在浙江、山东试点取得预期效果，大客户-城燃用能服务、工程服务场景、大客户资源商管道气资源协同、LNG资源商-城燃/LNG终端客户LNG交易交付等场景得到验证，并开创性的探索了11.28好气节，好气网产业互联网平台初具雏形。</p><p class=\"ql-align-justify\">六是稳步推进外部机构和政府对能源板块业务的认知，年内实现标普对新奥股份直销气模式的认可并首次给予BBB-级，穆迪、标普也提升新奥能源至BBB+级，新奥股份还首次被中诚信、鹏元提升至AAA级。同时借助强大项目落地，积极与河北省税局沟通能源板块13家境外企业中国税收居民企业认定，并成为河北省首家认定的企业。评级机构和税务部门认可，为公司融资授信以及资金出境通道打下良好基础。</p><p class=\"ql-align-justify\">不足：</p><p>在面对今年的环境中，能源板块在以下三个方面还有待提升：一是好气网的商业模式仍在探索，产品对支撑产业创值还不明显；二是智企业务有了快速的进步，但在模式沉淀和现有项目运营提升上还有待加强；三是能力机制方面，匹配战略升级的核心业务能力、生态组织等还需迭代优化。</p>";
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
