package com.xuanwu.netty3.demo5.server;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.frame.LengthFieldBasedFrameDecoder;
import org.jboss.netty.handler.codec.frame.LengthFieldPrepender;
import org.jboss.netty.handler.codec.protobuf.ProtobufDecoder;
import org.jboss.netty.handler.codec.protobuf.ProtobufEncoder;

import com.xuanwu.netty3.demo5.handler.PlayerDecoder;
import com.xuanwu.netty3.demo5.handler.PlayerEncoder;
import com.xuanwu.netty3.demo5.handler.PlayerHandler;
import com.xuanwu.netty3.demo5.protobuf.entity.Player;

public class PlayerServerMain {

	public static void main(String[] args) {
		ServerBootstrap bootstrap = new ServerBootstrap();

		ExecutorService boss = Executors.newCachedThreadPool();
		ExecutorService worker = Executors.newCachedThreadPool();

		bootstrap.setFactory(new NioServerSocketChannelFactory(boss, worker));

		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {

			public ChannelPipeline getPipeline() throws Exception {
				ChannelPipeline pipeline = Channels.pipeline();

//				pipeline.addLast("decoder", new PlayerDecoder());
//			    pipeline.addLast("encoder", new PlayerEncoder());
				pipeline.addLast("decoder", new LengthFieldBasedFrameDecoder( Integer.MAX_VALUE, 0, 4, 0, 4));
				pipeline.addLast("protobufDecoder", new ProtobufDecoder( Player.PBPlayer.getDefaultInstance()));

				pipeline.addLast("encoder", new LengthFieldPrepender(4));
				pipeline.addLast("protobufEncoder", new ProtobufEncoder());
				pipeline.addLast("handler", new PlayerHandler());
				return pipeline;
			}
		});

		bootstrap.bind(new InetSocketAddress(20000));
		System.out.println("服务端已经启动！");
	}

}
