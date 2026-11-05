package com.devPortes.client;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.devPortes.fields.exceptions.ExternalServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CloudinaryClientService implements ICloudinaryClient {
    private final Cloudinary cloudinary;
    private final String nameApi = "cloudinary ";

    @Override
    public String saveImg(String nameFolder, MultipartFile img) {

        try {
            // Sube los bytes de la imagen a Cloudinary
            Map<?, ?> uploadResult = cloudinary.uploader().upload(
                    img.getBytes(),
                    ObjectUtils.asMap("folder", nameFolder) // Nombre de la carpeta en Cloudinary (opcional)
            );

            // Retorna la URL pública HTTPS de la imagen guardada
            return uploadResult.get("secure_url").toString();

        } catch (IOException e) {
            throw new ExternalServiceException(nameApi, e.getMessage());
        }
    }

    @Override
    public String saveQrImg(String nameFolder, byte[] qrImg) {
    try {
        Map<?, ?> uploadResult = cloudinary.uploader().upload(
                qrImg,
                ObjectUtils.asMap("folder", nameFolder)
        );
        return uploadResult.get("secure_url").toString();
    }catch(IOException e){
        throw new ExternalServiceException(nameApi, e.getMessage());
    }
    }
}