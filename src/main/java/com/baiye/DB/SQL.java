package com.baiye.DB;

/**
 * Created by Baiye on 2016/10/11.
 */
public class SQL {
    public final static String music_insert_sql = "INSERT INTO MusicInfo (\n" +
            "\tNAME,\n" +
            "\tArtist,\n" +
            "\tAlbum,\n" +
            "\tCommentCount,\n" +
            "\tURL\n" +
            ")\n" +
            "VALUES\n" +
            "\t(?,?,?,?,?);";

    public final static String dupilicate_removal_select_sql = "SELECT\n" +
            "\t*\n" +
            "FROM\n" +
            "\tDuplicateRemoval\n" +
            "WHERE\n" +
            "\tmd5 = ?";
}
