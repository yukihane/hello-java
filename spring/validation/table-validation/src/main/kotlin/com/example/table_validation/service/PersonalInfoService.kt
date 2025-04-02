package com.example.table_validation.service

import com.example.table_validation.model.PersonalInfo
import org.springframework.stereotype.Service
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicLong

@Service
class PersonalInfoService {
    private val storage = ConcurrentHashMap<Long, PersonalInfo>()
    private val idSequence = AtomicLong(0)

    fun save(personalInfo: PersonalInfo): Long {
        val id = idSequence.incrementAndGet()
        storage[id] = personalInfo
        return id
    }

    fun saveAll(list: List<PersonalInfo>): List<Long> {
        return list.map { save(it) }
    }

    fun getAll(): List<PersonalInfo> {
        return storage.values.toList()
    }

    fun clear() {
        storage.clear()
    }
}
