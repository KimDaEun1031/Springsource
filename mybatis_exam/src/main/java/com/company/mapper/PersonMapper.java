package com.company.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.company.domain.PersonVO;

public interface PersonMapper {
	//인라인 처리 방식 - sql 구문을 인라인 방식
	//이것보다 xml로 사용하는 방식을 주 선호
//	@Insert("insert into person(id,name) values(#{id},#{name})")
	//sql구문을 적를 때 ?부분이 values(?,?,?) mybatis에서는 #으로 들어감
	//public int insert(String id, String name); //개별로 넘기는 형식은 허용치 않음 - 못씀
//	public int insert(@Param("id")String id, @Param("name")String name);
//	@Update("update person set name=#{name} where id=#{id}")
//	public int update(@Param("id")String id, @Param("name")String name);
//	@Delete("delete from person where id=#{id}")
//	public int delete(@Param("id")String id);
	
	//이 sql 구문은 xml 방식
	public int insert(@Param("id")String id, @Param("name")String name);
	public int update(@Param("id")String id, @Param("name")String name);
	public int delete(@Param("id")String id);
	public String getPerson(String id);
	public List<PersonVO> selectAll();
}
