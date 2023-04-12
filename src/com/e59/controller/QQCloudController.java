package com.e59.controller;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.concurrent.locks.ReentrantLock;

import com.e59.beans.FileCustom;
import com.e59.beans.QQ;
import com.e59.service.QQCloudService;
import com.e59.service.impl.QQCloudServiceImpl;

/**
 * 用来控制微云的控制器
 *
 * @author  Lutong99
 *
 */
public class QQCloudController extends QQController {
	/** 懒汉模式的单例模式 */
	private static QQCloudController qqCloudController = null;

	/** 锁 */
	private static ReentrantLock lock = new ReentrantLock();

	/** 用来使用微云的云服务 */
	private QQCloudService qqCloudService = new QQCloudServiceImpl();

	/**
	 * 空参构造器, java bean 必须
	 */
	private QQCloudController() {

	}

	/**
	 * 获取我们的懒汉单例
	 * 
	 * @return 控制器的对象
	 */
	public static QQCloudController getInstance() {
		if (qqCloudController == null) {
			// 锁, 来保证我们的懒汉安全, 基本没有什么用
			lock.lock();
			try {
				if (qqCloudController == null) {
					qqCloudController = new QQCloudController();
				}
			} finally {
				lock.unlock();
			}
		}
		return qqCloudController;
	}

	/**
	 * 上传文件的方法
	 * 
	 * @param fileChoseFile 从控制层里传过来的文件对象
	 * @param qq            我们使用qq用户, 用来关联我们的微云, 也就是微云用户
	 * @return 返回一个自定义文件, 然后加在我们的Table里面
	 */
	public FileCustom uploadFiles(File fileChoseFile, QQ qq) {
		/*
		 * private String fileName;
		 * 
		 * private String fileTime;
		 * 
		 * private String fileSize;
		 */
		
		String fileName = fileChoseFile.getName();

		String filePath = fileChoseFile.getAbsolutePath();

		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String fileTime = format.format(date);

		long totalSpace = fileChoseFile.length();

		double size = totalSpace;
		String fileSize = "";
		fileSize = size + " k";
		DecimalFormat decimalFormat = new DecimalFormat("#.00");
		if (size > 1024) {
			size = totalSpace / 1024.0;
			fileSize = decimalFormat.format(size) + " K";
			if (size > 1024 * 1024) {
				size = totalSpace / 1024.0 / 1024.0;
				fileSize = decimalFormat.format(size) + " M";

				if (size > 1024 * 1024 * 1024) {
					size = totalSpace / 1024.0 / 1024.0;
					fileSize = decimalFormat.format(size) + "G";
				}
			}
		}

		FileCustom fileUpload = new FileCustom(fileName, filePath, fileTime, fileSize);

		FileCustom uploadFiles = qqCloudService.uploadFiles(fileUpload, qq);

		return uploadFiles;

	}

	/**
	 * 获得所有的云文件, 我们就只是把这个数据中的所有文件放在JTable中
	 * @param qq 关联的微云用户
	 * @return JTable中的数据行
	 */
	public Vector<Vector<String>> getDatas(QQ qq) {
		Vector<Vector<String>> datas = qqCloudService.getDatas(qq);
		return datas;
	}

	/**
	 * 下载文件, 
	 * @param selectFile 我们在JTable中选中的文件
	 * @param qq 微云用户
	 * @return 返回我们的自定义文件
	 */
	public FileCustom downloadFiles(Vector<String> selectFile, QQ qq) {
		String fileName = selectFile.get(0);
		String fileTime = selectFile.get(1);
		String fileSize = selectFile.get(2);
		FileCustom fileUpload = new FileCustom(fileName, fileTime, fileSize);
		FileCustom downloadFiles = qqCloudService.downloadFiles(fileUpload, qq);
		return downloadFiles;

	}

	/**
	 * 删除文件
	 * @param deleteVector 删除的文件的所有的属性
	 * @param qq 绑定微云的用户
	 * @return 是否删除成功 {@code true} 就是删除成功, {@code false} 是删除失败
	 */
	public boolean deleteFiles(Vector<String> deleteVector, QQ qq) {

		String fileName = deleteVector.get(0);
		String fileTime = deleteVector.get(1);
		String fileSize = deleteVector.get(2);

		FileCustom fileUpload = new FileCustom(fileName, fileTime, fileSize);

		boolean deleteFiles = qqCloudService.deleteFiles(fileUpload, qq);
		return deleteFiles;
	}
}
