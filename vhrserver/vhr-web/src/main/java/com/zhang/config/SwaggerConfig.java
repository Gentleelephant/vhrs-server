package com.zhang.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.web.bind.annotation.GetMapping;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;


/**
 * @Author:Zpg
 * @Date:2020/4/1 22:13
 * @Version:1.0
 * @Description:
 */
@Configuration
public class SwaggerConfig {

    /** 配置Swagger的Docket实例*/
    @Bean
    public Docket docket(Environment environment){

        // 设置要显示的Swagger环境,可以根据不同的环境设置Swagger是否显示
        Profiles dev = Profiles.of("dev");

        // 通过environment.acceptsProfiles判断是否处在自己设定的环境中
        // boolean flag = environment.acceptsProfiles(dev);



        return new Docket(DocumentationType.SPRING_WEB)
                .apiInfo(apiInfo())
//                .enable(flag)      // 是否启动Swagger，如果为false则不能访问
                    .select()
                    // RequestHandlerSelectors：配置要扫描接口的方式
                    // basePackage：基于某个包进行扫描
                    // any：扫描全部
                    // none：不扫描
                    // withClassAnnotation：扫描类上的注解
                     .apis(RequestHandlerSelectors.basePackage("com.zhang.controller"))
//                    .apis(RequestHandlerSelectors.withMethodAnnotation(GetMapping.class))
                    // path() 过滤某路径
                    //.paths(PathSelectors.ant("com"))
                    .build();
    }

    @Bean
    public ApiInfo apiInfo(){
        // 作者信息
        Contact contact = new Contact("codezp", "http://www.codezp.club", "moon0hello@gmail");

        return new ApiInfo(
                "Vhr Api Documentation",
                "Vhr项目的API文档",
                "1.0",
                "",
                contact,
                "Apache 2.0",
                "",
                new ArrayList<VendorExtension>());
    }

}
