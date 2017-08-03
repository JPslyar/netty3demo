package com.xuanwu.netty3.demo1.handler;

import org.jboss.netty.channel.ChannelDownstreamHandler;
import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelHandlerContext;

public class MyChannelDownstreamHandler1 implements ChannelDownstreamHandler {

	public void handleDownstream(ChannelHandlerContext ctx, ChannelEvent e) throws Exception {
		System.out.println("MyChannelDownstreamHandler");
		ctx.sendDownstream(e);
	}

}
