package org.sunger.lib.http.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.net.Uri;
import android.text.TextUtils;

public class UrlUtil {

	public static boolean isUrl(String url) {
		if (TextUtils.isEmpty(url))
			return false;
		String regex = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(url);
		return matcher.matches();
	}

	public static String parseUrl(String urlString) {
		return Uri.parse(urlString).buildUpon().build().toString();
	}

	public static boolean isImage(String url) {
		String reg = "(?i).+?\\.(jpg|gif|bmp|gif|jpeg|png)";
		return url.matches(reg);
	}

}
