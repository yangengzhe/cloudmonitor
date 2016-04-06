package com.ices.csp.upload.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.cspframework.utils.helper.LogHelper;
import org.cspframework.web.upload.MultipartRequestWrapper;
import org.cspframework.web.upload.UploadResponse;
import org.springframework.stereotype.Service;


@Service
public class UploadFileServiceImpl implements UploadFileService {
	@Override
	public UploadResponse uploadFile(HttpServletRequest request,String saveDir) throws Exception {
//		File f = new File(saveDir);
//		if (!f.exists())
//			f.mkdirs();
//		UploadResponse res = new UploadResponse();
//		try {
//			MultipartRequestWrapper multipartRequest = new MultipartRequestWrapper(
//					request, saveDir, permitedSize, "UTF-8");
//			File fileIn = multipartRequest.getFile("filePath");
//			res.setSuccess(true);
//			res.setResult(fileIn.getAbsolutePath());
//		} catch (Exception e) {
//			LogHelper.error(this.getClass(), "文件上传出错：" + e.getMessage());
//			res.setSuccess(false);
//			res.setResult("文件上传出错：" + e.getMessage());
//		}

		UploadResponse res = new UploadResponse();
		File file = new File(saveDir);		
        //判断上传文件的保存目录是否存在
        if (!file.exists() && !file.isDirectory()) {
            System.out.println(saveDir+"目录不存在，需要创建");
            //创建目录
            file.mkdirs();
        }
		try{
            //使用Apache文件上传组件处理文件上传步骤：
            //1、创建一个DiskFileItemFactory工厂
            DiskFileItemFactory factory = new DiskFileItemFactory();
            //2、创建一个文件上传解析器
            ServletFileUpload upload = new ServletFileUpload(factory);
             //解决上传文件名的中文乱码
            upload.setHeaderEncoding("UTF-8"); 
            //3、判断提交上来的数据是否是上传表单的数据
            if(!ServletFileUpload.isMultipartContent(request)){
                //按照传统方式获取数据
            	res.setResult("");
            }
            //4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
            List<FileItem> list = upload.parseRequest(request);
            for(FileItem item : list){
                //如果fileitem中封装的是普通输入项的数据
                if(item.isFormField()){
                    String name = item.getFieldName();
                    //解决普通输入项的数据的中文乱码问题
                    String value = item.getString("UTF-8");
                    //value = new String(value.getBytes("iso8859-1"),"UTF-8");
                    System.out.println(name + "=" + value);
                }else{//如果fileitem中封装的是上传文件
                    //得到上传的文件名称，
                    String filename = item.getName();
                    System.out.println(filename);
                    if(filename==null || filename.trim().equals("")){
                        continue;
                    }
                    //注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：  c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
                    //处理获取到的上传文件的文件名的路径部分，只保留文件名部分
                    filename = filename.substring(filename.lastIndexOf("\\")+1);
                    //获取item中的上传文件的输入流
                    InputStream in = item.getInputStream();
                    //创建一个文件输出流
                    FileOutputStream out = new FileOutputStream(saveDir + "\\" + filename);
                    //创建一个缓冲区
                    byte buffer[] = new byte[1024];
                    //判断输入流中的数据是否已经读完的标识
                    int len = 0;
                    //循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
                    while((len=in.read(buffer))>0){
                        //使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
                        out.write(buffer, 0, len);
                    }
                    //关闭输入流
                    in.close();
                    //关闭输出流
                    out.close();
                    //删除处理文件上传时生成的临时文件
                    item.delete();
                    res.setSuccess(true);
                    res.setResult("文件上传成功！");
                }
            }
        }catch (Exception e) {
        	res.setSuccess(false);
        	res.setResult("文件上传失败！");
            e.printStackTrace();          
        }
		return res;
	}
}