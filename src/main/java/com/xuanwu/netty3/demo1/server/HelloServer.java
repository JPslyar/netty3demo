package com.xuanwu.netty3.demo1.server;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

import com.xuanwu.netty3.demo1.handler.HelloHandler1;
import com.xuanwu.netty3.demo1.handler.HelloHandler2;
import com.xuanwu.netty3.demo1.handler.MyChannelDownstreamHandler1;
import com.xuanwu.netty3.demo1.handler.MyChannelDownstreamHandler2;
import com.xuanwu.netty3.demo1.handler.MyChannelDownstreamHandler3;

public class HelloServer {

	public static void main(String[] args) {
		// 创建ServerBootstrap
		ServerBootstrap bootstrap = new ServerBootstrap();
		
		// 创建线程池
		ExecutorService boss = Executors.newCachedThreadPool();
		
		ExecutorService worker = Executors.newCachedThreadPool();
		
		// 设置NIO Socket工厂
		bootstrap.setFactory(new NioServerSocketChannelFactory(boss, worker));
		
		// 创建管道工厂
		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			
			public ChannelPipeline getPipeline() throws Exception {
				// 创建管道
				ChannelPipeline pipeline = Channels.pipeline();
				//设置管道处理器，类似于过滤器
				pipeline.addLast("encoder", new StringEncoder()); // 处理下行，即Netty是发送方
				pipeline.addLast("decoder", new StringDecoder()); // 处理上行，即Netty是接收方
				pipeline.addLast("helloHandler1", new HelloHandler1());
				pipeline.addLast("helloHandler2", new HelloHandler2());
				return pipeline;
			}
		});
		// 绑定端口
		bootstrap.bind(new InetSocketAddress(20000));
		
		System.out.println("服务端已经启动！");
	}

}
