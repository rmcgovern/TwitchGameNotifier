package model;
import java.net.URL;
 
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
 
public class JSONReader {
    
    public static JSONArray getUserFollows(String usernameString) {
        String url = "https://api.twitch.tv/kraken/users/" + usernameString + "/follows/channels?limit=100&direction=asc";
        
        try {
            String channelsJson = IOUtils.toString(new URL(url));
            JSONObject channelsJsonObject = (JSONObject) JSONValue.parseWithException(channelsJson);
            // get the channels
            JSONArray channelsArray = (JSONArray) channelsJsonObject.get("follows");
            // print out the channels
            for (int i = 0; i < channelsArray.size(); i++) {
               JSONObject channel = (JSONObject) channelsArray.get(i);
               JSONObject channelInfo = (JSONObject) channel.get("channel");
               System.out.println(channelInfo.get("display_name"));
            }
            
            return channelsArray;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
 
    public static JSONArray getGames() {
        String url = "https://api.twitch.tv/kraken/games/top?limit=50&offset=0";

        try {
            String gamesJson = IOUtils.toString(new URL(url));
            JSONObject gamesJsonObject = (JSONObject) JSONValue.parseWithException(gamesJson);
            // get the games
            JSONArray gamesArray = (JSONArray) gamesJsonObject.get("top");
            // print out the games
            for (int i = 0; i < gamesArray.size(); i++) {
               JSONObject game = (JSONObject) gamesArray.get(i);
               JSONObject gameInfo = (JSONObject) game.get("game");
               System.out.println(gameInfo.get("name"));
            }
            return gamesArray;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}