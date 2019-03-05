package com.github.yukihane.hibernateissues.qiita20180224.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.BatchSize;

@Data
@ToString(exclude = "grandsons")
@Entity
@Table(name = "child")
public class Child implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "parent_id")
    private int parentId;

    private String content;
    @BatchSize(size = 100)
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "child_id")
    private List<Grandsons> grandsons;

}