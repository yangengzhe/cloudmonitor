package com.beiyanght.csms.common.utils;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class ClipboardUtil {
	//获取剪切板数据
	public static String getClipboard() {Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
	Transferable clipT = clip.getContents(null);
	String transferData =null;
	  if (clipT != null) {
	try {
		transferData = (String) clipT.getTransferData(DataFlavor.stringFlavor);
	} catch (UnsupportedFlavorException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  }
		return transferData;
	}
}
