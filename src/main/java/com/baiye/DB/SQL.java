package com.baiye.DB;

/**
 * Created by Baiye on 2016/10/11.
 */
public class SQL {
    public final static String MUSIC_INSERT_SQL = "INSERT INTO MusicInfo (\n" +
            "\tNAME,\n" +
            "\tArtist,\n" +
            "\tAlbum,\n" +
            "\tCommentCount,\n" +
            "\tURL\n" +
            ")\n" +
            "VALUES\n" +
            "\t(?,?,?,?,?);";

    public final static String DUPILICATE_REMOVAL_SELECT_SQL = "SELECT\n" +
            "\t*\n" +
            "FROM\n" +
            "\tDuplicateRemoval\n" +
            "WHERE\n" +
            "\tmd5 = ?";
}
