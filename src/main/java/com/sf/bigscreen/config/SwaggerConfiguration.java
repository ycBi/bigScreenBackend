package com.sf.bigscreen.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author: bi xuan
 * @Date: 2021/6/18 16:07
 * @Description:Swagger配置类，该类里面的应该是固定的，主要用来设置文档的主题信息，比如文档的大标题，副标题，公司名 * 等
 **/
@Configuration//托管spring
@EnableSwagger2//开启swagger功能
public class SwaggerConfiguration {
    /**
     * 创建API应用
     * apiInfo() 增加API相关信息
     * 通过select()函数返回一个ApiSelectorBuilder实例,用来控制哪些接口暴露给Swagger来展现，
     * 本例采用指定扫描的包路径来定义指定要建立API的目录。
     *
     * @return
     */
    @Bean
    public Docket createRestApi() {
        //版本类型是swagger2
        return new Docket(DocumentationType.SWAGGER_2)
                //通过调用自定义方法apiInfo，获得文档的主要信息
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.sf.bigscreen.controller"))//扫描该包下面的API注解
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 创建该API的基本信息（这些基本信息会展现在文档页面中）
     * 访问地址：http://项目实际地址/swagger-ui.html
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("可视化大屏软件开发接口文档") //接口管理文档首页显示
                .description("可视化大屏软件开发")//API的描述
                .termsOfServiceUrl("192.168。7.219:9277")//网站url等
                .version("1.0")
                .build();

    }
}