package com.udacity.jwdnd.course1.cloudstorage.model;

import java.sql.Blob;

public class File {
    private Integer fileId;
    private String filename;
    private String contenttype;
    private String filesize;
    private String userId;
    private Blob filedata;

    public File(Integer fileId, String filename, String contenttype, String filesize,
                String userId, Blob filedata) {
        this.fileId = fileId;
        this.filename = filename;
        this.contenttype = contenttype;
        this.filesize = filesize;
        this.userId = userId;
        this.filedata = filedata;
    }

    public Integer getFileId() {
        return fileId;
    }

    public String getFilename() {
        return filename;
    }

    public String getContenttype() {
        return contenttype;
    }

    public String getFilesize() {
        return filesize;
    }

    public String getUserId() {
        return userId;
    }

    public Blob getFiledata() {
        return filedata;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setContenttype(String contenttype) {
        this.contenttype = contenttype;
    }

    public void setFilesize(String filesize) {
        this.filesize = filesize;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setFiledata(Blob filedata) {
        this.filedata = filedata;
    }
}
