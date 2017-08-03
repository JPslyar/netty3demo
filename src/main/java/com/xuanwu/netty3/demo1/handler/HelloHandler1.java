package com.xuanwu.netty3.demo1.handler;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ChildChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.channel.WriteCompletionEvent;
import org.jboss.netty.handler.timeout.IdleState;
import org.jboss.netty.handler.timeout.IdleStateEvent;

public class HelloHandler1 extends SimpleChannelHandler {

	@Override
	public void channelOpen(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		System.out.println("(server-handler1)channelOpen");
		super.channelOpen(ctx, e);
	}

	@Override
	public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		System.out.println("(server-handler1)channelClosed");
		super.channelClosed(ctx, e);
	}

	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		System.out.println("(server-handler1)channelConnected");
		super.channelConnected(ctx, e);
	}

	@Override
	public void channelDisconnected(ChannelHandlerContext ctx,
			ChannelStateEvent e) throws Exception {
		System.out.println("(server-handler1)channelDisconnected");
		super.channelDisconnected(ctx, e);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e)
			throws Exception {
		System.out.println("(server-handler1)exceptionCaught");
		super.exceptionCaught(ctx, e);
	}

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		System.out.println("(server-handler1)messageReceived");
		// 解析ChannelBuffer中的内容，可以交给Netty提供的StringDecoder
//		ChannelBuffer message = (ChannelBuffer) e.getMessage();
//		String msg = new String(message.array());
		String msg = (String) e.getMessage();
		
		System.out.println("接收到的信息1：" + msg);
		
		// 回写
		// 字符串回写同样可以交给Netty提供的StringEncoder来处理
		//ChannelBuffer copiedBuffer = ChannelBuffers.copiedBuffer("hi".getBytes());
		ctx.getChannel().write("hi1");
		super.messageReceived(ctx, e);//将事件传到下一个handler
	}
	
	@Override
	public void writeComplete(ChannelHandlerContext ctx, WriteCompletionEvent e) throws Exception {
		System.out.println("(server-handler1)writeComplete");
		super.writeComplete(ctx, e);
	}
	
	@Override
	public void writeRequested(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
		System.out.println("(server-handler1)writeRequested");
		super.writeRequested(ctx, e);
	}
	
	@Override
	public void handleUpstream(ChannelHandlerContext ctx, ChannelEvent e)
			throws Exception {
		System.out.println("(server-handler1)handleUpstream");
		super.handleUpstream(ctx, e);
	}
	
	@Override
	public void handleDownstream(ChannelHandlerContext ctx, ChannelEvent e) throws Exception {
		System.out.println("(server-handler1)handleDownstream");
		super.handleDownstream(ctx, e);
	}
}
