package com.ices.csp.upload.service;

import javax.servlet.http.HttpServletRequest;

import org.cspframework.web.upload.UploadResponse;

public interface UploadFileService {
	/**保存文件到本地磁盘
	 * @param request
	 * @param saveDir
	 * @return
	 * @throws Exception
	 */
	public abstract UploadResponse uploadFile(HttpServletRequest request, String saveDir) throws Exception;

}