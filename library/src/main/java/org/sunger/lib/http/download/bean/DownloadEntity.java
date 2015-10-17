package org.sunger.lib.http.download.bean;

/**
 * 文件下载实体类
 * 
 * @author Administrator
 *
 */
public class DownloadEntity {
	// 线程id
	public int threadId;
	// 开始下载点
	public int startPos;
	// 结束下载位置
	public int endPos;
	// 已经完成下载大小
	public int compeleteSize;
	// 下载链接
	public String url;

	public DownloadEntity(int threadId, int startPos, int endPos,
			int compeleteSize, String url) {
		this.threadId = threadId;
		this.startPos = startPos;
		this.endPos = endPos;
		this.compeleteSize = compeleteSize;
		this.url = url;
	}
}
