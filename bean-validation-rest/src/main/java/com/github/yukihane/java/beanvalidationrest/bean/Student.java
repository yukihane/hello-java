package com.github.yukihane.java.beanvalidationrest.bean;

import com.github.yukihane.java.beanvalidationrest.validation.Date;
import com.github.yukihane.java.beanvalidationrest.validation.Digit;
import com.github.yukihane.java.beanvalidationrest.validation.YearMonthDay;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@YearMonthDay({ "birthYear", "birthMonth", "birthDayOfMonth" })
public class Student {

    @Min(1)
    private long id;

    @Size(min = 1, max = 10)
    private String name;

    @NotBlank
    @Size(min = 4, max = 4)
    @Digit
    private String birthYear;

    @NotBlank
    @Size(min = 2, max = 2)
    @Digit
    private String birthMonth;

    @NotBlank
    @Date
    private String birthDayOfMonth;

}
