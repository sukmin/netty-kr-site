package kr.netty.site;

import kr.netty.site.config.AppConfig;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class Main {

	public static void main(String[] args) {
		AbstractApplicationContext springContext = null;
		try {
			springContext = new AnnotationConfigApplicationContext(AppConfig.class);
			springContext.registerShutdownHook();

			HttpServer appServer = springContext.getBean(HttpServer.class);
			appServer.start();
		} finally {
			springContext.close();
		}
	}
}
