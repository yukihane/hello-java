package com.example.hexagonal_architecture

import org.apache.ibatis.annotations.*

@Mapper
interface PersonMapper {
    @Select("select * from person where id = #{id}")
    fun findById(@Param("id") id: Long): PersonEntity?

    @Select("select * from person order by id")
    fun findAll(): List<PersonEntity>

    @Insert("insert into person(name, age) values (#{name}, #{age})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    fun insert(entity: PersonEntity)
}
