package handlers;

import com.google.gson.Gson;
import request.RegisterRequest;
import result.RegisterResult;
import service.RegisterService;
import spark.Request;
import spark.Response;

public class RegisterHandler {
    private Gson gson;
    public Object handleRequest(Request req, Response res) throws Exception {
        RegisterRequest request = gson.fromJson(req.body(), RegisterRequest.class);
        RegisterService service = new RegisterService();
        RegisterResult result = service.register(request);
        //set status
        res.status(200);
        res.status(400);
        res.status(403);
        res.status(500);
        return gson.toJson(result);
    }
}
