package com.hit.user_service_api.infrastructure.mybatis;

import com.hit.base_service_api.domain.object.EnumBase;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class EnumValueIntTypeHandler<E extends Enum<E>> extends BaseTypeHandler<E> {
  private Class<EnumBase<E>> cls;

  public EnumValueIntTypeHandler() {
  }

  public EnumValueIntTypeHandler(Class<EnumBase<E>> cls) {
    this.cls = (Class<EnumBase<E>>) cls;
  }

  @SuppressWarnings("unchecked")
  @Override
  public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType)
      throws SQLException {
    if (parameter == null) {
      ps.setObject(i, null);
    } else {
      ps.setObject(i, ((EnumBase<E>) parameter).getValue());
    }
  }

  @Override
  public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
    if (Objects.isNull(rs.getObject(columnName)))
      return null;
    return EnumBase.parseCode(cls, rs.getInt(columnName)).orElse(null);
  }

  @Override
  public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
    if (Objects.isNull(rs.getObject(columnIndex)))
      return null;
    return EnumBase.parseCode(cls, rs.getInt(columnIndex)).orElse(null);
  }

  @Override
  public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
    if (Objects.isNull(cs.getObject(columnIndex)))
      return null;
    return EnumBase.parseCode(cls, cs.getInt(columnIndex)).orElse(null);
  }

}
