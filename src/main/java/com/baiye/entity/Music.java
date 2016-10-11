package com.baiye.entity;

import org.apache.commons.lang3.StringUtils;

import java.math.BigInteger;

/**
 * Created by Baiye on 2016/10/10.
 */
public class Music {

    private String name;

    private String artistName;

    private String albumName;

    private BigInteger commentCount;

    private String songURL;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public BigInteger getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(BigInteger commentCount) {
        this.commentCount = commentCount;
    }

    public String getSongURL() {
        return songURL;
    }

    public void setSongURL(String songURL) {
        this.songURL = songURL;
    }

    public boolean isValid()
    {
        if(StringUtils.isNotBlank(getName()) && StringUtils.isNotBlank(getArtistName()) && StringUtils.isNotBlank(getAlbumName())
                && StringUtils.isNotBlank(getSongURL()))
            return true;
        return false;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Name : " + this.getName() + "\t");
        sb.append("Artist : "+ this.getArtistName() + "\t");
        sb.append("Album : " + this.getAlbumName() + "\t");
        sb.append("CommentCount : " + this.getCommentCount() + "\t");
        sb.append("URL : " + this.getSongURL() + "\t");
        return sb.toString();
    }
}
