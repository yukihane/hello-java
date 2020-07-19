package com.example.gradleexample.video.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import lombok.Data;

@Entity
@Data
public class VideoItem {

    @Id
    @GeneratedValue
    private Long id;

    @Version
    private int version;

    @ManyToOne(optional = false)
    private Title title;

    /** メディア管理番号 */
    private String managementNumber;
}
