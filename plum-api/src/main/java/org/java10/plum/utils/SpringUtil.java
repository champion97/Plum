package org.java10.plum.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Component;

/**
 * @author Tate<i@ridog.me>
 */
@Component
@Slf4j
public class SpringUtil extends ApplicationObjectSupport {
    private static ApplicationContext applicationContext = null;

    @Override
    protected void initApplicationContext(ApplicationContext context)throws BeansException {
        super.initApplicationContext(context);
        if(SpringUtil.applicationContext == null){
            SpringUtil.applicationContext = context;
            log.info("Spring Container initial successful");
        }
    }

    public static ApplicationContext getAppContext() {
        return applicationContext;
    }

    public static  <T>  T getBean(String name,Class<T> clazz){
        return (T)getAppContext().getBean(name, clazz);
    }

}
