package com.xuanwu.netty3.demo5.handler;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

import com.xuanwu.netty3.demo5.protobuf.entity.Player;
import com.xuanwu.netty3.demo5.protobuf.entity.Player.PBPlayer;

public class PlayerEncoder extends OneToOneEncoder {
	private int count = 0;
	@Override
	protected Object encode(ChannelHandlerContext ctx, Channel channel, Object msg) throws Exception {
		System.out.println("encode("+ ++count +")......");
		Player.PBPlayer player = (PBPlayer) msg;
		
		// 创建ChannelBuffer
		ChannelBuffer buffer = ChannelBuffers.dynamicBuffer();
		buffer.writeInt(player.toByteArray().length);
		buffer.writeBytes(player.toByteArray());
		
		return buffer;
	}

}
