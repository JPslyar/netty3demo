package com.xuanwu.netty3.demo5.handler;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.FrameDecoder;

import com.xuanwu.netty3.demo5.protobuf.entity.Player;
import com.xuanwu.netty3.demo5.protobuf.entity.Player.PBPlayer;

public class PlayerDecoder extends FrameDecoder {
	private static final int BASE_LENGTH = 4;
	private int count = 0;
	@Override
	protected Object decode(ChannelHandlerContext ctx, Channel channel, ChannelBuffer buffer) throws Exception {
		// 数据包格式：数据长度（int）+ 数据（byte[]）
		System.out.println("decode("+ ++count +")......  buffer.readableBytes() "+buffer.readableBytes());
		if (BASE_LENGTH <= buffer.readableBytes()) {
			buffer.markReaderIndex();
			
			int length = buffer.readInt();
			if (buffer.readableBytes() < length) {
				buffer.resetReaderIndex();
				return null;
			}
			
			byte[] data = new byte[length];
			buffer.readBytes(data);
			
			PBPlayer player = Player.PBPlayer.parseFrom(data);
			return player;
		}
		
		return null;
	}

}
