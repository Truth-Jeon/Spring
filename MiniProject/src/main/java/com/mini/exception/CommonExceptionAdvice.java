package com.mini.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.log4j.Log4j;

@ControllerAdvice
@Log4j
public class CommonExceptionAdvice {
	@ExceptionHandler(Exception.class) // 처리할 예외, 500번 예외
	public String except(Exception ex, Model model) { // 실제 예외, 모델 객체
		log.error("Exception ......" + ex.getMessage());
		model.addAttribute("exception", ex);// 예외페이지 담기
		log.error(model);
		return "error_page"; // view 이름 //에러페이지로 이동
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handle404(NoHandlerFoundException ex) {
		return "custom404"; //view 이름, custom404.jsp
	}
}
