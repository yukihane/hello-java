package com.github.yukihane.java.beanvalidationrest.controller;

import com.github.yukihane.java.beanvalidationrest.bean.ClassRoom;
import com.github.yukihane.java.beanvalidationrest.repository.ClassRoomRepository;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private ClassRoomRepository repository;

    @GetMapping(path = "/rooms", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    public List<ClassRoom> list() {
        return repository.getAll();
    }

    @GetMapping(path = "/room/{id}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    public ClassRoom get(@PathVariable final long id) {
        return repository.getById(id).orElse(null);
    }

    @PostMapping(path = "/room", consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE }, produces = {
            MediaType.APPLICATION_JSON_UTF8_VALUE })
    public ClassRoom add(@RequestBody @Valid final ClassRoom classRoom) {
        repository.add(classRoom);
        return classRoom;
    }
}
