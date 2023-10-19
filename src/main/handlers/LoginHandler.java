package handlers;

import com.google.gson.Gson;
import request.LoginRequest;
import result.LoginResult;
import service.LoginService;
import spark.Request;
import spark.Response;

public class LoginHandler {
    private Gson gson;
    public Object handleRequest(Request req, Response res) throws Exception {
        LoginRequest request = gson.fromJson(req.body(), LoginRequest.class);
        LoginService service = new LoginService();
        LoginResult result = service.login(request);
        //set status
        res.status(200);
        res.status(401);
        res.status(500);
        return gson.toJson(result);
    }
}
