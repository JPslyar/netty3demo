package com.xuanwu.netty3.demo5.handler;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

import com.xuanwu.netty3.demo5.protobuf.entity.Player;
import com.xuanwu.netty3.demo5.protobuf.entity.Player.PBPlayer;

public class PlayerClientHandler extends SimpleChannelHandler {
    private int count = 0;
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
		Player.PBPlayer player = (PBPlayer) e.getMessage();
		System.out.println("client("+ ++count +")--"+player.getName()+"-------------------------------");
	}

}
