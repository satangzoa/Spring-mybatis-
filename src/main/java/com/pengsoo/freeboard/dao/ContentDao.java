package com.pengsoo.freeboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.pengsoo.freeboard.dto.ContentDto;




public class ContentDao implements IDao{
	
	JdbcTemplate template;

	@Autowired
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	
	public ContentDao() {
		
	}
	
	@Override
	public ArrayList<ContentDto> listDao(){
		String query = "select * from board order by mid desc";
		ArrayList<ContentDto> dtos = (ArrayList<ContentDto>)template.query(query,	
				new BeanPropertyRowMapper<ContentDto>(ContentDto.class));
				return dtos;
}
	
	@Override
	public void writeDao(final String mWriter, final String mContent) {
		System.out.println("writeDao()");
		
		this.template.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) 
			throws SQLException {
				
		String query= "insert into board (mid, mWriter, mContent) values (board_seq.nextval,?,?)";
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setString(1,mWriter);
		pstmt.setString(2,mContent);
		return pstmt;
		}
		});
	}
	
	@Override
	public ContentDto viewDao( String strID) {
			System.out.println("viewDao()");
			
			String query = "select * from board where mid =" +strID;
			return template.queryForObject(query, new BeanPropertyRowMapper<ContentDto>(ContentDto.class));
	
}
	
	@Override
	public void deleteDao( final String bId) {
			System.out.println("deleteDao()");
			
			String query = "delete from board where mid =?" ;
			this.template.update(query, new PreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					
					ps.setInt(1,Integer.parseInt(bId));
				}
			});
}}
