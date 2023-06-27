package org.example.dao.impl;

import org.example.beans.QQ;
import org.example.dao.QQMainDAO;

import java.sql.SQLException;

public class QQMainDAOImpl implements QQMainDAO {

    public boolean updateInfo(QQ updateQq) {
//		String sql = "update qq_table (qq_nickname, qq_password, qq_photo, qq_gender, "
//				+ "qq_birthday, qq_country, qq_province, qq_city, qq_email, qq_nation) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
        String sql = "update qq_table set qq_nickname = ?, qq_password = ?, qq_photo = ?, qq_gender = ?, qq_birthday = ?, qq_country = ?, qq_province = ?, qq_city = ?, qq_email = ?, qq_nation = ? where qq_number = ?";
        Object[] params = {updateQq.getNickname(), updateQq.getPassword(), updateQq.getPhoto(), updateQq.getGender(),
                updateQq.getBirthday(), updateQq.getCountry(), updateQq.getProvince(), updateQq.getCity(),
                updateQq.getEmail(), updateQq.getNation(), updateQq.getNumber()};
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
