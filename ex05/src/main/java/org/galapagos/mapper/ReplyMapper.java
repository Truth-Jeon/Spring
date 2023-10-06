package org.galapagos.mapper;

import org.galapagos.domain.ReplyVO;

public interface ReplyMapper {
	ReplyVO get(Long no);
	void create(ReplyVO vo);
	void update(ReplyVO vo);
	void delete(Long no);
}
