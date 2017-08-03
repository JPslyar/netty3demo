package com.xuanwu.netty3.demo3.handler;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

public class MyHandler1 extends SimpleChannelHandler {
	private int count = 1;
	
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		ChannelBuffer buffer = (ChannelBuffer) e.getMessage();
		byte[] bytes = buffer.array();
		System.out.println(new String(bytes) + " " + count++);
	}

}
