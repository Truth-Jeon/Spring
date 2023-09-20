package org.galapagos.mapper;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper { //<- 구현체를 동적으로 만들어줌 (MyBatis가 해줌)
	//어노테이션을 통해 SQL 제정
	@Select("SELECT sysdate()")
	public String getTime(); //public 생략 가능함
	
	public String getTime2();//TimeMapper.xml을 통해서 정의하겠다
}