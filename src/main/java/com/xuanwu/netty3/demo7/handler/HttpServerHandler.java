package com.xuanwu.netty3.demo7.handler;

import java.util.List;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.handler.codec.http.DefaultHttpResponse;
import org.jboss.netty.handler.codec.http.HttpMethod;
import org.jboss.netty.handler.codec.http.HttpRequest;
import org.jboss.netty.handler.codec.http.HttpResponse;
import org.jboss.netty.handler.codec.http.HttpResponseStatus;
import org.jboss.netty.handler.codec.http.HttpVersion;
import org.jboss.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import org.jboss.netty.handler.codec.http.multipart.InterfaceHttpData;
import org.jboss.netty.handler.codec.http.multipart.InterfaceHttpData.HttpDataType;

public class HttpServerHandler extends SimpleChannelHandler {
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		HttpRequest request = (HttpRequest) e.getMessage();
		String uri = request.getUri();
		if (request.getMethod() == HttpMethod.GET) {
			// GET
			System.out.println("GET");
			doGet(request);
		} else if (request.getMethod() == HttpMethod.POST) {
			// POST
			System.out.println("POST");
			doPost(request);
		}
		
		System.out.println("uri : " + uri);
		
		HttpResponse response = new DefaultHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
		ChannelBuffer content = ChannelBuffers.dynamicBuffer();
		content.writeBytes("OK!".getBytes());
		response.setContent(content);
		
		ctx.getChannel().write(response);
		ctx.getChannel().close();
	}

	private void doPost(HttpRequest request) {
		try {
			HttpPostRequestDecoder decoder = new HttpPostRequestDecoder(request);
			List<InterfaceHttpData> bodyHttpDatas = decoder.getBodyHttpDatas();
			for (InterfaceHttpData bodyHttpData : bodyHttpDatas) {
				if (bodyHttpData.getHttpDataType() == HttpDataType.Attribute) {
					System.out.println(bodyHttpData);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void doGet(HttpRequest request) {
		String uri = request.getUri();
		String[] array = uri.split("[?]");
		if (array.length > 1) {
			String parameters = array[1];
			String[] kvs = parameters.split("[&]");
			for (String kv : kvs) {
				System.out.println(kv);
			}
		}
		
	}

}
