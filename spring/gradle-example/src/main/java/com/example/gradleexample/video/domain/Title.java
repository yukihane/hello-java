package com.example.gradleexample.video.domain;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import lombok.Data;

@Entity
@Data
public class Title {

    @Id
    @GeneratedValue
    private Long id;

    @Version
    private int version;

    private String name;

    @OneToMany(mappedBy = "title")
    private List<VideoItem> items;
}
