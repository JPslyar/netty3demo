package com.xuanwu.netty3.demo8.server;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ConnectionlessBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioDatagramChannelFactory;

import com.xuanwu.netty3.demo8.handler.UDPServerHandler;

public class UDPServer {

	public static void main(String[] args) {
		ConnectionlessBootstrap bootstrap = new ConnectionlessBootstrap();
		
		bootstrap.setFactory(new NioDatagramChannelFactory(Executors.newCachedThreadPool()));
		
		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			
			public ChannelPipeline getPipeline() throws Exception {
				ChannelPipeline pipeline = Channels.pipeline();
		        pipeline.addLast("handler", new UDPServerHandler());
		        return pipeline;
			}
		});
		
		bootstrap.bind(new InetSocketAddress(20000));
		System.out.println("started!");
	}

}
