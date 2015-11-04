package kr.netty.site;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class HttpServer {
	
	@Autowired
	@Qualifier("bossThreadCount")
	private int bossThreadCount;

	@Autowired
	@Qualifier("workerThreadCount")
	private int workerThreadCount;

	@Autowired
	@Qualifier("tcpPort")
	private int tcpPort;

	public void start(){
		// Configure the server.
        EventLoopGroup bossGroup = new NioEventLoopGroup(bossThreadCount);
        EventLoopGroup workerGroup = new NioEventLoopGroup(workerThreadCount);
        
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.option(ChannelOption.SO_BACKLOG, 1024);
            b.group(bossGroup, workerGroup)
             .channel(NioServerSocketChannel.class)
             .handler(new LoggingHandler(LogLevel.INFO))
             .childHandler(new HttpServerInitializer());

            Channel ch = b.bind(tcpPort).sync().channel();

            System.err.println("Open your web browser and navigate to http" +
                    "://127.0.0.1:" + tcpPort + '/');

            ch.closeFuture().sync();
        } catch (InterruptedException e) {
			System.out.println("error");
		} finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
	}
	
}
