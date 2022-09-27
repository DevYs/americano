package devy.americano.backend.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfig {

	/**
	 * 설정에서 틀린 부분이 없지만 403 에러가 발생할 수 있다<br>
	 * 서버가 다르지만 도메인이 같고 같은 형식의 서버라면...
	 * 예를들어 같은 도메인의 서버에서 동일한 톰캣이 중복으로 실행되고 있고 로그인 기능이 있다면
	 * 모든 JAVA 웹 앱이 로그인에 성공하면 'JSESSIONID'라는 쿠키를 발행하게 된다.
	 * 로그인을 할때마다 로그인에 성공한 웹 앱의 JSESSIONID 쿠키로 교체되기 때문에 웹 앱은 인증과 권한을 잃게 된다.
	 */
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http.csrf().disable()
				.authorizeRequests()
				.anyRequest().permitAll()
				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.cors()
				.and()
				.formLogin()
				.disable()
				.build()
		;
	}

}
