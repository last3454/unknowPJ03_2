package com.unknown.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.unknown.config.CustomException;
import com.unknown.data.entity.user.UserEntity;
import com.unknown.data.repo.user.UserRepo;
import com.unknown.model.enums.LoginException;
import com.unknown.model.vo.SessionVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	final private UserRepo userRepo;

	public SessionVO checkLogin(String loginId, String loginPw, HttpServletRequest request) {
		UserEntity userEntity = userRepo.findByLoginIdIgnoreCase(loginId);
		if(userEntity == null) {
			throw new CustomException(LoginException.USER_EMPTY);
		}

		//비밀번호 5회 이상 불일치
		if(userEntity.getPassFailCnt() >= 5) {
			throw new CustomException(LoginException.USER_PASS_FAIL_LIMIT);
		}

		//비밀번호 불일치
		if(loginPw.equals(userEntity.getPass())) {
			userEntity.setPassFailCnt(userEntity.getPassFailCnt()+1);
			throw new CustomException(LoginException.USER_PASS_FAIL);
		}

		return SessionVO.builder()
				.loginId(loginId)
				.loginNm("")
				.email("").build();
	}
}
