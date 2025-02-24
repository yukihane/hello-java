package com.example.hexagonal_architecture

import org.apache.ibatis.annotations.*

@Mapper
interface PersonMapper {
    @Select("select * from people where id = #{id}")
    fun findById(@Param("id") id: Long): PersonEntity?

    @Select("select * from people order by id")
    fun findAll(): List<PersonEntity>

    @Insert("insert into people(name, age) values (#{name}, #{age})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    fun insert(entity: PersonEntity)
}
