package com.baiye.DAO;

import com.baiye.DB.DBConf;
import com.baiye.DB.SQL;
import com.baiye.entity.DuplicateRemoval;
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
public class MusicDAO extends BaseDAO{

    private Logger logger = LoggerFactory.getLogger(MusicDAO.class);

    private QueryRunner queryRunner;







    public MusicDAO() {
        super.init();
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





}
