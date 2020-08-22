package com.example.openapisample;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import lombok.Data;

@Entity
@Data
public class Book {

    @Id
    @GeneratedValue
    private Long id;

    @Version
    private int version;

    private String author;

    // modelと名前が異なっている例(modelではtitle)
    private String bookTitle;

    private int series;

}
