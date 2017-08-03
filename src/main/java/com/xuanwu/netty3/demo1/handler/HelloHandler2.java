package com.xuanwu.netty3.demo1.handler;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.channel.WriteCompletionEvent;

public class HelloHandler2 extends SimpleChannelHandler {

	@Override
	public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		System.out.println("(server-handler2)channelClosed");
		super.channelClosed(ctx, e);
	}

	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		System.out.println("(server-handler2)channelConnected");
		super.channelConnected(ctx, e);
	}

	@Override
	public void channelDisconnected(ChannelHandlerContext ctx,
			ChannelStateEvent e) throws Exception {
		System.out.println("(server-handler2)channelDisconnected");
		super.channelDisconnected(ctx, e);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e)
			throws Exception {
		System.out.println("(server-handler2)exceptionCaught");
		super.exceptionCaught(ctx, e);
	}

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		System.out.println("(server-handler2)messageReceived");
		// 解析ChannelBuffer中的内容，可以交给Netty提供的StringDecoder
//		ChannelBuffer message = (ChannelBuffer) e.getMessage();
//		String msg = new String(message.array());
		String msg = (String) e.getMessage();
		
		System.out.println("接收到的信息2：" + msg);
		
		// 回写
		// 字符串回写同样可以交给Netty提供的StringEncoder来处理
		//ChannelBuffer copiedBuffer = ChannelBuffers.copiedBuffer("hi".getBytes());
		ctx.getChannel().write("hi2");
		super.messageReceived(ctx, e);
	}

	
	@Override
	public void writeComplete(ChannelHandlerContext ctx, WriteCompletionEvent e) throws Exception {
		System.out.println("(server-handler2)writeComplete");
		super.writeComplete(ctx, e);
	}
	
	@Override
	public void writeRequested(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
		System.out.println("(server-handler2)writeRequested");
		super.writeRequested(ctx, e);
	}
	
	@Override
	public void handleUpstream(ChannelHandlerContext ctx, ChannelEvent e)
			throws Exception {
		System.out.println("(server-handler2)handleUpstream");
		super.handleUpstream(ctx, e);
	}
	
	@Override
	public void handleDownstream(ChannelHandlerContext ctx, ChannelEvent e) throws Exception {
		System.out.println("(server-handler2)handleDownstream");
		super.handleDownstream(ctx, e);
	}
}
