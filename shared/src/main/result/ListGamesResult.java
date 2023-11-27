package result;


import chess.ChessBoard;
import chess.ChessGame;
import chess.gameImple;
import com.google.gson.*;
import model.GameModel;

import java.lang.reflect.Type;
import java.util.Collection;

/**
 * Class that creates a List Games Result that JoinGameService returns
 */

public class ListGamesResult {
    /**
     * Set of all games in the Database
     */
    private Collection<GameModel> games;
    //    private int gameID;
    /**
     * Name of the game
     */
    private String gameName;
    /**
     * Takes in an authToken to check if the User is authorized
     */
    private String authToken;
    /**
     * Message returned after HTTP request
     */
    private String message;

    /**
     * Constructor for ListGamesResult
     *
     * @param gameModels Set of all games in the Database
     * @param message    Message returned if HTTP request fails or is successful
     */

    public ListGamesResult(Collection<GameModel> gameModels, String message) {
        this.games = gameModels;
        this.message = message;
    }

    public ListGamesResult() {

    }

    public Collection<GameModel> getGamesList() {
        return games;
    }

    public void setGamesList(Collection<GameModel> gamesList) {
        this.games = gamesList;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static Gson serialization() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(ListGamesResult.class, new ListGamesResult.adapterListGames());
        return gsonBuilder.create();
    }

    public static class adapterListGames implements JsonDeserializer<ListGamesResult> {
        public ListGamesResult deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(ChessGame.class, new gameImple.adapterChessGame());
            return builder.create().fromJson(jsonElement, ListGamesResult.class);
        }
    }
}