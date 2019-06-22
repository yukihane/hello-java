package com.example.mybatisassociation.repository;

import static java.util.Comparator.comparingLong;

import com.example.mybatisassociation.entity.Sex;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SexRepositoryImpl implements SexRepository {

    private final SexMapper mapper;

    @Getter(lazy = true)
    private final Map<Long, Sex> cache = cache();

    @Override
    public Optional<Sex> findById(final long id) {
        final Sex sex = getCache().get(id);
        return Optional.ofNullable(sex);
    }

    private Map<Long, Sex> cache() {
        return mapper.findAll().stream().collect(Collectors.toMap(Sex::getId, Function.identity()));
    }

    @Override
    public List<Sex> findAll() {
        return getCache().values().stream()
            .sorted(comparingLong(Sex::getId)).collect(Collectors.toList());
    }
}
