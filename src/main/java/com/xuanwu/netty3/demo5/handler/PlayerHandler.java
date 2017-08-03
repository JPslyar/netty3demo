package com.xuanwu.netty3.demo5.handler;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

import com.xuanwu.netty3.demo5.protobuf.entity.Player;
import com.xuanwu.netty3.demo5.protobuf.entity.Player.PBPlayer;
import com.xuanwu.netty3.demo5.protobuf.entity.Player.PBPlayer.Builder;

public class PlayerHandler extends SimpleChannelHandler {
	private int count = 0;
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
		Player.PBPlayer player = (PBPlayer) e.getMessage();
		System.out.println("server("+ ++count +")--"+player.getName()+"-------------------------------");
//		// 回写
		Builder builder = Player.PBPlayer.newBuilder();
		builder.setPlayId(1).setAge(2).setName("tomcat("+ count +")").addSkills("netty");
		PBPlayer player2 = builder.build();
		ctx.getChannel().write(player2);
	}

}
