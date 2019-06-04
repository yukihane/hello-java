package com.github.yukihane.spring.mybatisdatetime;

import java.sql.CallableStatement;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

//@MappedTypes({ String.class })
@MappedJdbcTypes(value = { JdbcType.VARCHAR, JdbcType.CHAR }, includeNullJdbcType = true)
public class StringTypeHandler extends org.apache.ibatis.type.StringTypeHandler {

    @Override
    public void setNonNullParameter(final PreparedStatement ps, final int i, final String parameter,
        final JdbcType jdbcType) throws SQLException {
        super.setNonNullParameter(ps, i, parameter, jdbcType);
        System.out.println(jdbcType);
        final ParameterMetaData md = ps.getParameterMetaData();
        final String className = md.getParameterClassName(i);
        final String typeName = md.getParameterTypeName(i);
        System.out.println("class: " + className + ", type: " + typeName);
    }

    @Override
    public String getNullableResult(final ResultSet rs, final String columnName) throws SQLException {
        final ResultSetMetaData md = rs.getMetaData();
        final int count = md.getColumnCount();
        for (int i = 1; i <= count; i++) {
            final String name = md.getColumnName(i);
            final String label = md.getColumnLabel(i);
            final int size = md.getColumnDisplaySize(i);
            System.out.println("paramter: " + columnName + ", name: " + name + ", label: " + label + ", size: " + size);
        }
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