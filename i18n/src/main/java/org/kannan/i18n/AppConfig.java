package org.kannan.i18n;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class AppConfig
{

    @Bean
    public MessageSource messageSource()
    {
        ReloadableResourceBundleMessageSource aMessageSrc = new ReloadableResourceBundleMessageSource();
        // all files under the classpath with prefix messages
        aMessageSrc.setBasename("classpath:messages");
        return aMessageSrc;
    }

    @Bean
    public LocaleResolver localeResolver()
    {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        return slr;
    }
}
