package com.example.demo.client;

import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class SampleClient {

    public static void main(String[] args) {

        // URI定義
        String getUri = "http://localhost:8080/JerseySample/rest/api/getlist";
        String postUri = "http://localhost:8080/JerseySample/rest/api/getbyid";

        // パラメーター定義
        int id = 3;

        // GETメソッドのAPIをコール
        get(getUri);

        // POSTメソッドのAPIをコール
        post(postUri, id);
    }

    // GETメソッドのAPIをコール
    private static void get(String uri) {

        // RestTemplate作成
        RestTemplate restTemplate = new RestTemplateBuilder()
                .basicAuthentication("test", "test")
                .build();

        // ヘッダー設定
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // GETリクエストを送信
        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);

        // レスポンスを表示
        System.out.println("GET(Response Status):" + response.getStatusCodeValue());
        System.out.println("GET(Response Body):" + response.getBody());
    }

    // POSTメソッドのAPIをコール
    private static void post(String uri, int id) {

        // RestTemplate作成
        RestTemplate restTemplate = new RestTemplateBuilder()
                .basicAuthentication("test", "test")
                .build();

        // ヘッダー設定
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        // リクエストボディ作成
        String requestBody = "{\"id\":" + id + "}";

        // POSTリクエストを送信
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);

        // レスポンスを表示
        System.out.println("POST(Response Status):" + response.getStatusCodeValue());
        System.out.println("POST(Response Body):" + response.getBody());
    }
}


