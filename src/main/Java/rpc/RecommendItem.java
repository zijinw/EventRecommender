package rpc;

import algorithm.GeoRecommendation;
import algorithm.Recommendation;
import entity.Item;
import org.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Zijin Wang on 10/13/2017.
 */
@WebServlet("/recommendation")
public class RecommendItem extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("user_id");
        double lat = Double.parseDouble(request.getParameter("lat"));
        double lon = Double.parseDouble(request.getParameter("lon"));
        Recommendation recommendation = new GeoRecommendation();
        List<Item> items = recommendation.recommendItems(userId, lat, lon);
        RpcHelper.writeJsonArray(response, new JSONArray(items));
    }
}
