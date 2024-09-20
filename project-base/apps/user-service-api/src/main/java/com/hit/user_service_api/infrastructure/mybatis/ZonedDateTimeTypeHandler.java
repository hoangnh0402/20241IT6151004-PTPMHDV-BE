package com.hit.user_service_api.infrastructure.mybatis;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

import java.sql.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;

@MappedJdbcTypes(JdbcType.TIMESTAMP)
public class ZonedDateTimeTypeHandler extends BaseTypeHandler<ZonedDateTime> {
  @Override
  public void setNonNullParameter(PreparedStatement ps, int i, ZonedDateTime parameter,
                                  JdbcType jdbcType) throws SQLException {
    ps.setTimestamp(i, Timestamp.from(parameter.toInstant()));
  }

  @Override
  public ZonedDateTime getNullableResult(ResultSet rs, String columnName) throws SQLException {
    Timestamp ts = rs.getTimestamp(columnName, Calendar.getInstance());
    if (ts != null) {
      return convertTimestampToZonedDateTime(ts);
    }
    return null;
  }

  @Override
  public ZonedDateTime getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
    Timestamp ts = rs.getTimestamp(columnIndex, Calendar.getInstance());
    if (ts != null) {
      return convertTimestampToZonedDateTime(ts);
    }
    return null;
  }

  @Override
  public ZonedDateTime getNullableResult(CallableStatement cs, int columnIndex)
      throws SQLException {
    Timestamp ts = cs.getTimestamp(columnIndex, Calendar.getInstance());

    if (ts != null) {
      return convertTimestampToZonedDateTime(ts);
    }
    return null;
  }

  private ZonedDateTime convertTimestampToZonedDateTime(Timestamp ts) {
    return ZonedDateTime.ofLocal(ts.toLocalDateTime(), ZoneId.systemDefault(), null);
  }
}
