package com.github.yukihane.hibernateissues.qiita20180224.repository;

import com.github.yukihane.hibernateissues.qiita20180224.entity.Parent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentRepository extends JpaRepository<Parent, Integer> {
}
