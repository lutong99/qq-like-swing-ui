package org.example.service.impl;

import org.example.beans.FileCustom;
import org.example.beans.QQ;
import org.example.dao.QQCloudDAO;
import org.example.dao.impl.QQCloudDAOImpl;
import org.example.service.QQCloudService;

import java.util.Vector;

/**
 * QQCloudService 的实现类, 主要调用我们的QQDAO层
 *
 * @author Lutong99
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
