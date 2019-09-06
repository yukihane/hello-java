package com.example.mybatismapperexample;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

@MappedJdbcTypes(value = { JdbcType.CHAR, JdbcType.VARCHAR }, includeNullJdbcType = true)
public class MessageTypeHandler extends BaseTypeHandler<Message> {

    @Override
    public void setNonNullParameter(final PreparedStatement ps, final int i, final Message parameter,
        final JdbcType jdbcType)
        throws SQLException {
        ps.setString(i, parameter.getMessage());
    }

    @Override
    public Message getNullableResult(final ResultSet rs, final String columnName) throws SQLException {
        final String str = rs.getString(columnName);
        return convertNullable(str);
    }

    @Override
    public Message getNullableResult(final ResultSet rs, final int columnIndex) throws SQLException {
        final String str = rs.getString(columnIndex);
        return convertNullable(str);
    }

    @Override
    public Message getNullableResult(final CallableStatement cs, final int columnIndex) throws SQLException {
        final String str = cs.getString(columnIndex);
        return convertNullable(str);
    }

    private static Message convertNullable(final String str) {
        if (str == null) {
            return null;
        }
        return Message.of(str);
    }
}
