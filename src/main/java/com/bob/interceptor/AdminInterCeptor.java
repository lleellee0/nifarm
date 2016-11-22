package com.bob.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.bob.dao.company_info.CompanyInfoDao;
import com.bob.dao.company_info.CompanyInfoVo;
import com.bob.dao.farm_info.FarmInfoDao;
import com.bob.dao.farm_info.FarmInfoVo;
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
				String requestUri = request.getRequestURI();
				
				
				SingletonSetting ssi = SingletonSetting.getInstance();
				
				
				
				CompanyInfoDao cidao = new CompanyInfoDao();
				CompanyInfoVo civo = cidao.selectByMemberIndex(memberVo.getIndex());
				
				FarmInfoDao fidao = new FarmInfoDao();
				FarmInfoVo fivo = fidao.selectByMemberIndex(memberVo.getIndex());
				
				System.out.println("**** " + ssi.getPath() + "inner/result/" + fivo.getIndex() + "/" + fivo.getCheck_count());
				System.out.println("*** " + requestUri);
				
				
				if(requestUri.contains(ssi.getPath() + "inner/api/image/"))
					return true;
				

				
				if(civo.getIndex() != 0 && (( (ssi.getPath() + "inner/company/result/" + civo.getIndex() + "/" + civo.getCheck_count()).equals(requestUri) || 
						(ssi.getPath() + "inner/company/api/result/" + civo.getIndex() + "/" + civo.getCheck_count() ).equals(requestUri) || 
						(ssi.getPath() + "inner/company/api/result/" + civo.getIndex()) .equals(requestUri)  ))) {	// 자기 기업인 경우
					//response.sendRedirect(ssi.getPath() + "inner/company/result/" + civo.getIndex() + "/" + civo.getCheck_count());
					return true;
				}
				else if(fivo.getIndex() != 0 && (( (ssi.getPath() + "inner/result/" + fivo.getScale() + "/" + fivo.getIndex() + "/" + fivo.getCheck_count()).equals(requestUri) || 
						(ssi.getPath() + "inner/result/" + fivo.getIndex() + "/" + fivo.getCheck_count()).equals(requestUri) || 
						(ssi.getPath() + "inner/api/result/" + fivo.getIndex() ).equals(requestUri) || 
						(ssi.getPath() + "inner/api/result/" + fivo.getScale() + "/" + fivo.getIndex() + "/" + fivo.getCheck_count() ).equals(requestUri))) ) {	//	자기 농장인 경우
					//response.sendRedirect(ssi.getPath() + "inner/result/" + fivo.getIndex() + "/" + fivo.getCheck_count());
					return true;
				}
				else	// 정보가 둘 다 없는 경우
					response.sendRedirect(ssi.getPath());
				// 홈페이지로 redirect
				
				

				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 널이 아니면 정상적으로 컨트롤러 호출(관리자임)
		return true;
	}
}
