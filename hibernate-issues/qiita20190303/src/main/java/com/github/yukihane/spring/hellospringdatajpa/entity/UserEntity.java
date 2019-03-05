package com.github.yukihane.spring.hellospringdatajpa.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class UserEntity {

    @Id
    @GeneratedValue
    private Integer id;

    private Date basedate;

    public UserEntity(final Date basedate) {
        this.basedate = basedate;
    }
}
