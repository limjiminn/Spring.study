package shop.awesomejump.config.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomAuthenticationProvider implements AuthenticationProvider {
				//화면에서 입력한 로그인 정보와 DB에서 가져온 사용자의 정보를 비교해주는 인터페이스
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		//화면에서 사용자가 입력한 로그인 정보를 담고 있다
		
		String username = (String) authentication.getPrincipal();
		String password = (String) authentication.getCredentials();
		log.debug("username = {}, password = {}", username, password);
		
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}
	
}