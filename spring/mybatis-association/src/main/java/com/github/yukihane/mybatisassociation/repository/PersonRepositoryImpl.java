package com.github.yukihane.mybatisassociation.repository;

import com.github.yukihane.mybatisassociation.entity.Person;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PersonRepositoryImpl implements PersonRepository {

    private final InnerMapper mapper;

    @Override
    public Optional<Person> findById(final long id) {
        return mapper.findById(id);
    }

    @Mapper
    interface InnerMapper {
        @Select("select * from person where id = #{id}")
        Optional<Person> findById(long id);
    }

}
