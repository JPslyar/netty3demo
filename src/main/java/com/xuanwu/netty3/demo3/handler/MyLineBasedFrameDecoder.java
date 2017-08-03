package com.xuanwu.netty3.demo3.handler;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.FrameDecoder;

public class MyLineBasedFrameDecoder extends FrameDecoder {
	
	@Override
	protected Object decode(ChannelHandlerContext ctx, Channel channel,
			ChannelBuffer buffer) throws Exception {
		final int eol = findEndOfLine(buffer);
		if (eol >= 0) {
            final ChannelBuffer frame;
            final int length = eol - buffer.readerIndex();
            try {
            	frame = extractFrame(buffer, buffer.readerIndex(), length);
            } finally {
                buffer.skipBytes(length + 1);
            }
            return frame;
        }
		return null;
	}
	private static int findEndOfLine(final ChannelBuffer buffer) {
        final int n = buffer.writerIndex();
        for (int i = buffer.readerIndex(); i < n; i ++) {
            final byte b = buffer.getByte(i);
//            System.out.print(new String(new byte[]{b}));
            if (b == 'Q') {
                return i;
            } 
//            if (b == '\n') {
//            	return i;
//            } else if (b == '\r' && i < n - 1 && buffer.getByte(i + 1) == '\n') {
//            	return i;  // \r\n
//            }
        }
        return -1;  // Not found.
    }
}
