package com.github.yukihane.java.beanvalidationrest.repository;

import com.github.yukihane.java.beanvalidationrest.bean.ClassRoom;
import com.github.yukihane.java.beanvalidationrest.bean.Student;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

@Repository
public class ClassRoomRepository {

    private final Map<Long, ClassRoom> classRooms = new HashMap<>();

    @PostConstruct
    public void init() {
        final List<Student> students = Arrays.asList(new Student(1L, "suzuki", "2000", "10", "10"),
                new Student(2L, "tanaka", "2000", "08", "08"));
        final ClassRoom room = new ClassRoom(1L, "1-1", students);
        add(room);
    }

    public List<ClassRoom> getAll() {
        return classRooms.values().stream().sorted((e1, e2) -> (int) (e1.getId() - e2.getId()))
                .collect(Collectors.toList());
    }

    public Optional<ClassRoom> getById(final long id) {
        return Optional.ofNullable(classRooms.get(id));
    }

    public Optional<ClassRoom> getByName(final String name) {
        return classRooms.values().stream().filter(e -> Objects.equals(e.getName(), name)).findAny();
    }

    public void add(final ClassRoom classRoom) {
        classRooms.put(classRoom.getId(), classRoom);
    }

}
