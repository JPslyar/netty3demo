package com.xuanwu.netty3.demo3.server;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.frame.LineBasedFrameDecoder;

import com.xuanwu.netty3.demo3.handler.MyHandler1;
import com.xuanwu.netty3.demo3.handler.MyLineBasedFrameDecoder;

public class DelimiterServer {

	public static void main(String[] args) {
		ServerBootstrap bootstrap = new ServerBootstrap();
		
		ExecutorService boss = Executors.newCachedThreadPool();
		ExecutorService worker = Executors.newCachedThreadPool();
		
		bootstrap.setFactory(new NioServerSocketChannelFactory(boss, worker));
		
		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			
			public ChannelPipeline getPipeline() throws Exception {
				ChannelPipeline pipeline = Channels.pipeline();
//				pipeline.addLast("frameDecoder", new MyLineBasedFrameDecoder());
				pipeline.addLast("frameDecoder", new MyLineBasedFrameDecoder());
				pipeline.addLast("myhandler1", new MyHandler1());
				return pipeline;
			}
		});
		
		bootstrap.bind(new InetSocketAddress("localhost", 20000));
		System.out.println("started! ");
	}

}
