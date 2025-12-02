package com.example.tasksystem.dropbox.service;

import com.dropbox.core.DbxException;

import java.io.IOException;
import java.io.InputStream;

public interface DropboxService {

    String uploadFile(String fileName, InputStream inputStream, long size) throws IOException, DbxException;

    InputStream downloadFile(String dropBoxFileId) throws DbxException, IOException;

    void deleteFile(String dropBoxFileId) throws DbxException;
}
