package com.example.mybatisassociation.repository;

import com.example.mybatisassociation.ApplicationContextProvider;
import com.example.mybatisassociation.entity.Sex;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.springframework.context.ApplicationContext;

public class SexTypeHandler extends BaseTypeHandler<Sex> {

    @Override
    public void setNonNullParameter(final PreparedStatement ps, final int i, final Sex parameter,
        final JdbcType jdbcType) throws SQLException {
        final long id = parameter.getId();
        ps.setLong(i, id);
    }

    @Override
    public Sex getNullableResult(final ResultSet rs, final String columnName) throws SQLException {
        final long id = rs.getLong(columnName);
        if (rs.wasNull()) {
            return null;
        }
        return findSex(id);
    }

    private Sex findSex(final long id) {
        final ApplicationContext context = ApplicationContextProvider.getApplicationContext();
        final SexRepository sexMapper = context.getBean(SexRepository.class);
        final Optional<Sex> sex = sexMapper.findById(id);
        return sex.get();
    }

    @Override
    public Sex getNullableResult(final ResultSet rs, final int columnIndex) throws SQLException {
        final long id = rs.getLong(columnIndex);
        if (rs.wasNull()) {
            return null;
        }
        return findSex(id);
    }

    @Override
    public Sex getNullableResult(final CallableStatement cs, final int columnIndex) throws SQLException {
        final long id = cs.getLong(columnIndex);
        if (cs.wasNull()) {
            return null;
        }
        return findSex(id);
    }

}
