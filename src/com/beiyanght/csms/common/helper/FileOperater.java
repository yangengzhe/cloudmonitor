package com.beiyanght.csms.common.helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * 文件操作类
 *
 * @author  王吉文
 */
public class FileOperater {
	/*----------------------类常量定义-------------------------*/

	
	/*----------------------业务逻辑-------------------------*/
    /**
     * 文件复制方法
     * @throws IOException 
     */
    public static void copyFile(File source, File out) throws IOException {
    	FileInputStream inFile = null;
    	FileOutputStream outFile = null;
        try {
            inFile = new FileInputStream(source);
            outFile = new FileOutputStream(out);
            byte[] buffer = new byte[1024];
            int i = 0;
            while ((i = inFile.read(buffer)) != -1) {
                outFile.write(buffer, 0, i);
            }
        } catch (IOException e) {
        	throw e;
        } finally {
			try {
				if (outFile != null)
					outFile.close();
        	}catch(IOException ex) {
        		throw ex;
        	}finally {
        		if(inFile != null)
            		inFile.close();
        	}
        }
    }

    /**
     * 文件打开方法
     * @throws IOException 
     */
    public static void openFile(File file) throws IOException {
		Runtime r = Runtime.getRuntime();
		String name = file.getAbsolutePath().replace(" ", "%20");
		r.exec("cmd /c start " + "file:///" + name);
    }

    /**
     * 得到一个文件的内容，以字符串的形式返回
     * @throws IOException 
     */
    public static String readText(File file) throws IOException {
        StringBuilder content = new StringBuilder();
        BufferedReader reader = null;
        try {
            InputStreamReader pr = new InputStreamReader(new FileInputStream(file), "UTF-8");
            reader = new BufferedReader(pr);
            String temp = null;
            while ((temp = reader.readLine()) != null) {
                content.append(temp);
            }
        } catch (IOException e) {
        	throw e;
        } finally {
        	if(reader != null) {
        		reader.close();
        	}
        }
        return content.toString();
    }

	/**
	 * 将字符串写到一个文件中去，实际上是删除这个文件重新创建，然后写内容
	 * 
	 * @throws IOException
	 */
	public static void writeText(File file, String text) throws IOException {
		OutputStreamWriter pw = null;
		try {
			pw = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
			pw.append(text);
		} catch (IOException e) {
			throw e;
		} finally {
			if (pw != null)
				pw.close();
		}
	}
	
	/** 
	 *  根据路径删除指定的目录或文件，无论存在与否 
	 *@param sPath  要删除的目录或文件 
	 *@return 删除成功返回 true，否则返回 false。 
	 */  
	public static boolean DeleteFolder(String sPath) {  
	    Boolean flag = false;  
	    File file = new File(sPath);  
	    // 判断目录或文件是否存在  
	    if (!file.exists()) {  // 不存在返回 false  
	        return flag;  
	    } else {  
	        // 判断是否为文件  
	        if (file.isFile()) {  // 为文件时调用删除文件方法  
	            return deleteFile(sPath);  
	        } else {  // 为目录时调用删除目录方法  
	            return deleteDirectory(sPath);  
	        }  
	    }  
	}
	
	/** 
	 * 删除单个文件 
	 * @param   sPath    被删除文件的文件名 
	 * @return 单个文件删除成功返回true，否则返回false 
	 */  
	public static boolean deleteFile(String sPath) {  
		Boolean flag = false;  
		File file = new File(sPath);  
	    // 路径为文件且不为空则进行删除  
	    if (file.isFile() && file.exists()) {  
	        file.delete();  
	        flag = true;  
	    }  
	    return flag;  
	}
	
	/** 
	 * 删除目录（文件夹）以及目录下的文件 
	 * @param   sPath 被删除目录的文件路径 
	 * @return  目录删除成功返回true，否则返回false 
	 */  
	public static boolean deleteDirectory(String sPath) {  
	    //如果sPath不以文件分隔符结尾，自动添加文件分隔符  
	    if (!sPath.endsWith(File.separator)) {  
	        sPath = sPath + File.separator;  
	    }  
	    File dirFile = new File(sPath);  
	    //如果dir对应的文件不存在，或者不是一个目录，则退出  
	    if (!dirFile.exists() || !dirFile.isDirectory()) {  
	        return false;  
	    }  
	    Boolean flag = true;  
	    //删除文件夹下的所有文件(包括子目录)  
	    File[] files = dirFile.listFiles();  
	    for (int i = 0; i < files.length; i++) {  
	        //删除子文件  
	        if (files[i].isFile()) {  
	            flag = deleteFile(files[i].getAbsolutePath());  
	            if (!flag) break;  
	        } //删除子目录  
	        else {  
	            flag = deleteDirectory(files[i].getAbsolutePath());  
	            if (!flag) break;  
	        }  
	    }  
	    if (!flag) return false;  
	    //删除当前目录  
	    if (dirFile.delete()) {  
	        return true;  
	    } else {  
	        return false;  
	    }  
	}
}
