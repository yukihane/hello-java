package com.github.yukihane.samplespringdataderivedquery.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class MyEntity {

    @Id
    private Long id;

    private String name;
}
