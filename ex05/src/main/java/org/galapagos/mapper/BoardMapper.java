package org.galapagos.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.galapagos.domain.BoardAttachmentVO;
import org.galapagos.domain.BoardVO;
import org.galapagos.domain.Criteria;
import org.springframework.test.context.TestExecutionListeners;

public interface BoardMapper {
//	@Select("select * from tbl_board")
	public List<BoardVO> getList();
	
	public List<BoardVO> getListWithPaging(Criteria cri);
	
	public void insert(BoardVO board);
	
	public void insertSelectKey(BoardVO board);
	
	public BoardVO read(Long bno); //bno : PK - where절 구성
	
	public int delete(Long bno); //영향받은 행의 갯수가 리턴됨.
	
	public int update(BoardVO board);
	
	public int getTotalCount(Criteria cri); //검색기능에서 쓰임.
	
	public void insertAttachment(BoardAttachmentVO attach);
	
	public List<BoardAttachmentVO> getAttachmentList(Long bno);
	
	public BoardAttachmentVO getAttachment(Long no);
	
	public int removeAttachment(Long no);
}
