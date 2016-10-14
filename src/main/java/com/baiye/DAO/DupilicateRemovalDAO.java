package com.baiye.DAO;

import com.baiye.DB.SQL;
import com.baiye.entity.DuplicateRemoval;
import org.apache.commons.dbutils.QueryRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.Map;

/**
 * Created by Baiye on 2016/10/14.
 */
public class DupilicateRemovalDAO extends BaseDAO{

    private Logger logger = LoggerFactory.getLogger(DupilicateRemovalDAO.class);

    private QueryRunner queryRunner;

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

    public boolean insertDuplicateRemoval(DuplicateRemoval duplicateRemoval)
    {
        queryRunner = getQueryRunner();

        try {

            int i = queryRunner.update(getConnection(),SQL.DUPLICATE_REMOVAL_INSERT_SQL,duplicateRemoval.getMd5());
            return i==1;
        } catch (SQLException e) {
            logger.error("插入单条音乐信息失败：" + e.getMessage());
        }
        return false;

    }
}
