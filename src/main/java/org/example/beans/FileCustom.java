package org.example.beans;

/**
 * 我们的文件类 这个文件类主要用来作为上传和下载的时候使用
 * <p>
 * 在上传和下载的时候我们使用上传的时间来判断是否为同一个文件
 *
 * @author Lutong99
 */

public class FileCustom {
    /**
     * 文件名字
     */
    private String fileName;

    /**
     * 文件路径, 也就是文件在本地文件的全路径
     */
    private String filePath;

    /**
     * 文件上传的时间, 在数据库中不存在重复
     */
    private String fileTime;

    /**
     * 文件大小, 主要是来显示在我们的微云页面上
     */
    private String fileSize;

    /**
     * Java bean 保证必须要有一个空参构造器
     */
    public FileCustom() {

    }

    /**
     * 创建一个文件上传下载对象, 使用全部的属性, 当进行数据库查询的时候可以进行返回一个完整对象
     *
     * @param fileName 文件名
     * @param filePath 文件路径
     * @param fileTime 文件上传的时间
     * @param fileSize 文件大小
     */
    public FileCustom(String fileName, String filePath, String fileTime, String fileSize) {
        super();
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileTime = fileTime;
        this.fileSize = fileSize;
    }

    /**
     * 不显示我们的路径, 只显示名字, 主要用来显示到我们的微云界面上
     *
     * @param fileName
     * @param fileTime
     * @param fileSize
     */
    public FileCustom(String fileName, String fileTime, String fileSize) {
        super();
        this.fileName = fileName;
        this.fileTime = fileTime;
        this.fileSize = fileSize;
    }

    /**
     * @return fileName 文件名
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName 要被设置的文件名
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return filePath 文件路径
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * @param filePath 要被设置的文件路径
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * @return 文件上传时间
     */
    public String getFileTime() {
        return fileTime;
    }

    /**
     * @param fileTime 要被设置的文件上传时间, 但一般没有什么用
     */
    public void setFileTime(String fileTime) {
        this.fileTime = fileTime;
    }

    /**
     * @return 文件大小
     */
    public String getFileSize() {
        return fileSize;
    }

    /**
     * @param fileSize 需要被设置的文件大小
     */
    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    /**
     * 用来做测试的toString, 一般没有什么用
     */
    @Override
    public String toString() {
        return "FileUpload [fileName=" + fileName + ", filePath=" + filePath + ", fileTime=" + fileTime + ", fileSize="
                + fileSize + "]";
    }
}
