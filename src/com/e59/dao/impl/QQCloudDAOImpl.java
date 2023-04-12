package com.e59.dao.impl;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.Vector;

import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.e59.beans.FileCustom;
import com.e59.beans.QQ;
import com.e59.dao.QQCloudDAO;

/**
 * QQ cloud DAO 的实现类
 *
 * @author  Lutong99
 *
 */
public class QQCloudDAOImpl implements QQCloudDAO {

	@Override
	public FileCustom uploadFiles(FileCustom fileUpload, QQ qq) {

		String sql = "insert into qq_cloud" + qq.getNumber()
				+ " (cloud_name, cloud_path, cloud_time, cloud_size) values (?, ?, ?, ?);";

		Object[] params = { fileUpload.getFileName(), fileUpload.getFilePath(), fileUpload.getFileTime(),
				fileUpload.getFileSize() };

		try {
			int execute = queryRunner.execute(sql, params);

			if (execute > 0) {
				return fileUpload;
			} else {
				return null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;

		}
	}

	@Override
	public Vector<Vector<String>> getDatas(QQ qq) {
		String sql = "select cloud_name, cloud_time, cloud_size from qq_cloud" + qq.getNumber()
				+ " where cloud_state = 1";

		ResultSetHandler<Vector<Vector<String>>> rshHandler = new ResultSetHandler<Vector<Vector<String>>>() {

			@Override
			public Vector<Vector<String>> handle(ResultSet rs) throws SQLException {
				Vector<Vector<String>> doubleVector = new Vector<Vector<String>>();

				while (rs.next()) {
					Vector<String> vector = new Vector<String>();
					String filename = rs.getString("cloud_name");
					String filetime = rs.getString("cloud_time");
					String filesize = rs.getString("cloud_size");

					vector.add(filename);
					vector.add(filetime);
					vector.add(filesize);
					doubleVector.add(vector);
				}
				return doubleVector;
			}

		};
		Object[] params = {};
		try {
			Vector<Vector<String>> query = queryRunner.query(sql, rshHandler, params);
			if (query != null) {
				return query;
			} else {
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public FileCustom downloadFiles(FileCustom downloadFiles, QQ qq) {
		String sql = "select cloud_name fileName, cloud_path filePath, cloud_time fileTime, cloud_size fileSize from qq_cloud"
				+ qq.getNumber() + " where cloud_time = ? and cloud_state = 1";
		BeanHandler<FileCustom> rsh = new BeanHandler<>(FileCustom.class);
		Object[] params = { downloadFiles.getFileTime() };
		try {
			FileCustom downloadFile = queryRunner.query(sql, rsh, params);
			if (downloadFile != null) {
				return downloadFile;
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean deleteFiles(FileCustom fileUpload, QQ qq) {
		// String sql = "delete from qq_cloud" + qq.getNumber() + " where cloud_time =
		// ?";
		// 下面采用逻辑删除
		String sql = "update qq_cloud" + qq.getNumber() + " set cloud_state = 0 where cloud_time = ? ";
		Object[] params = { fileUpload.getFileTime() };

		try {
			int update = queryRunner.update(sql, params);

			if (update > 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

}
