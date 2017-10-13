package rpc;

import db.DBConnection;
import db.DBConnectionFactory;
import entity.Item;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Zijin Wang on 10/12/2017.
 */
@WebServlet("/history")
public class ItemHistory extends HttpServlet {

    private DBConnection conn = DBConnectionFactory.getDBConnection();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            JSONObject input = RpcHelper.readJsonObject(request);
            if (input.has("user_id") && input.has("favorite")) {
                String userId = (String) input.get("user_id");
                JSONArray array = (JSONArray) input.get("favorite");
                List<String> histories  = new ArrayList<>();
                for (int i = 0; i < array.length(); i++) {
                    String eventId = (String) array.get(i);
                    histories.add(eventId);
                }
                conn.setFavoriteItems(userId, histories);
                RpcHelper.writeJsonObject(response, new JSONObject().put("status", "OK"));
            } else {
                RpcHelper.writeJsonObject(response, new JSONObject().put("status", "InvalidParameter"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            JSONObject input = RpcHelper.readJsonObject(request);
            if (input.has("user_id")) {
                String userId = (String) input.get("user_id");
                Set<Item> favorites = conn.getFavoriteItems(userId);
                JSONArray array = new JSONArray();
                for (Item item : favorites) {
                    JSONObject obj = item.toJSONObject();
                    try {
                        obj.append("favorite", true);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    array.put(obj);
                }
                RpcHelper.writeJsonArray(response, array);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            JSONObject input = RpcHelper.readJsonObject(request);
            if (input.has("user_id") && input.has("favorite")) {
                String userId = (String) input.get("user_id");
                JSONArray array = (JSONArray) input.get("favorite");
                List<String> histories  = new ArrayList<>();
                for (int i = 0; i < array.length(); i++) {
                    String eventId = (String) array.get(i);
                    histories.add(eventId);
                }
                conn.unsetFavoriteItems(userId, histories);
                RpcHelper.writeJsonObject(response, new JSONObject().put("status", "OK"));
            } else {
                RpcHelper.writeJsonObject(response, new JSONObject().put("status", "InvalidParameter"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
