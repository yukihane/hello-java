package com.github.yukihane.examples.hello_thymeleaf_layout_dialect

import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration(proxyBeanMethods = false)
class MyConfig {

    @Bean
    fun layoutDialect(): LayoutDialect {
        return LayoutDialect()
    }
}
