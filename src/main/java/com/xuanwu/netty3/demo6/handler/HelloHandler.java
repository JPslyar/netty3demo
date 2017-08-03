package com.xuanwu.netty3.demo6.handler;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.handler.timeout.IdleState;
import org.jboss.netty.handler.timeout.IdleStateEvent;

public class HelloHandler extends SimpleChannelHandler {
	

	@Override
	public void handleUpstream(ChannelHandlerContext ctx, ChannelEvent e)
			throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("ss");
		if (e instanceof IdleStateEvent) {
			IdleStateEvent event = (IdleStateEvent) e;
			System.out.println(event.getState() + " " + sdf.format(new Date()));
			if (event.getState() == IdleState.ALL_IDLE) { 
				ctx.getChannel().close();
			}
		} else {
			super.handleUpstream(ctx, e);
		}
	}

	@Override
	public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		System.out.println("channelClosed");
		super.channelClosed(ctx, e);
	}

	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		System.out.println("channelConnected");
		super.channelConnected(ctx, e);
	}

	@Override
	public void channelDisconnected(ChannelHandlerContext ctx,
			ChannelStateEvent e) throws Exception {
		System.out.println("channelDisconnected");
		super.channelDisconnected(ctx, e);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e)
			throws Exception {
		System.out.println("exceptionCaught");
		super.exceptionCaught(ctx, e);
	}

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		System.out.println("messageReceived");
		// 解析ChannelBuffer中的内容，可以交给Netty提供的StringDecoder
//		ChannelBuffer message = (ChannelBuffer) e.getMessage();
//		String msg = new String(message.array());
		String msg = (String) e.getMessage();
		
		System.out.println("接收到的信息：" + msg);
	}

}
