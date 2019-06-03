package com.github.yukihane.spring.mybatisdatetime;

import java.time.Clock;
import java.time.OffsetDateTime;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController("/datetime")
public class DateTimeController {

    private final Clock clock;
    private final DateTimeMapper mapper;

    @GetMapping
    public String datetime() {

        final OffsetDateTime now = OffsetDateTime.now(clock);
        mapper.update(now);
        mapper.updateTz(now);

        final OffsetDateTime selectedDatetime = mapper.select();
        final OffsetDateTime selectedDatetimeTz = mapper.selectTz();

        return "Persisted Time: " + selectedDatetime + " TZ: " + selectedDatetimeTz;
    }

    @Mapper
    public interface DateTimeMapper {

        @Select("select col_timestamp from my_datetime")
        OffsetDateTime select();

        @Update("update my_datetime set col_timestamp = #{now} where id = 1")
        void update(OffsetDateTime now);

        @Select("select col_timestamptz from my_datetime")
        OffsetDateTime selectTz();

        @Update("update my_datetime set col_timestamptz = #{now} where id = 1")
        void updateTz(OffsetDateTime now);
    }
}
