package com.xuanwu.netty3.demo4.server;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.frame.LengthFieldBasedFrameDecoder;
import org.jboss.netty.handler.codec.string.StringDecoder;

import com.xuanwu.netty3.demo4.handler.MyLengthHandler;

public class LengthServer {

	public static void main(String[] args) {
		ServerBootstrap bootstrap = new ServerBootstrap();
		
		ExecutorService boss = Executors.newCachedThreadPool();
		ExecutorService worker = Executors.newCachedThreadPool();
		
		bootstrap.setFactory(new NioServerSocketChannelFactory(boss, worker));
		
		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			
			public ChannelPipeline getPipeline() throws Exception {
				ChannelPipeline pipeline = Channels.pipeline();
//				pipeline.addLast("frameDecoder", new MyLengthBasedFrameDecoder());
				pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
				pipeline.addLast("stringDecoder", new StringDecoder());
				pipeline.addLast("myhandler1", new MyLengthHandler());
				return pipeline;
			}
		});
		
		bootstrap.bind(new InetSocketAddress(20000));
		System.out.println("started! ");
	}

}
