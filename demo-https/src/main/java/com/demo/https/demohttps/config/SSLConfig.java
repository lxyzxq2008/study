package com.demo.https.demohttps.config;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SSLConfig {
    /**
     * spring boot 1.0
     */
//   @Bean
//    public EmbeddedServletContainerFactory servletContainer() {
//        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory() {
//            @Override
//            protected void postProcessContext(Context  context) {
//                SecurityConstraint constraint = new SecurityConstraint();
//                constraint.setUserConstraint("CONFIDENTIAL");
//                SecurityCollection collection = new SecurityCollection();
//                collection.addPattern("/*");
//                constraint.addCollection(collection);
//                context.addConstraint(constraint);
//            }
//        };
//        tomcat.addAdditionalTomcatConnectors(httpConnector());
//        return tomcat;
//    }

    /**
     * springboot 2.0
     * @return
     */
    @Bean
    public TomcatServletWebServerFactory servletWebServerFactory() {
        TomcatServletWebServerFactory tomcat =
                new TomcatServletWebServerFactory() {
                    @Override
                    protected void postProcessContext(Context context) {
                        SecurityConstraint securityConstraint = new SecurityConstraint();
                        securityConstraint.setUserConstraint("CONFIDENTIAL");
                        SecurityCollection collection = new SecurityCollection();
                        collection.addPattern("/*");
                        securityConstraint.addCollection(collection);
                        context.addConstraint(securityConstraint);
                    }
                };

        tomcat.addAdditionalTomcatConnectors(initiateHttpConnector());
        return tomcat;
    }

    private Connector initiateHttpConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        // 设置将分配给通过此连接器接收的请求的方案,当通过http访问资源时，将使用此连接器
        connector.setScheme("http");
        // 设置http请求时使用的端口
        connector.setPort(9090);
        // 设置通过此链接器的请求的安全连接标志
        // 当设置true时，HTTP请求就是用HTTP协议；
        // 当设置false时，HTTP请求会被重定向到HTTPS协议进行访问
        connector.setSecure(false);
        // 被重定向HTTP请求所对应的HTTPS协议端口
        connector.setRedirectPort(8080);
        return connector;
    }
}
