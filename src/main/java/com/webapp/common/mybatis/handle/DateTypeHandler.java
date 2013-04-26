package com.webapp.common.mybatis.handle;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

public class DateTypeHandler implements TypeHandler<Date>{

	public DateTypeHandler(){}

	@Override
	public void setParameter(PreparedStatement ps,int i,Date parameter,JdbcType jdbcType) throws SQLException{
		 ps.setTimestamp(i,new Timestamp((parameter).getTime()));
	}

	@Override
	public Date getResult(ResultSet rs,String columnName) throws SQLException{
		return new Date(rs.getDate(columnName).getTime());
	}

	@Override
	public Date getResult(ResultSet rs,int columnIndex) throws SQLException{
		return new Date(rs.getDate(columnIndex).getTime());
	}

	@Override
	public Date getResult(CallableStatement cs,int columnIndex) throws SQLException{
		return new Date(cs.getDate(columnIndex).getTime());
	}

}
