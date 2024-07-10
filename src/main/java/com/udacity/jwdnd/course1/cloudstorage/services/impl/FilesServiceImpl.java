package com.udacity.jwdnd.course1.cloudstorage.services.impl;

import com.udacity.jwdnd.course1.cloudstorage.repository.FilesMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import com.udacity.jwdnd.course1.cloudstorage.model.ResponseFile;
import com.udacity.jwdnd.course1.cloudstorage.services.FilesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FilesServiceImpl implements FilesService {

    private final FilesMapper filesMapper;

    @Override
    public ResponseFile getResponseFile(Files file) {
        String base64 = Base64.getEncoder().encodeToString(file.getFiledata());
        String dataURL = "data:" + file.getContenttype() + ";base64," + base64;
        return ResponseFile.builder().fileid(file.getFileid()).filename(file.getFilename()).dataURL(dataURL).build();
    }

    @Override
    public List<ResponseFile> getAllFiles(int userid) throws Exception {
        List<Files> files = filesMapper.findByUserId(userid);
        if (files == null) {
            throw new Exception();
        }
        return files.stream().map(this::getResponseFile).collect(Collectors.toList());
    }

    @Override
    public void addFile(MultipartFile fileUpload, int userid) throws IOException {
        Files file = new Files();
        try {
            file.setContenttype(fileUpload.getContentType());
            file.setFiledata(fileUpload.getBytes());
            file.setFilename(fileUpload.getOriginalFilename());
            file.setFilesize(Long.toString(fileUpload.getSize()));
        } catch (IOException e) {
            throw e;
        }
        filesMapper.insertFile(file, userid);
    }

    @Override
    public void deleteFile(int fileid) {
        filesMapper.deleteFile(fileid);
    }
}
