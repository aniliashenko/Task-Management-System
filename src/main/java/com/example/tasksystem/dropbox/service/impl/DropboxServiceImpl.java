package com.example.tasksystem.dropbox.service.impl;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.example.tasksystem.dropbox.service.DropboxService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class DropboxServiceImpl implements DropboxService {

    @Value("${dbx-access-token}")
    private String accessToken;

    @Override
    public String uploadFile(String fileName, InputStream inputStream, long size) throws IOException, DbxException {
        final DbxRequestConfig config = DbxRequestConfig.newBuilder("task-system").build();
        final DbxClientV2 client = new DbxClientV2(config, accessToken);

        final FileMetadata metadata = client.files()
                .uploadBuilder("/attachments/" + fileName)
                .uploadAndFinish(inputStream);

        return metadata.getPathLower();
    }

    @Override
    public InputStream downloadFile(String dropBoxFileId) throws DbxException, IOException {
        final DbxRequestConfig config = DbxRequestConfig.newBuilder("task-system").build();
        final DbxClientV2 client = new DbxClientV2(config, accessToken);

        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        client.files().download(dropBoxFileId).download(out);
        return new ByteArrayInputStream(out.toByteArray());
    }

    @Override
    public void deleteFile(String dropBoxFileId) throws DbxException {
        final DbxRequestConfig config = DbxRequestConfig.newBuilder("task-system").build();
        final DbxClientV2 client = new DbxClientV2(config, accessToken);
        client.files().deleteV2(dropBoxFileId);
    }
}
