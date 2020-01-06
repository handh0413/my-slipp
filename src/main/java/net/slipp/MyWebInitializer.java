package net.slipp;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class MyWebInitializer extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// 기존에 초기화를 담당했던 클래스를 등록한다.
		return builder.sources(MySlippApplication.class);
	}
}
