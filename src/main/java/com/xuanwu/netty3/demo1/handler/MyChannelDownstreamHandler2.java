package com.xuanwu.netty3.demo1.handler;

import org.jboss.netty.channel.ChannelDownstreamHandler;
import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelHandlerContext;

public class MyChannelDownstreamHandler2 implements ChannelDownstreamHandler {

	public void handleDownstream(ChannelHandlerContext ctx, ChannelEvent e) throws Exception {
		System.out.println("MyChannelDownstreamHandler2");
		ctx.sendDownstream(e);
	}

}
