package com.xuanwu.netty3.demo8.client;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ConnectionlessBootstrap;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioDatagramChannelFactory;

import com.xuanwu.netty3.demo8.handler.UDPClientHandler;

public class UDPClient {

	public static void main(String[] args) {
		ConnectionlessBootstrap bootstrap = new ConnectionlessBootstrap();
		
		bootstrap.setFactory(new NioDatagramChannelFactory(Executors.newCachedThreadPool()));
		
		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			
			public ChannelPipeline getPipeline() throws Exception {
				ChannelPipeline pipeline = Channels.pipeline();
		        pipeline.addLast("handler", new UDPClientHandler());
		        return pipeline;
			}
		});
		
		ChannelFuture connect = bootstrap.connect(new InetSocketAddress("localhost", 20000));
		
		ChannelBuffer buffer= ChannelBuffers.dynamicBuffer();
        buffer.writeBytes("hello".getBytes());
		connect.getChannel().write(buffer);
	}

}
