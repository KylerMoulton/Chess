package result;

public class CreateGameResult {
    private String authToken;
    private int gameID;
    private String message;

    public CreateGameResult(String authToken, int gameID, String message) {
        this.authToken = authToken;
        this.gameID = gameID;
        this.message = message;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
