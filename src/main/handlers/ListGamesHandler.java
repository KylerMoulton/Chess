package handlers;

import com.google.gson.Gson;
import exeptions.UnauthorizedException;
import result.ListGamesResult;
import service.ListGamesService;
import spark.Request;
import spark.Response;

import java.util.HashSet;

public class ListGamesHandler {
    private final Gson gson = new Gson();
    public Object handleRequest(Request req, Response res)  {
        ListGamesService service = new ListGamesService();
        ListGamesResult result = new ListGamesResult(new HashSet<>(),null);
        //set status
        try {
            String token = req.headers("Authorization");
            result = service.listGames(token);
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
