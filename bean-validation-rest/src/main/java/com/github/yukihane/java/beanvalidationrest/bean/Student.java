package com.github.yukihane.java.beanvalidationrest.bean;

import com.github.yukihane.java.beanvalidationrest.validation.Date;
import com.github.yukihane.java.beanvalidationrest.validation.Digit;
import com.github.yukihane.java.beanvalidationrest.validation.RequireIfNeeded;
import com.github.yukihane.java.beanvalidationrest.validation.YearMonthDay;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@YearMonthDay({ "knownBirthday", "birthYear", "birthMonth", "birthDayOfMonth" })
@RequireIfNeeded({ "knownBirthday", "birthYear" })
@RequireIfNeeded({ "knownBirthday", "birthMonth" })
@RequireIfNeeded({ "knownBirthday", "birthDayOfMonth" })
public class Student {

    @Min(1)
    private long id;

    @Size(min = 1, max = 10)
    private String name;

    private boolean knownBirthday;

    @Size(min = 4, max = 4)
    @Digit
    private String birthYear;

    @Size(min = 2, max = 2)
    @Digit
    private String birthMonth;

    @Date
    private String birthDayOfMonth;

}
