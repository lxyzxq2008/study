[返回](../README.md)
# SpringBoot 配置使用HTTPS

HTTPS介绍
=================
>传输层安全性协议（英语：Transport Layer Security，缩写作 TLS），及其前身安全套接层（Secure Sockets Layer，缩写作 SSL）是一种安全协议，目的是为互联网通信，提供安全及数据完整性保障。网景公司（Netscape）在1994年推出首版网页浏览器，网景导航者时，推出HTTPS协议，以SSL进行加密，这是SSL的起源。IETF将SSL进行标准化，1999年公布第一版TLS标准文件。随后又公布RFC 5246 （2008年8月）与 RFC 6176 （2011年3月）。在浏览器、电子邮件、即时通信、VoIP、网络传真等应用程序中，广泛支持这个协议。主要的网站，如Google、Facebook等也以这个协议来创建安全连接，发送数据。目前已成为互联网上保密通信的工业标准。
 SSL包含记录层（Record Layer）和传输层，记录层协议确定传输层数据的封装格式。传输层安全协议使用X.509认证，之后利用非对称加密演算来对通信方做身份认证，之后交换对称密钥作为会谈密钥（Session key）。这个会谈密钥是用来将通信两方交换的数据做加密，保证两个应用间通信的保密性和可靠性，使客户与服务器应用之间的通信不被攻击者窃听。
 在配置TLS/SSL之前我们需要拿到相应签名的证书，测试实例可以使用Java 下面的 Keytool 来生成证书：

生成证书
=================

    keytool -genkey -alias tomcat -keypass 123456 -storetype PKCS12 -keyalg RSA -keysize 2048  -keystore keystore.p12 -validity 3650 -storepass 123456
        -alias tomcat(别名)  
        -keypass 123456(别名密码)   
        -keyalg RSA(算法)  
        -keysize 1024(密钥长度)  
        -storetype PKCS12（类型）
        -validity 3650(有效期，天单位)  
        -keystore /Users/lixueyang/Workspace/GitHub/study/demo-https/cer/keystore.keystore(指定生成证书的位置和证书名称)  
        -storepass 123456(获取keystore信息的密码)
        
生成的证书放到resources目录中

配置properties文件
=================
    server.ssl.key-store=classpath:keystore.p12
    server.ssl.key-store-password=123456
    server.ssl.key-alias=httpsdemo
    server.ssl.key-store-type=PKCS12
    
添加配置文件
=================
>参看 com.demo.https.demohttps.config.SSLConfig

##测试地址
>https://localhost:8080/index
