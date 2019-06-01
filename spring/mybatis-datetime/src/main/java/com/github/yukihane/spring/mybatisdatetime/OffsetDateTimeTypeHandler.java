package com.github.yukihane.spring.mybatisdatetime;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.springframework.beans.factory.annotation.Autowired;

public class OffsetDateTimeTypeHandler extends BaseTypeHandler<OffsetDateTime> {

    @Autowired
    private ZoneOffset offset;

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
        return timestamp.toInstant().atOffset(offset);
    }
}