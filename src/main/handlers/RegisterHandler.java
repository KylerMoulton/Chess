package handlers;

import com.google.gson.Gson;
import exeptions.AlreadyTakenException;
import exeptions.BadReqException;
import request.RegisterRequest;
import result.RegisterResult;
import service.RegisterService;
import spark.Request;
import spark.Response;

public class RegisterHandler {
    private final Gson gson = new Gson();
    public Object handleRequest(Request req, Response res) {
        RegisterRequest request = gson.fromJson(req.body(), RegisterRequest.class);
        RegisterService service = new RegisterService();
        RegisterResult result = new RegisterResult(null,null,null);
        try {
            result = service.register(request);
            res.status(200);
        } catch (BadReqException e) {
            res.status(400);
            result.setMessage(e.getMessage());
        } catch (AlreadyTakenException e) {
            res.status(403);
            result.setMessage(e.getMessage());
        } catch (Exception e) {
            res.status(500);
            result.setMessage(e.getMessage());

        }

        return gson.toJson(result);
    }
}
