package com.github.yukihane.spring.mybatisdatetime;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

//@MappedTypes({ String.class })
@MappedJdbcTypes({ JdbcType.VARCHAR, JdbcType.CHAR })
public class StringTypeHandler extends org.apache.ibatis.type.StringTypeHandler {

    @Override
    public void setNonNullParameter(final PreparedStatement ps, final int i, final String parameter,
        final JdbcType jdbcType) throws SQLException {
        super.setNonNullParameter(ps, i, parameter, jdbcType);
        System.out.println(jdbcType);
    }

    @Override
    public String getNullableResult(final ResultSet rs, final String columnName) throws SQLException {
        return super.getNullableResult(rs, columnName);
    }

    @Override
    public String getNullableResult(final ResultSet rs, final int columnIndex) throws SQLException {
        return super.getNullableResult(rs, columnIndex);
    }

    @Override
    public String getNullableResult(final CallableStatement cs, final int columnIndex) throws SQLException {
        return super.getNullableResult(cs, columnIndex);
    }

}