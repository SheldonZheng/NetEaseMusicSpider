package com.baiye.DAO;

import com.baiye.DB.DBConf;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Baiye on 2016/10/11.
 */
public class MusicDAO {

    private Logger logger = LoggerFactory.getLogger(MusicDAO.class);

    private Connection conn;

    private QueryRunner queryRunner;

    private ArrayHandler arrayHandler;


    public MusicDAO() {
        try {
            Class.forName(DBConf.JDBC_DRIVER);
            conn = DriverManager.getConnection(DBConf.DB_URL,DBConf.USER,DBConf.PASS);
        } catch (ClassNotFoundException e) {
            logger.error("未找到数据库驱动" + e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
