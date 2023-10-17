package org.galapagos.service;

import java.util.List;

import org.galapagos.domain.BoardAttachmentVO;
import org.galapagos.domain.BoardVO;
import org.galapagos.domain.Criteria;
import org.springframework.web.multipart.MultipartFile;

public interface BoardService {
	public int getTotal(Criteria cri);
	
	public void register(BoardVO board, List<MultipartFile> files) throws Exception;
	
	public BoardVO get(Long bno);
	
	public boolean modify(BoardVO board, List<MultipartFile> files) throws Exception;
	
	public boolean remove(Long bno);
	
//	public List<BoardVO> getList();
	
	public List<BoardVO> getList(Criteria cri);
	
	public BoardAttachmentVO getAttachment(Long no);
	
	public boolean removeAttachment(Long no);
}
