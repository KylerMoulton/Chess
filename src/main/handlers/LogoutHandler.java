package handlers;

import com.google.gson.Gson;
import result.LogoutResult;
import service.LogoutService;
import spark.Response;

public class LogoutHandler {
    private Gson gson;
    public Object handleRequest(Response res) throws Exception {
        LogoutService service = new LogoutService();
        LogoutResult result = service.logout();
        //set status
        res.status(200);
        res.status(401);
        res.status(500);
        return gson.toJson(result);
    }
}
