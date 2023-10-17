package org.galapagos.service;

import java.util.List;

import org.galapagos.domain.BoardAttachmentVO;
import org.galapagos.domain.BoardVO;
import org.galapagos.domain.Criteria;
import org.galapagos.mapper.BoardMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service //새로운 어노테이션 등장! 빈 등록이 일어남. (빼먹는 실수 하지 말자.)
@AllArgsConstructor //가독성을 중요시하는 사람들은 생성자를 주입해줌.
public class BoardServiceImpl implements BoardService {
//	@Autowired //편리함을 추구하는 사람들은 Autowired를 사용함.
	private BoardMapper mapper; // BoardMapper mapper : 생성자의 매개변수로 주
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void register(BoardVO board, List<MultipartFile> files) throws Exception{
//		log.info("register......." + board);
		mapper.insertSelectKey(board);
		Long bno = board.getBno();
		
		for(MultipartFile part: files) {
			if(part.isEmpty()) continue;
			BoardAttachmentVO attach = new BoardAttachmentVO(bno, part);
			mapper.insertAttachment(attach); //DB에 insert하는 과정
//			try {
//				//attach에서 예외가 발생한다면? : 
//				BoardAttachmentVO attach = new BoardAttachmentVO(bno, part); // ~~~/upload/board에 저장
//				throw new Exception("실패");
//				mapper.insertAttachment(attach); //DB에 insert하는 과정
//			} catch(Exception e) {
//				e.printStackTrace();
//			}
		}
	}

	@Override
	public BoardVO get(Long bno) {
//		log.info("get......" + bno);
		BoardVO board = mapper.read(bno);
		
		List<BoardAttachmentVO> list = mapper.getAttachmentList(bno);
		board.setAttaches(list);
		
		return board;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean modify(BoardVO board, List<MultipartFile> files) throws Exception {
		int result = mapper.update(board);
		Long bno = board.getBno();
		
		for(MultipartFile part: files) {
			if(part.isEmpty()) continue;
			BoardAttachmentVO attach = new BoardAttachmentVO(bno, part);
			mapper.insertAttachment(attach);
		}
		
		return result == 1;
	}

	@Override
	public boolean remove(Long bno) {
		log.info("remove...." + bno);
		return mapper.delete(bno) == 1;
	}

//	@Override
//	public List<BoardVO> getList() {
//		log.info("getList..........");
//		return mapper.getList();
//	}
	
	@Override
	public List<BoardVO> getList(Criteria cri) { //?pageNum=1&amount=5
		log.info("get List with citeria: " + cri);
		
		return mapper.getListWithPaging(cri);
	}
	
	@Override
	public int getTotal(Criteria cri) {
		log.info("get total count");
		
		return mapper.getTotalCount(cri);
	}
	
	@Override
	   public BoardAttachmentVO getAttachment(Long no) {
	      return mapper.getAttachment(no);
	}
	
	@Override
	   public boolean removeAttachment(Long no) {
	      return mapper.removeAttachment(no) == 1;
	}
}
