package handlers;

import com.google.gson.Gson;
import result.ClearResult;
import service.ClearService;
import spark.Response;

public class ClearHandler {
    private final Gson gson = new Gson();
    public Object handleRequest(Response res)  {
        ClearService service = new ClearService();
        ClearResult result = new ClearResult(null);
        try {
            result = service.clear();
            res.status(200);
        } catch (Exception e) {
            res.status(500);
            result.setMessage(e.getMessage());
        }
        //set status
        return gson.toJson(result);
    }
}
