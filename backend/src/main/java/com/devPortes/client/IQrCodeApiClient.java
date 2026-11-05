package com.devPortes.client;

import org.springframework.web.multipart.MultipartFile;

public interface IQrCodeApiClient {
    byte[] createQr (String Url);

}
