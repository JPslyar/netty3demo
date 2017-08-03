package com.xuanwu.netty3.demo4.client;

import java.net.Socket;
import java.nio.ByteBuffer;

public class ClientMain {

	public static void main(String[] args) throws Exception {
		Socket socket = new Socket("localhost", 20000);
		
		String message = "123213";
		// length + data
		ByteBuffer buffer = ByteBuffer.allocate(4 + message.length());
		buffer.putInt(message.length());
		buffer.put(message.getBytes());
		System.out.println(message.getBytes().length);
		
		for (int i = 0; i < 2; i++) {
			
			socket.getOutputStream().write(buffer.array());
		}
		
		socket.close();
	}

}
