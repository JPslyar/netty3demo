package com.xuanwu.netty3.demo1.client;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

import com.xuanwu.netty3.demo1.handler.ClientHandler;

public class TestClient {

	public static void main(String[] args) {
		ClientBootstrap bootstrap = new ClientBootstrap();
		
		// 创建线程池
		ExecutorService boss = Executors.newCachedThreadPool();
		ExecutorService worker = Executors.newCachedThreadPool();
		
		// 设置socket工厂
		bootstrap.setFactory(new NioClientSocketChannelFactory(boss, worker));
		
		// 设置管道工厂
		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			
			public ChannelPipeline getPipeline() throws Exception {
				ChannelPipeline pipeline = Channels.pipeline();
				pipeline.addLast("decoder", new StringDecoder()); // 上行解码器
				pipeline.addLast("encoder", new StringEncoder()); // 下行编码器
				pipeline.addLast("handler", new ClientHandler());
				
				return pipeline;
			}
		});
		
		// 连接服务端
		ChannelFuture connect = bootstrap.connect(new InetSocketAddress("localhost", 20000));
		
		System.out.println("客户端已经成功链接服务端！");
		
		// 获取Channel
		Channel channel = connect.getChannel();
		// 向服务端写数据
		channel.write("hello");
	}

}
