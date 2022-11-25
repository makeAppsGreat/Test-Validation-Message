package kr.makeappsgreat.testvalidationmessage;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class SimpleConfiguration {

    @Bean("validationMessageSource")
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages/ValidationMessages");
        messageSource.setDefaultEncoding("UTF-8");

        return messageSource;
    }

    @Bean
    public LocalValidatorFactoryBean getValidator(@Qualifier("validationMessageSource") MessageSource messageSource) {
        System.out.println(">> 100 " + messageSource.getClass().getName());
        ((ResourceBundleMessageSource) messageSource).getBasenameSet().forEach(s -> System.out.println(">> 110 " + s));

        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource);


        return bean;
    }
}
