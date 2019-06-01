package com.github.yukihane.spring.mybatisdatetime;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import lombok.AccessLevel;
import lombok.Getter;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.springframework.context.ApplicationContext;

public class OffsetDateTimeTypeHandler extends BaseTypeHandler<OffsetDateTime> {

    @Getter(value = AccessLevel.PRIVATE, lazy = true)
    private final ZoneOffset offset = offset();

    private ZoneOffset offset() {
        final ApplicationContext ctx = ApplicationContextProvider.getApplicationContext();
        return ctx.getBean(ZoneOffset.class);
    }

    @Override
    public void setNonNullParameter(final PreparedStatement ps, final int i, final OffsetDateTime parameter,
        final JdbcType jdbcType) throws SQLException {
        final Timestamp timestamp = Timestamp.from(parameter.toInstant());
        ps.setTimestamp(i, timestamp);
    }

    @Override
    public OffsetDateTime getNullableResult(final ResultSet rs, final String columnName) throws SQLException {
        return convertNullable(rs.getTimestamp(columnName));
    }

    @Override
    public OffsetDateTime getNullableResult(final ResultSet rs, final int columnIndex) throws SQLException {
        return convertNullable(rs.getTimestamp(columnIndex));
    }

    @Override
    public OffsetDateTime getNullableResult(final CallableStatement cs, final int columnIndex) throws SQLException {
        return convertNullable(cs.getTimestamp(columnIndex));
    }

    private OffsetDateTime convertNullable(final Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toInstant().atOffset(getOffset());
    }
}