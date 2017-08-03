package com.xuanwu.netty3.demo5.client;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.codec.frame.LengthFieldBasedFrameDecoder;
import org.jboss.netty.handler.codec.frame.LengthFieldPrepender;
import org.jboss.netty.handler.codec.protobuf.ProtobufDecoder;
import org.jboss.netty.handler.codec.protobuf.ProtobufEncoder;

import com.xuanwu.netty3.demo5.handler.PlayerClientHandler;
import com.xuanwu.netty3.demo5.handler.PlayerDecoder;
import com.xuanwu.netty3.demo5.handler.PlayerEncoder;
import com.xuanwu.netty3.demo5.protobuf.entity.Player;
import com.xuanwu.netty3.demo5.protobuf.entity.Player.PBPlayer;
import com.xuanwu.netty3.demo5.protobuf.entity.Player.PBPlayer.Builder;

public class PlayerClientMain {

	public static void main(String[] args) throws InterruptedException {
		ClientBootstrap bootstrap = new ClientBootstrap();
		
		ExecutorService boss =Executors.newCachedThreadPool();
		ExecutorService worker =Executors.newCachedThreadPool();
		
		bootstrap.setFactory(new NioClientSocketChannelFactory(boss, worker));
		
		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			
			public ChannelPipeline getPipeline() throws Exception {
				ChannelPipeline pipeline = Channels.pipeline();
//				pipeline.addLast("encoder", new PlayerEncoder());
//				pipeline.addLast("decoder", new PlayerDecoder());
				pipeline.addLast("decoder", new LengthFieldBasedFrameDecoder( Integer.MAX_VALUE, 0, 4, 0, 4));
				pipeline.addLast("protobufDecoder", new ProtobufDecoder( Player.PBPlayer.getDefaultInstance()));

				pipeline.addLast("encoder", new LengthFieldPrepender(4));
				pipeline.addLast("protobufEncoder", new ProtobufEncoder());
				pipeline.addLast("handler", new PlayerClientHandler());
				return pipeline;
			}
		});
		
		ChannelFuture future = bootstrap.connect(new InetSocketAddress("localhost", 20000));
		Builder builder = Player.PBPlayer.newBuilder();
		for(int i=0;i<10000;i++){
			builder.setPlayId(100).setAge(30).setName("sam("+ (i+1) +")").addSkills("java");
			PBPlayer player = builder.build();
			future.getChannel().write(player);
		}
	}

}
