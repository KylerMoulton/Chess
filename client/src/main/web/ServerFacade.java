package web;

import com.google.gson.Gson;
import request.*;
import result.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

public class ServerFacade {
    public RegisterResult registerUser(RegisterRequest req) throws IOException {
        URL url = new URL("http://localhost:8080/user");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setReadTimeout(5000);
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.addRequestProperty("Accept", "application/json");
        connection.connect();
        try (OutputStream requestBody = connection.getOutputStream();) {
            requestBody.write(new Gson().toJson(req).getBytes(StandardCharsets.UTF_8));
        }
        RegisterResult result = null;
        if (connection.getResponseCode() == 200) {
            Map<String, List<String>> headers = connection.getHeaderFields();
            InputStream responseBody = connection.getInputStream();
            result = new Gson().fromJson(new InputStreamReader(responseBody), RegisterResult.class);
            responseBody.close();
            return result;
        } else {
            // SERVER RETURNED AN HTTP ERROR
            InputStream responseBody = connection.getErrorStream();
            // Read and process error response body from InputStream ...
        }
        return result;
    }

    public LoginResult loginUser(LoginRequest req) throws IOException {
        URL url = new URL("http://localhost:8080/session");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setReadTimeout(5000);
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.addRequestProperty("Accept", "application/json");
        connection.connect();
        try (OutputStream requestBody = connection.getOutputStream();) {
            requestBody.write(new Gson().toJson(req).getBytes(StandardCharsets.UTF_8));
        }
        LoginResult result = null;
        if (connection.getResponseCode() == 200) {
            Map<String, List<String>> headers = connection.getHeaderFields();
            InputStream responseBody = connection.getInputStream();
            result = new Gson().fromJson(new InputStreamReader(responseBody), LoginResult.class);
            responseBody.close();
            return result;
        } else {
            // SERVER RETURNED AN HTTP ERROR
            InputStream responseBody = connection.getErrorStream();
            // Read and process error response body from InputStream ...
        }
        return result;
    }

    public LogoutResult logoutUser(String auth) throws IOException {
        URL url = new URL("http://localhost:8080/session");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setReadTimeout(5000);
        connection.setRequestMethod("DELETE");
        connection.setDoOutput(false);
        connection.addRequestProperty("Accept", "application/json");
        connection.addRequestProperty("Authorization", auth);
        connection.connect();
        LogoutResult result = null;
        if (connection.getResponseCode() == 200) {
            Map<String, List<String>> headers = connection.getHeaderFields();
            InputStream responseBody = connection.getInputStream();
            result = new Gson().fromJson(new InputStreamReader(responseBody), LogoutResult.class);
            responseBody.close();
            return result;
        } else {
            // SERVER RETURNED AN HTTP ERROR
            InputStream responseBody = connection.getErrorStream();
            // Read and process error response body from InputStream ...
        }
        return result;
    }

    public CreateGameResult createGame(CreateGameRequest req) throws IOException {
        URL url = new URL("http://localhost:8080/game");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setReadTimeout(5000);
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.addRequestProperty("Accept", "application/json");
        connection.addRequestProperty("Authorization", req.getAuthToken());
        connection.connect();
        try (OutputStream requestBody = connection.getOutputStream();) {
            requestBody.write(new Gson().toJson(req).getBytes(StandardCharsets.UTF_8));
        }
        CreateGameResult result = null;
        if (connection.getResponseCode() == 200) {
            Map<String, List<String>> headers = connection.getHeaderFields();
            InputStream responseBody = connection.getInputStream();
            result = new Gson().fromJson(new InputStreamReader(responseBody), CreateGameResult.class);
            responseBody.close();
            return result;
        } else {
            // SERVER RETURNED AN HTTP ERROR
            InputStream responseBody = connection.getErrorStream();
            // Read and process error response body from InputStream ...
        }
        return result;
    }

    public ListGamesResult listGames(String auth) throws IOException {
        URL url = new URL("http://localhost:8080/game");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setReadTimeout(5000);
        connection.setRequestMethod("GET");
        connection.setDoOutput(true);
        connection.addRequestProperty("Accept", "application/json");
        connection.addRequestProperty("Authorization", auth);
        connection.connect();
        ListGamesResult result = null;
        if (connection.getResponseCode() == 200) {
            Map<String, List<String>> headers = connection.getHeaderFields();
            InputStream responseBody = connection.getInputStream();
            //result = new Gson().fromJson(new InputStreamReader(responseBody), ListGamesResult.class);
            result = ListGamesResult.serialization().fromJson(new InputStreamReader(responseBody), ListGamesResult.class);
            responseBody.close();
            return result;
        } else {
            // SERVER RETURNED AN HTTP ERROR
            InputStream responseBody = connection.getErrorStream();
            // Read and process error response body from InputStream ...
        }
        return result;
    }

    public JoinGameResult joinGame(JoinGameRequest req) throws IOException {
        URL url = new URL("http://localhost:8080/game");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setReadTimeout(5000);
        connection.setRequestMethod("PUT");
        connection.setDoOutput(true);
        connection.addRequestProperty("Authorization", req.getAuthToken());
        connection.addRequestProperty("Accept", "application/json");
        connection.connect();
        try (OutputStream requestBody = connection.getOutputStream();) {
            requestBody.write(new Gson().toJson(req).getBytes(StandardCharsets.UTF_8));
        }
        JoinGameResult result = null;
        if (connection.getResponseCode() == 200) {
            Map<String, List<String>> headers = connection.getHeaderFields();
            InputStream responseBody = connection.getInputStream();
            result = new Gson().fromJson(new InputStreamReader(responseBody), JoinGameResult.class);
            responseBody.close();
            return result;
        } else {
            // SERVER RETURNED AN HTTP ERROR
            InputStream responseBody = connection.getErrorStream();
            // Read and process error response body from InputStream ...
        }
        return result;
    }
}

