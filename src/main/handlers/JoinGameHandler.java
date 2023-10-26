package handlers;

import com.google.gson.Gson;
import exeptions.AlreadyTakenException;
import exeptions.BadReqException;
import exeptions.UnauthorizedException;
import request.JoinGameRequest;
import result.JoinGameResult;
import service.JoinGameService;
import spark.Request;
import spark.Response;

public class JoinGameHandler {
    private Gson gson = new Gson();
    public Object handleRequest(Request req, Response res)  {
        JoinGameRequest request = gson.fromJson(req.body(), JoinGameRequest.class);
        JoinGameService service = new JoinGameService();
        JoinGameResult result = new JoinGameResult(null,null);
        //set status
        try {
            String token = req.headers("Authorization");
            result = service.joinGame(request,token);
            res.status(200);
        } catch (BadReqException e) {
            res.status(400);
            result.setMessage((e.getMessage()));
        } catch (UnauthorizedException e) {
            res.status(401);
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
