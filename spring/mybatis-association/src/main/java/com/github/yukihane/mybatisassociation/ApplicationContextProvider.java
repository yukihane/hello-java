package com.github.yukihane.mybatisassociation;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextProvider implements ApplicationContextAware {

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    private static class Holder {
        private static final Holder SINGLETON = new Holder();

        private ApplicationContext applicationContext;
    }

    @Override
    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
        Holder.SINGLETON.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return Holder.SINGLETON.applicationContext;
    }
}