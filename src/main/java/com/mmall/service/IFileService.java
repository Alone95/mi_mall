package com.mmall.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author alone95
 * @date 2017/12/3
 */
public interface IFileService {

    String upload(MultipartFile file ,String path);
}
