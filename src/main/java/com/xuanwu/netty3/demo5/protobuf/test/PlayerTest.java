package com.xuanwu.netty3.demo5.protobuf.test;

import java.util.Arrays;

import com.xuanwu.netty3.demo5.protobuf.entity.Player;
import com.xuanwu.netty3.demo5.protobuf.entity.Player.PBPlayer;
import com.xuanwu.netty3.demo5.protobuf.entity.Player.PBPlayer.Builder;

public class PlayerTest {

	public static void main(String[] args) throws Exception {
		toPlayer(toBytes());
	}
	
	public static void toPlayer(byte[] bytes) throws Exception{
		PBPlayer player = Player.PBPlayer.parseFrom(bytes);
		System.out.println("playId : " + player.getPlayId());
		System.out.println("age : " + player.getAge());
		System.out.println("name : " + player.getName());
		System.out.println("skills : " + player.getSkillsList());
	}
	
	public static byte[] toBytes() {
		Builder builder = Player.PBPlayer.newBuilder();
		builder.setPlayId(12).setAge(30).setName("tom").addSkills("java");
		
		PBPlayer player = builder.build();
		byte[] bytes = player.toByteArray();
		System.out.println(Arrays.toString(bytes));
		
		return bytes;
	}

}
