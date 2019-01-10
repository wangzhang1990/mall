package com.taotao.service;

import org.springframework.web.multipart.MultipartFile;

import com.taotao.result.PictureResult;

public interface PictureService {
	PictureResult upload(MultipartFile uploadFile);
}
