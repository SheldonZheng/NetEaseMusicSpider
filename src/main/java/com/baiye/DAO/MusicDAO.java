package com.baiye.DAO;

import com.baiye.DB.DBConf;
import com.baiye.DB.SQL;
import com.baiye.entity.Music;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by Baiye on 2016/10/11.
 */
public class MusicDAO {

    private Logger logger = LoggerFactory.getLogger(MusicDAO.class);

    private Connection conn;

    private QueryRunner queryRunner;

    private ArrayHandler arrayHandler;

    private MapHandler mapHandler;






    public MusicDAO() {
        init();
    }

    public boolean insertMusic(Music music)
    {
        queryRunner = getQueryRunner();

        try {
            Object []params = {music.getName(),music.getArtistName(),music.getAlbumName(),music.getCommentCount(),music.getSongURL()};
            int i = queryRunner.update(getConnection(),SQL.MUSIC_INSERT_SQL,params);
            return i==1;
        } catch (SQLException e) {
            logger.error("插入单条音乐信息失败：" + e.getMessage());
        }
        return false;
    }

    public boolean insertMusic(List<Music> musics)
    {
        queryRunner = getQueryRunner();

        try {
            for (Music music : musics) {
                Object []params = {music.getName(),music.getArtistName(),music.getAlbumName(),music.getCommentCount(),music.getSongURL()};
                int i = queryRunner.update(getConnection(),SQL.MUSIC_INSERT_SQL,params);
                if(i != 1)
                    return false;
            }
            return true;
        } catch (SQLException e) {
            logger.error("插入多条音乐信息失败：" + e.getMessage());
        }
        return false;
    }

    public Map findDuplicateRemoval(String md5)
    {
        queryRunner = getQueryRunner();

        try {
            return queryRunner.query(getConnection(), SQL.DUPILICATE_REMOVAL_SELECT_SQL,md5,getMapHandler());
        } catch (SQLException e) {
            logger.error("查询去重表失败" + e.getMessage());
        }
        return null;
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
            logger.error("未找到数据库驱动：" + e.getMessage());
        } catch (SQLException e) {
            logger.error("获取数据库连接失败：" + e.getMessage());
        }
        return null;
    }


    private void init()
    {
        getConnection();
        getQueryRunner();
        getArrayHandler();
        getMapHandler();
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

    public MapHandler getMapHandler() {
        if(mapHandler != null)
            return mapHandler;
        mapHandler = new MapHandler();
        return mapHandler;
    }
}
