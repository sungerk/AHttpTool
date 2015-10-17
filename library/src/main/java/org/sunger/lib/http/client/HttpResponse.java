package org.sunger.lib.http.client;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.sunger.lib.http.utils.IOUtils;
import org.sunger.lib.http.utils.UrlUtil;

import android.os.Build;
import android.text.TextUtils;

public class HttpResponse {
	private final static String CONTENT_DISPOSTION_CHAR = "attachment; filename=";
	private final static String CONTENT_DISPOSTION = "Content-Disposition";
	private final static String CONTENT_TYPE_IMAGE = "image/";
	private Map<String, List<String>> headers;
	private int contentLength;
	private String contentType;
	private int statusCode;
	private String fileName;
	private InputStream payload;

	public HttpResponse(HttpURLConnection httpURLConnection) throws Exception {
		payload = httpURLConnection.getInputStream();
		initHeaders(httpURLConnection.getHeaderFields());
		contentLength = httpURLConnection.getContentLength();
		contentType = httpURLConnection.getContentType();
		statusCode = httpURLConnection.getResponseCode();
		String file = httpURLConnection.getURL().getFile().toString();
		fileName = file.substring(file.lastIndexOf('/') + 1);
		initFileName();
	}

	private void initFileName() {
		String contentDispositionStr = getFirstHeaderValue(CONTENT_DISPOSTION);
		// Content-Disposition存在说明是文件,设置为文件中的名字
		if (!TextUtils.isEmpty(contentDispositionStr)) {
			fileName = contentDispositionStr.substring(CONTENT_DISPOSTION_CHAR
					.length());
		} else if (contentType.startsWith(CONTENT_TYPE_IMAGE)
				|| !UrlUtil.isImage(fileName)) {
			fileName += "."
					+ contentType.substring(CONTENT_TYPE_IMAGE.length());
		}
	}

	public byte[] getContent() {
		return IOUtils.inputStreamToByte(payload);
	}

	private void initHeaders(Map<String, List<String>> rawHeaders) {
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.GINGERBREAD) {
			final Map<String, List<String>> newHeaders = new HashMap<String, List<String>>(
					rawHeaders.size());
			for (final Map.Entry<String, List<String>> e : rawHeaders
					.entrySet()) {
				final String key = e.getKey();
				final int keyLen = key.length();
				final StringBuilder newKey = new StringBuilder(keyLen);
				for (int i = 0; i < keyLen; ++i) {
					final char c = key.charAt(i);
					final char c2;
					if (i == 0 || key.charAt(i - 1) == '-') {
						c2 = Character.toUpperCase(c);
					} else {
						c2 = c;
					}
					newKey.append(c2);
				}
				newHeaders.put(newKey.toString(), e.getValue());
			}
			this.headers = Collections.unmodifiableMap(newHeaders);
		} else {
			this.headers = Collections.unmodifiableMap(rawHeaders);
		}
	}

	public String getContentCharset() {
		final String contentType = getFirstHeaderValue("Content-Type");
		if (contentType == null) {
			return null;
		}
		final int i = contentType.indexOf('=');
		return i == -1 ? null : contentType.substring(i + 1).trim();
	}

	public int getStatusCode() {
		return statusCode;
	}

	public int getContentLength() {
		return contentLength;
	}

	public String getContentType() {
		return contentType;
	}

	public String getFileName() {
		return fileName;
	}

	public Map<String, List<String>> getHeaders() {
		return headers;
	}

	public String getFirstHeaderValue(String name) {
		final List<String> values = headers.get(name);
		if (values == null || values.isEmpty()) {
			return null;
		}
		return values.get(0);
	}

	public InputStream getPayload() {
		return payload;
	}

}
