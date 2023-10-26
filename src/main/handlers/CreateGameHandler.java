package handlers;

import com.google.gson.Gson;
import exeptions.BadReqException;
import exeptions.UnauthorizedException;
import request.CreateGameRequest;
import result.CreateGameResult;
import service.CreateGameService;
import spark.Request;
import spark.Response;

public class CreateGameHandler {
    private final Gson gson = new Gson();
    public Object handleRequest(Request req, Response res)  {
        CreateGameRequest request = gson.fromJson(req.body(), CreateGameRequest.class);
        CreateGameService service = new CreateGameService();
        CreateGameResult result = new CreateGameResult(0,null);
        //set status
        try {
            String token = req.headers("Authorization");
            result = service.createGame(request,token);
            res.status(200);
        } catch (BadReqException e) {
            res.status(400);
            result.setMessage((e.getMessage()));
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
