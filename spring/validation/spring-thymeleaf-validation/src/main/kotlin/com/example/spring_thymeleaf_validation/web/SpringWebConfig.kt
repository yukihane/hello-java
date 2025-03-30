package com.example.spring_thymeleaf_validation.web

import com.example.spring_thymeleaf_validation.business.services.VarietyService
import com.example.spring_thymeleaf_validation.web.conversion.DateFormatter
import com.example.spring_thymeleaf_validation.web.conversion.VarietyFormatter
import org.springframework.beans.BeansException
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.context.MessageSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.ResourceBundleMessageSource
import org.springframework.format.FormatterRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class SpringWebConfig : WebMvcConfigurer, ApplicationContextAware {
    private lateinit var applicationContext: ApplicationContext


    @Throws(BeansException::class)
    override fun setApplicationContext(applicationContext: ApplicationContext) {
        this.applicationContext = applicationContext
    }

    /*
     *  Message externalization/internationalization
     */
    @Bean
    fun messageSource(): ResourceBundleMessageSource {
        val messageSource = ResourceBundleMessageSource()
        messageSource.setBasename("Messages")
        return messageSource
    }

    /*
     * Add formatter for class *.stsm.business.entities.Variety
     * and java.util.Date in addition to the ones registered by default
     */
    override fun addFormatters(registry: FormatterRegistry) {
        super.addFormatters(registry)
        registry.addFormatter(varietyFormatter(applicationContext.getBean(VarietyService::class.java)))
        registry.addFormatter(dateFormatter(applicationContext.getBean(MessageSource::class.java)))
    }

    @Bean
    fun varietyFormatter(varietyService: VarietyService): VarietyFormatter {
        return VarietyFormatter(varietyService)
    }

    @Bean
    fun dateFormatter(messageSource: MessageSource): DateFormatter {
        return DateFormatter(messageSource)
    }
}
