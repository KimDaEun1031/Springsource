package com.company.mapper;

import com.company.domain.RegisterVO;

public interface BlogMapper {
	public int insertRegister(RegisterVO regist);
	public int deleteRegister(RegisterVO regist);
	public int updateRegister(RegisterVO regist);
}
