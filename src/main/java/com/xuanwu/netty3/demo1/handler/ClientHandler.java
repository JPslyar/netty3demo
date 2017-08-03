package com.xuanwu.netty3.demo1.handler;

import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.channel.WriteCompletionEvent;

public class ClientHandler extends SimpleChannelHandler {

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		System.out.println("(client)messageReceived");
		String msg = (String) e.getMessage();
		System.out.println("客户端收到服务端的信息：" + msg);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e)
			throws Exception {
		System.out.println("(client)exceptionCaught");
		super.exceptionCaught(ctx, e);
	}

	@Override
	public void channelOpen(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		System.out.println("(client)channelOpen");
		super.channelOpen(ctx, e);
	}

	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		System.out.println("(client)channelConnected");
		super.channelConnected(ctx, e);
	}

	@Override
	public void channelDisconnected(ChannelHandlerContext ctx,
			ChannelStateEvent e) throws Exception {
		System.out.println("(client)channelDisconnected");
		super.channelDisconnected(ctx, e);
	}

	@Override
	public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		System.out.println("(client)channelClosed");
		super.channelClosed(ctx, e);
	}

	@Override
	public void writeComplete(ChannelHandlerContext ctx, WriteCompletionEvent e) throws Exception {
		System.out.println("(client)writeComplete");
		super.writeComplete(ctx, e);
	}
	
	@Override
	public void writeRequested(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
		System.out.println("(client)writeRequested");
		super.writeRequested(ctx, e);
	}
	
	@Override
	public void handleUpstream(ChannelHandlerContext ctx, ChannelEvent e)
			throws Exception {
		System.out.println("(client)handleUpstream");
		super.handleUpstream(ctx, e);
	}
	
	@Override
	public void handleDownstream(ChannelHandlerContext ctx, ChannelEvent e) throws Exception {
		System.out.println("(client)handleDownstream");
		super.handleDownstream(ctx, e);
	}
}
