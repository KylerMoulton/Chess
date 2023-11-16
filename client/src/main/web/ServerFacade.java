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

    public LogoutResult logoutResult

}
