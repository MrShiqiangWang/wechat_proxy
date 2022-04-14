package com.wangsq.tools;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author 福泰
 * @version com.wangsq.tools.BeanUtils.java, v 0.1 2022年04月13日 10:56 AM 福泰
 */
@Component
public class BeanUtils implements ApplicationContextAware {

    private static ApplicationContext APPLICATION_CONTEXT;

    public static <T> Map<String, T> getBeanOfType(Class<T> clazz) {
        if (APPLICATION_CONTEXT != null) {
            return APPLICATION_CONTEXT.getBeansOfType(clazz);
        }
        return null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        APPLICATION_CONTEXT = applicationContext;
    }
}