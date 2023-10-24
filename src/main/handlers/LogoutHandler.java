package handlers;

import com.google.gson.Gson;
import exeptions.UnauthorizedException;
import result.LogoutResult;
import service.LogoutService;
import spark.Request;
import spark.Response;

public class LogoutHandler {
    private final Gson gson = new Gson();
    public Object handleRequest(Request req, Response res) {
        LogoutService service = new LogoutService();
        LogoutResult result = new LogoutResult(null,null);
        //set status
        try {
            String token = req.headers("Authorization");
            result = service.logout(token);
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
