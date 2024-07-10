package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import com.udacity.jwdnd.course1.cloudstorage.model.ResponseFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FilesService {
    ResponseFile getResponseFile(Files file);

    List<ResponseFile> getAllFiles(int userid) throws Exception;

    void addFile(MultipartFile fileUpload, int userid) throws IOException;

    void deleteFile(int fileid);
}
