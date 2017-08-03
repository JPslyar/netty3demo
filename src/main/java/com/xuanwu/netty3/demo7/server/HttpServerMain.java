package com.xuanwu.netty3.demo7.server;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.http.HttpRequestDecoder;
import org.jboss.netty.handler.codec.http.HttpResponseEncoder;

import com.xuanwu.netty3.demo7.handler.HttpServerHandler;

public class HttpServerMain {

	public static void main(String[] args) {
		ServerBootstrap bootstrap = new ServerBootstrap();
		
		ExecutorService boss = Executors.newCachedThreadPool();
		ExecutorService worker = Executors.newCachedThreadPool();
		
		bootstrap.setFactory(new NioServerSocketChannelFactory(boss, worker));
		
		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			
			public ChannelPipeline getPipeline() throws Exception {
				ChannelPipeline pipeline = Channels.pipeline();
				
				pipeline.addLast("decoder", new HttpRequestDecoder());
				pipeline.addLast("encoder", new HttpResponseEncoder());
				pipeline.addLast("handler", new HttpServerHandler());
				
				return pipeline;
			}
		});
		
		bootstrap.bind(new InetSocketAddress(8080));
		
		System.out.println("started!");
	}

}
