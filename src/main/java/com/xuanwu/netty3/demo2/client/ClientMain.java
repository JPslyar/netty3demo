package com.xuanwu.netty3.demo2.client;

import java.net.Socket;

public class ClientMain {

	public static void main(String[] args) throws Exception {
		Socket socket = new Socket("localhost", 20000);
		for (int i = 0; i < 1000; i++) {
			socket.getOutputStream().write("hello".getBytes());
		}
		
		socket.close();
	}

}
