package com.baiye.DAO;

import com.baiye.DB.DBConf;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Baiye on 2016/10/14.
 */
public class BaseDAO {

    private Logger logger = LoggerFactory.getLogger(BaseDAO.class);

    private Connection conn;

    private QueryRunner queryRunner;

    private ArrayHandler arrayHandler;

    private MapHandler mapHandler;


    protected void init()
    {
        getConnection();
        getQueryRunner();
        getArrayHandler();
        getMapHandler();
    }

    protected Connection getConnection()
    {
        if(conn != null)
            return conn;
        try {
            Class.forName(DBConf.JDBC_DRIVER);
            conn = DriverManager.getConnection(DBConf.DB_URL,DBConf.USER,DBConf.PASS);

            return conn;
        } catch (ClassNotFoundException e) {
            logger.error("未找到数据库驱动：" + e.getMessage());
        } catch (SQLException e) {
            logger.error("获取数据库连接失败：" + e.getMessage());
        }
        return null;
    }

    protected QueryRunner getQueryRunner() {
        if(queryRunner == null)
            queryRunner = new QueryRunner();
        return queryRunner;
    }

    protected ArrayHandler getArrayHandler() {
        if(arrayHandler == null)
            arrayHandler = new ArrayHandler();
        return arrayHandler;
    }

    protected MapHandler getMapHandler() {
        if(mapHandler == null)
            mapHandler = new MapHandler();
        return mapHandler;
    }
}
