import java.util.HashMap;
import com.fasterxml.jackson.databind.ObjectMapper;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {

        HashMap<Integer, String> dataMap = new HashMap<>();
        final int[] id = {0}; //initialize id as 0, increment everytime data is added

        //grab port as a parameter
        int port = Integer.parseInt(args[0]);
        //set up port
        port(port);

        get("/item", (req, res) -> {
            String json = new ObjectMapper().writeValueAsString(dataMap);
            return json;
        });

        //Add data to map, data is not added if the value already exists in the map.
        post("/item", (req, res) -> {
            String data = req.queryParams("value");

            if(!dataMap.containsValue(data)) {
                dataMap.put(id[0], req.queryParams("value"));
                id[0]++;
            }
            res.redirect("/item");
            return res;
        });

        delete("/item", (req,res) -> {
            dataMap.clear();
            res.redirect("/item");
            return res;
        });

        get("/item/:id", (req,res) -> {
            Integer key = Integer.parseInt(req.params("id"));
            if(dataMap.containsKey(key)) {
                return dataMap.get(key);
            } else {
                res.type("application/json");
                res.status(404);
                return "{\"message\":\"ID does not exist\"}";
            }
        });

        //Update data by id
        put("/item/:id", (req,res) -> {
            Integer key = Integer.parseInt(req.params("id"));
            if(dataMap.containsKey(key)) {
                dataMap.put(Integer.parseInt(req.params("id")), req.queryParams("value"));
                res.redirect("/item");
                System.out.println("After update: " + dataMap.toString());
                return res;
            } else {
                res.type("application/json");
                res.status(404);
                return "{\"message\":\"ID does not exist\"}";
            }

        });

        //delete data by id
        delete("/item/:id", (req,res) -> {
            dataMap.remove(Integer.parseInt(req.params("id")));
            res.redirect("/item");
            return res;
        });



    }
}