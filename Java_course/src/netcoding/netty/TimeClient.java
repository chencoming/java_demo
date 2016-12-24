package netcoding.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 *
 * @author coming  2016-7-23
 */
public class TimeClient {
	
	public void connect(String host, int port) throws Exception{
		//���ÿͻ���NIO�߳���
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(group).channel(NioSocketChannel.class)
				.option(ChannelOption.TCP_NODELAY, true)
				.handler(new ChannelInitializer<SocketChannel>() {
					public void initChannel(SocketChannel channel) throws Exception{
						channel.pipeline().addLast(new TimeClientHandler());
					}
				});
			
			//�����첽���Ӳ���
			ChannelFuture f = b.connect(host, port).sync();
			
			//�ȴ��ͻ�����·�ر�
			f.channel().closeFuture().sync();
		} finally {
			group.shutdownGracefully();
		}
	}

	/**
	 * @param args
	 * @return void
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		new TimeClient().connect("127.0.0.1", 8080);
	}

}
