package com.adle.projet.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan( basePackages = { "com.adle.projet" } )
public class WebMvcConfig implements WebMvcConfigurer {

    private int maxUploadSizeInMb = 5 * 1024 * 1024;

    @Bean
    public InternalResourceViewResolver resolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass( JstlView.class );
        resolver.setPrefix( "/WEB-INF/views/" );
        resolver.setSuffix( ".jsp" );
        return resolver;
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasename( "messages" );
        return source;
    }

    @Bean( name = "multipartResolver" )
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize( maxUploadSizeInMb * 2 );
        multipartResolver.setMaxUploadSizePerFile( maxUploadSizeInMb );
        return multipartResolver;
    }

    public Validator getValidator() {
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.setValidationMessageSource( messageSource() );
        return validator;
    }

    public void addResourceHandlers( ResourceHandlerRegistry registry ) {
        registry
                .addResourceHandler( "/resources/**" )
                .addResourceLocations( "/resources/" );
    }
}
