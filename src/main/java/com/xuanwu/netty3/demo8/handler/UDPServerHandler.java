package com.xuanwu.netty3.demo8.handler;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.buffer.DynamicChannelBuffer;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

public class UDPServerHandler extends SimpleChannelHandler {

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		ChannelBuffer buffer = (ChannelBuffer)e.getMessage();
        byte[] recByte=buffer.copy().toByteBuffer().array();
        String msg=new String(recByte);
        System.out.println("from client:"+msg);
        
        ChannelBuffer responseBuffer= ChannelBuffers.dynamicBuffer();
        responseBuffer.writeBytes("OK".getBytes());
        e.getChannel().write(responseBuffer,e.getRemoteAddress());
	}
	
}
