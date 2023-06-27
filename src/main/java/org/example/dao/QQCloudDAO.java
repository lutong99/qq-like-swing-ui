package org.example.dao;

import org.example.beans.FileCustom;
import org.example.beans.QQ;

import java.util.Vector;

public interface QQCloudDAO extends QQDAO {

    /**
     * 上传文件的方法
     *
     * @param fileUpload 上传的自定义文件类的文件
     * @param qq         微云的qq用户
     * @return 我们上传的自定义文件类
     */
     FileCustom uploadFiles(FileCustom fileUpload, QQ qq);

    /**
     * 获取所有的数据, 从数据库中
     *
     * @param qq qq用户
     * @return 所有数据的Vector
     */
     Vector<Vector<String>> getDatas(QQ qq);

    /**
     * 下载文件的方法
     *
     * @param downloadFiles 需要下载的文件
     * @param qq            用户
     * @return 下载的自定义文件类
     */
     FileCustom downloadFiles(FileCustom downloadFiles, QQ qq);

    /**
     * 删除文件的方法
     *
     * @param deleteFiles 删除的文件
     * @param qq          qq用户
     * @return {@code true} 删除成功 {@code false} 删除失败
     */
     boolean deleteFiles(FileCustom deleteFiles, QQ qq);

}
