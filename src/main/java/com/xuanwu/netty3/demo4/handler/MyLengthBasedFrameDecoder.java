package com.xuanwu.netty3.demo4.handler;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.FrameDecoder;

public class MyLengthBasedFrameDecoder extends FrameDecoder {
	
	@Override
	protected Object decode(ChannelHandlerContext ctx, Channel channel,
			ChannelBuffer buffer) throws Exception {
		if (buffer.readableBytes() >= 4) {
			buffer.markReaderIndex();
			
			int length = buffer.readInt();
			System.out.println(buffer.readableBytes());
			if (buffer.readableBytes() < length) {
				buffer.resetReaderIndex();
				return null;
			}
			
			byte[] data = new byte[length];
			buffer.readBytes(data);
			
			return  new String(data);
		}
		return null;
	}
}
