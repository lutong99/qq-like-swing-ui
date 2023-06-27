package org.example.service;

import org.example.beans.FileCustom;
import org.example.beans.QQ;

import java.util.Vector;

/**
 * QQ微云的服务层
 *
 * @author Lutong99
 */
public interface QQCloudService extends QQService {

    /**
     * 上传文件的服务
     *
     * @param fileUpload 上传的我们的自定义文件对象
     * @param qq         微云的qq用户
     * @return 上传的文件对象
     */
    public abstract FileCustom uploadFiles(FileCustom fileUpload, QQ qq);

    /**
     * 获取所有的已经上传的数据的服务
     *
     * @param qq 微云的qq用户
     * @return 所有的信息的Vector
     */
    public abstract Vector<Vector<String>> getDatas(QQ qq);

    /**
     * 下载文件的服务
     *
     * @param downLoadFiles 下载的自定义文件
     * @param qq            微云的qq用户
     * @return 我们的自定义文件
     */
    public abstract FileCustom downloadFiles(FileCustom downLoadFiles, QQ qq);

    /**
     * 删除云文件的服务
     *
     * @param deleteFile 要删除的自定义文件
     * @param qq         微云用户
     * @return {@code true} 删除成功, {@code false} 删除失败
     */
    public abstract boolean deleteFiles(FileCustom deleteFile, QQ qq);

}
