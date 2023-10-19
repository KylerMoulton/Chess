package handlers;

import com.google.gson.Gson;
import result.ClearResult;
import service.ClearService;
import spark.Response;

public class ClearHandler {
    private Gson gson;
    public Object handleRequest(Response res) throws Exception {
        ClearService service = new ClearService();
        ClearResult result = service.clear();
        //set status
        res.status(200);
        res.status(500);
        return gson.toJson(result);
    }
}
