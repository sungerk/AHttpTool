package org.sunger.lib.http.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class IOUtils {
	/**
	 * 读取流转换成字节
	 * 
	 * @param inStream
	 * @return
	 * @throws IOException
	 */
	public static final byte[] inputStreamToByte(InputStream inStream) {
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		int nRead;
		byte[] data = new byte[1024 * 4];
		try {
			while ((nRead = inStream.read(data, 0, data.length)) != -1) {
				buffer.write(data, 0, nRead);
			}
			buffer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buffer.toByteArray();
	}

	/**
	 * 字节转换成流
	 * 
	 * @param buf
	 * @return
	 */
	public static final InputStream byteToInputStream(byte[] buf) {
		return new ByteArrayInputStream(buf);
	}

}
