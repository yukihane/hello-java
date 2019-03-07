package com.github.yukihane.java.beanvalidationrest.validation;

import java.time.LocalDate;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class YearMonthDayValidator implements ConstraintValidator<YearMonthDay, Object> {

    private static final Logger LOG = LoggerFactory.getLogger(YearMonthDayValidator.class);
    private String[] fields;

    @Override
    public void initialize(final YearMonthDay constraintAnnotation) {
        fields = constraintAnnotation.value();
    }
    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {

        if (value == null) {
            return true;
        }


        try {
            final BeanWrapper wrapper = new BeanWrapperImpl(value);
            final boolean known = (boolean) wrapper.getPropertyValue(fields[0]);
            if (known == false) {
                return true;
            }
            final CharSequence yearStr = (CharSequence) wrapper.getPropertyValue(fields[1]);
            final CharSequence monthStr = (CharSequence) wrapper.getPropertyValue(fields[2]);
            final CharSequence dayOfMonthStr = (CharSequence) wrapper.getPropertyValue(fields[3]);

            final int year = Integer.valueOf(yearStr.toString()).intValue();
            final int month = Integer.valueOf(monthStr.toString()).intValue();
            final int dayOfMonth = Integer.valueOf(dayOfMonthStr.toString()).intValue();

            LocalDate.of(year, month, dayOfMonth);
        } catch (final Exception e) {
            LOG.info("year-month-day format error", e);
            return false;
        }
        return true;
    }

}
