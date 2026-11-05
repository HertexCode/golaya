package com.devPortes.client;


import com.devPortes.fields.exceptions.ExternalServiceException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class QrCodeApiClientService implements IQrCodeApiClient {

    RestClient restClient = RestClient.create();

    public QrCodeApiClientService(RestClient restClient) {
        this.restClient=restClient;
    }

    @Override
    public byte[] createQr(String urlQr) {
        String urlEncode = URLEncoder.encode(urlQr, StandardCharsets.UTF_8);
        String url = "https://api.qrserver.com/v1/create-qr-code/?data=" + urlEncode + "&size=10000x10000&color=021F00&margin=15";
        String nameApi = "QrCodeApi";

        try {
            return restClient.get()
                    .uri(url)
                    .retrieve()
                    .body(byte[].class);
        } catch (RestClientException e) {
            throw new ExternalServiceException(nameApi, "ERROR al conectar con el cliente");
        }
    }
}
