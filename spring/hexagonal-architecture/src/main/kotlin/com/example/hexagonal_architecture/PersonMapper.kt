package com.example.hexagonal_architecture

import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select

@Mapper
interface PersonMapper {
    @Select("select * from person where id = #{id}")
    fun findById(@Param("id") id: Long): PersonEntity?

    @Select("select * from person order by id")
    fun findAll(): List<PersonEntity>
}
