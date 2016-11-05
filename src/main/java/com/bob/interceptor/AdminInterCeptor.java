package com.bob.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.bob.dao.member.MemberVo;
import com.bob.mook.SingletonSetting;

public class AdminInterCeptor extends HandlerInterceptorAdapter {
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {	
		MemberVo memberVo = (MemberVo) request.getSession().getAttribute("memberVo");
		
		try {
			// logininfo 세션값이 널일경우
			if (memberVo == null) {
				// 로그인페이지로 redirect
				SingletonSetting ssi = SingletonSetting.getInstance();
				response.sendRedirect(ssi.getPath());

				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			// 관리자가 아닐 경우
			if (!memberVo.isAdmin()) {
				// 홈페이지로 redirect
				SingletonSetting ssi = SingletonSetting.getInstance();
				response.sendRedirect(ssi.getPath());

				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 널이 아니면 정상적으로 컨트롤러 호출(관리자임)
		return true;
	}
}
