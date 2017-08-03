package com.xuanwu.netty3.demo3.client;

import java.net.Socket;

public class ClientMain {

	public static void main(String[] args) throws Exception {
		Socket socket = new Socket("localhost", 20000);
		for (int i = 0; i < 1000; i++) {
			socket.getOutputStream().write("helloQ你好Q".getBytes());
//			socket.getOutputStream().write("hello\n".getBytes());
		}
		socket.getOutputStream().flush();
		socket.close();
	}

}
