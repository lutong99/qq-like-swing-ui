package com.e59.service.impl;

import java.util.Vector;

import com.e59.beans.FileCustom;
import com.e59.beans.QQ;
import com.e59.dao.QQCloudDAO;
import com.e59.dao.impl.QQCloudDAOImpl;
import com.e59.service.QQCloudService;

/**
 * QQCloudService 的实现类, 主要调用我们的QQDAO层
 *
 * @author  Lutong99
 *
 */
public class QQCloudServiceImpl implements QQCloudService {
	/**
	 * 关联我们的DAO层
	 */
	private QQCloudDAO qqCloudDAO = new QQCloudDAOImpl();

	@Override
	public FileCustom uploadFiles(FileCustom fileUpload, QQ qq) {

		FileCustom uploadFiles = qqCloudDAO.uploadFiles(fileUpload, qq);
		return uploadFiles;

	}

	@Override
	public Vector<Vector<String>> getDatas(QQ qq) {
		Vector<Vector<String>> datas = qqCloudDAO.getDatas(qq);
		return datas;
	}

	@Override
	public FileCustom downloadFiles(FileCustom downloadFile, QQ qq) {
		FileCustom downloadFiles = qqCloudDAO.downloadFiles(downloadFile, qq);
		return downloadFiles;
	}

	@Override
	public boolean deleteFiles(FileCustom deleteFile, QQ qq) {
		boolean deleteFiles = qqCloudDAO.deleteFiles(deleteFile, qq);
		return deleteFiles;
	}

}
