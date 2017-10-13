package algorithm;

import entity.Item;

import java.util.List;

/**
 * Created by Zijin Wang on 10/13/2017.
 */
public interface Recommendation {

    public List<Item> recommendItems(String userId, double latitude, double longitude);

}
