package rpc;

import db.DBConnection;
import db.DBConnectionFactory;
import entity.Item;
import external.ExternalAPI;
import external.ExternalAPIFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Zijin Wang on 10/12/2017.
 */
@javax.servlet.annotation.WebServlet("/search")
public class SearchItem extends javax.servlet.http.HttpServlet {

    private DBConnection conn = DBConnectionFactory.getDBConnection();

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String userId = request.getParameter("user_id");
        double lat = Double.parseDouble(request.getParameter("lat"));
        double lon = Double.parseDouble(request.getParameter("lon"));
        String term = request.getParameter("keyword");
        List<Item> items = conn.searchItems(userId, lat, lon, term);
        List<JSONObject> list = new ArrayList<>();

        Set<String> favorite = conn.getFavoriteItemIds(userId);

        try {
            for (Item item : items) {
                JSONObject event = item.toJSONObject();
                // Check if this is a favorite one.
                // This field is required by frontend to correctly display favorite items.
                event.put("favorite", favorite.contains(item.getItemId()));

                list.add(event);
            }
        }  catch (Exception e) {
            e.printStackTrace();
        }

        JSONArray array = new JSONArray(list);
        RpcHelper.writeJsonArray(response, array);
    }
}
