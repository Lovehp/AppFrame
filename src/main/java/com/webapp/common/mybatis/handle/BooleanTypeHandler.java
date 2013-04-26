package com.webapp.common.mybatis.handle;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

@SuppressWarnings("rawtypes")
public class BooleanTypeHandler implements TypeHandler{

	public BooleanTypeHandler(){}

	@Override
	public void setParameter(PreparedStatement ps,int i,Object parameter,JdbcType jdbcType) throws SQLException{
		ps.setString(i,(Boolean)parameter==true?"Y":"N");
	}

	@Override
	public Object getResult(ResultSet rs,String columnName) throws SQLException{
		Boolean rt=Boolean.FALSE;
		if(rs.getString(columnName).equalsIgnoreCase("Y")){
			rt=Boolean.TRUE;
		}
		return rt;
	}

	@Override
	public Object getResult(CallableStatement cs,int columnIndex) throws SQLException{
		return cs.getBoolean(columnIndex)==true?"Y":"N";
	}

	@Override
	public Object getResult(ResultSet rs,int columnIndex) throws SQLException{
		return rs.getBoolean(columnIndex)==true?"Y":"N";
	}

}
