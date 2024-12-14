package com.tech.novus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Random;

public class Command {
    static HttpClient client = HttpClient.newHttpClient();

    /**
     * Method used to trigger GET requests
     * */
    public static void executeGetRequest(String urlString,String token){
        HttpRequest request;
        try {
            request = HttpRequest.newBuilder()
                    .GET()
                    .uri(new URI(urlString))
                    .setHeader("Content-Type","application/json")
                    .setHeader("Authorization", "Bearer "+token)
                    .build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.statusCode() +"\n" +response.body());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void executeDeleteRequest(String url,String param,String token){
        HttpRequest request;
        try {
            request = HttpRequest.newBuilder()
                    .DELETE()
                    .uri(new URI(url+param))
                    .setHeader("Content-Type","application/json")
                    .setHeader("Authorization", "Bearer "+token)
                    .build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.statusCode() +"\n" +response.body());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createRepositoryJsonBody(String url, String repoName, String repoType, String requestType,String token){
        Repo repo = new Repo();
        String newRepoName = repoName+"-"+repoType;
        repo.populateFields();
        repo.setRclass(repoType);
        if(repoType.equals("remote")){
            repo.setUrl(generateURL(newRepoName));
        }
        String json;
        ObjectMapper mapper = new ObjectMapper();
        try {
            json = mapper.writeValueAsString(repo);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        url = url+newRepoName;
        if(requestType.equals("PUT")){
            executePutRequest(url,json,token);
        }else{
            executePostRequest(url,json,token);
        }
    }

    public static void createUserPostBody(String url,String username, String password,String email,String token){
        User user = new User();
        user.populateFields();
        user.setEmail(email);
        user.setPassword(password);
        user.setUsername(username);
        String json;
        ObjectMapper mapper = new ObjectMapper();
        try {
            json = mapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        executePostRequest(url,json,token);
    }

    public static void executePostRequest(String url, String json,String token){
        HttpRequest request;
        try {
            request = HttpRequest.newBuilder()
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .uri(new URI(url))
                    .setHeader("Content-Type","application/json")
                    .setHeader("Authorization", "Bearer "+token)
                    .build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.statusCode() +"\n" +response.body());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void executePutRequest(String url, String json,String token){
        HttpRequest request;
        try {
            request = HttpRequest.newBuilder()
                    .PUT(HttpRequest.BodyPublishers.ofString(json))
                    .uri(new URI(url))
                    .setHeader("Content-Type","application/json")
                    .setHeader("Authorization", "Bearer "+token)
                    .build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.statusCode() +"\n" +response.body());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static String generateURL(String repoName){
        Random random = new Random();
        int port = random.nextInt(10000) +1000;
        return "http://compuzign.jfrog.io:"+port+"/"+repoName;
    }
}
