package com.github.yukihane.spring.hellospringdatajpa.repository;

import java.util.Date;

public interface CustomUserRepository {
    int deletByBasedateRange(final Date start, final Date end);
}
