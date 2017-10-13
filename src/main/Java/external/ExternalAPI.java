package external;

import entity.Item;

import java.util.List;

/**
 * Created by Zijin Wang on 10/12/2017.
 */
public interface ExternalAPI {
    public List<Item> search(double lat, double lon, String term);
}
