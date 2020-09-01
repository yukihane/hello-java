package com.github.yukihane.springboot.oauth2clientrestsample;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
