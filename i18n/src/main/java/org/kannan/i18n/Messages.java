package org.kannan.i18n;

import java.util.Locale;
import java.util.MissingResourceException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

@Component
public class Messages
{
    @Autowired
    private MessageSource messageSource;

    private MessageSourceAccessor accessor;

    // To let the messages accessed statically
    private static Messages ownInstance;

    // Default locale for your app.
    // app.default.locale expected to be available in application.properties/yml
    @Value("${app.default.locale}")
    private String defaultLocale;

    @PostConstruct
    public void init()
    {
        Locale.setDefault(new Locale(defaultLocale));
        accessor = new MessageSourceAccessor(messageSource);
        ownInstance = this;
    }

    private String getString(String key)
    {
        try
        {
            return accessor.getMessage(key);
        } catch (MissingResourceException|NoSuchMessageException e)
        {
            return '!' + key + '!';
        }
    }

    /**
     * Returns the Locale specific message from the configured
     * {@link MessageSource}
     * 
     * @param theKey
     *            The key for which the message will be returned.
     * @return The message string associated with the given key.
     */
    public static String getMessage(String theKey)
    {
        return ownInstance.getString(theKey);
    }
    
    public static void main(String[] args)
    {
        ReloadableResourceBundleMessageSource aMessageSrc = new ReloadableResourceBundleMessageSource();
        aMessageSrc.setBasename("classpath:messages");

        LocaleContextHolder.setLocale(new Locale("en_US"));

        Messages aMessages = new Messages();
         ownInstance = aMessages;
        aMessages.accessor = new MessageSourceAccessor(aMessageSrc);
        
        System.out.println(Messages.getMessage("i18n.message"));
        
    }
}