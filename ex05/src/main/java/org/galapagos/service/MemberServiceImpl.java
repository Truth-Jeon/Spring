package org.galapagos.service;

import java.io.File;
import java.io.IOException;

import org.galapagos.domain.AuthVO;
import org.galapagos.domain.MemberVO;
import org.galapagos.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnails;

@Service
@Log4j
public class MemberServiceImpl implements MemberService {

	public static final String AVATAR_UPDATE_DIR = "/Users/jeonhayoon/upload/avatar";

	@Autowired
	MemberMapper mapper;

	@Autowired
	private PasswordEncoder pwEncoder;

	@Override
	public MemberVO get(String username) {
		// TODO Auto-generated method stub
		return mapper.read(username);
	}

	@Override
	public void register(MemberVO member, MultipartFile avatar) throws IOException {
		// 1. 비밀번호 암호화
		String encPassword = pwEncoder.encode(member.getPassword());
		member.setPassword(encPassword);

		// 2. tbl_member에 저장
		mapper.insert(member);

		// 3. tbl_member_auth에 저장
		AuthVO auth = new AuthVO(member.getUsername(), "ROLE_USER");
		mapper.insertAuth(auth);

		// 4. avatar 이미지 저장
		if (!avatar.isEmpty()) {
			File dest = new File(AVATAR_UPDATE_DIR, member.getUsername() + ".png");

			Thumbnails.of(avatar.getInputStream()).size(50, 50).toFile(dest);
		}

	}
}
