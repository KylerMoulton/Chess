package handlers;

import com.google.gson.Gson;
import exeptions.UnauthorizedException;
import request.LoginRequest;
import result.LoginResult;
import service.LoginService;
import spark.Request;
import spark.Response;

public class LoginHandler {
    private final Gson gson = new Gson();
    public Object handleRequest(Request req, Response res) {
        LoginRequest request = gson.fromJson(req.body(), LoginRequest.class);
        LoginService service = new LoginService();
        LoginResult result = new LoginResult(null,null,null);
        //set status
        try {
            result = service.login(request);
            res.status(200);
        } catch (UnauthorizedException e) {
            res.status(401);
            result.setMessage(e.getMessage());
        } catch (Exception e) {
            res.status(500);
            result.setMessage(e.getMessage());

        }
        return gson.toJson(result);
    }
}
