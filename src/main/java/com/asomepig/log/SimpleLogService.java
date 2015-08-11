/**
 * 
 */
package com.asomepig.log;

/**
 * @author Eric Chen asomepig@gmail.com 
 * 固定格式日志工具类.
 */
public interface SimpleLogService {

	/**
	 * 追加文本到日志文件
	 * @param content
	 */
	public void append(String content);
	/**
	 * 关闭所有的stream工具
	 */
	public void close();
}
