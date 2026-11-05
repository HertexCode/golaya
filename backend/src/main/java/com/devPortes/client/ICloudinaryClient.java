package com.devPortes.client;

import org.springframework.web.multipart.MultipartFile;

public interface ICloudinaryClient {
    String saveImg(String nameFolder,MultipartFile img);
    String saveQrImg(String nameFolder, byte[] qrImg);
}
