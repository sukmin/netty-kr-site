package kr.netty.site.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@ComponentScan("kr.netty.site")
@PropertySource("classpath:server.properties")
public class AppConfig {
	
	@Value("${boss.thread.count}")
	private int bossThreadCount;

	@Value("${worker.thread.count}")
	private int workerThreadCount;

	@Value("${tcp.port}")
	private int tcpPort;

	@Bean(name = "bossThreadCount")
	public int getBossThreadCount() {
		return bossThreadCount;
	}

	@Bean(name = "workerThreadCount")
	public int getWorkerThreadCount() {
		return workerThreadCount;
	}

	@Bean(name = "tcpPort")
	public int getTcpPort() {
		return tcpPort;
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
