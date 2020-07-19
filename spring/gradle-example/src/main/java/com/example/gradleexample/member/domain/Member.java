package com.example.gradleexample.member.domain;

import com.example.gradleexample.video.domain.VideoItem;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import lombok.Data;

@Entity
@Data
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    @Version
    private int version;

    private String name;

    private int age;

    @OneToMany
    private List<VideoItem> rentals;
}
