import java.util.HashMap;
import com.fasterxml.jackson.databind.ObjectMapper;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {

        HashMap<Integer, String> dataMap = new HashMap<>();
        final int[] id = {0}; //initialize id as 0, increment everytime data is added

        //grab port as a parameter
//        int port = Integer.parseInt(args[0]);
        int port = 4000;
        //set up port
        port(port);

        get("/item", (req, res) -> {

            String json = new ObjectMapper().writeValueAsString(dataMap);
            return json;
        });

        post("/item", (req, res) -> {
            dataMap.put(id[0], req.queryParams("value"));
            id[0]++;
            res.redirect("/item");
            return res;
        });



    }
}