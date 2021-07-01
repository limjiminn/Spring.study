package shop.awesomejump.config.security;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import lombok.extern.slf4j.Slf4j;
import shop.awesomejump.domain.CustomAdapter;
import shop.awesomejump.domain.MemberDTO;

@Slf4j
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		CustomAdapter memberAdapter = (CustomAdapter) authentication.getPrincipal();
			//AccountService의 UserDetails의 구현체가 리턴해주는 스프링 시큐리티 유저를 받아 올 수 있음
		MemberDTO member = memberAdapter.getMember();
		
		member.setMem_pw("[PROTECTED]");
		
		memberAdapter.eraseCredentials();
		log.debug("Customer 로그인에 성공하였습니다.");
		
		if (member.getMem_email() == "admin@email.com") {
			SecurityContextHolder.getContext()
			.setAuthentication(new UsernamePasswordAuthenticationToken
				(member, memberAdapter.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"))));
	
		} else {
			SecurityContextHolder.getContext()
			.setAuthentication(new UsernamePasswordAuthenticationToken
				(member, memberAdapter.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))));
		}

		response.sendRedirect("/");
		
	}

}