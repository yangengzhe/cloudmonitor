package com.beiyanght.csms.common.helper;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * 保存文件弹出框
 * 
 * @author 王吉文
 */
public class SaveChooser extends JFileChooser {

	/*-------------------类成员定义--------------------*/
	private static final long serialVersionUID = 1L;

	/*-------------------构造方法--------------------*/
	public SaveChooser() {
		super();
		this.setAcceptAllFileFilterUsed(false);
		this.setFileFilter(new FileNameExtensionFilter("JPG(*.jpg)", "jpg"));
		this.setFileFilter(new FileNameExtensionFilter("PNG(*.png)", "png"));
		this.setFileFilter(new FileNameExtensionFilter("GIF(*.gif)", "gif"));
		this.setFileFilter(new FileNameExtensionFilter("BMP(*.bmp)", "bmp"));
	}

	/*-------------------业务逻辑--------------------*/
	@Override
	public void approveSelection() {
		File file = this.getSelectedFile();
		if (file.exists()) {
			int copy = JOptionPane.showConfirmDialog(null, "是否要覆盖当前文件？", "保存",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (copy == JOptionPane.YES_OPTION)
				super.approveSelection();
		} else
			super.approveSelection();
	}
	/*-------------------GET&SET--------------------*/
}
