package com.baiye.DAO;

import com.baiye.DB.DBConf;
import com.baiye.entity.Music;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Baiye on 2016/10/11.
 */
public class MusicDAO {

    private Logger logger = LoggerFactory.getLogger(MusicDAO.class);

    private Connection conn;

    private QueryRunner queryRunner;

    private ArrayHandler arrayHandler;




    public MusicDAO() {
        init();
    }

    public boolean insertMusic(Music music)
    {

        return false;
    }

    public boolean insertMusic(List<Music> musics)
    {

        return false;
    }



    private Connection getConnection()
    {
        if(conn != null)
            return conn;
        try {
            Class.forName(DBConf.JDBC_DRIVER);
            conn = DriverManager.getConnection(DBConf.DB_URL,DBConf.USER,DBConf.PASS);
            return conn;
        } catch (ClassNotFoundException e) {
            logger.error("未找到数据库驱动" + e.getMessage());
        } catch (SQLException e) {
            logger.error("获取数据库连接失败" + e.getMessage());
        }
        return null;
    }


    private void init()
    {
        getConnection();
        getQueryRunner();
        getArrayHandler();
    }

    public QueryRunner getQueryRunner() {
        if(queryRunner != null)
            return queryRunner;
        queryRunner = new QueryRunner();
        return queryRunner;
    }

    public ArrayHandler getArrayHandler() {
        if(arrayHandler != null)
            return arrayHandler;
        arrayHandler = new ArrayHandler();
        return arrayHandler;
    }
}
