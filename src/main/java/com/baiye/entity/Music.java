package com.baiye.entity;

/**
 * Created by Baiye on 2016/10/10.
 */
public class Music {

    private String name;

    private String artistName;

    private String albumName;

    private String commentCount;

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

    public String getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(String commentCount) {
        this.commentCount = commentCount;
    }

    public String getSongURL() {
        return songURL;
    }

    public void setSongURL(String songURL) {
        this.songURL = songURL;
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
