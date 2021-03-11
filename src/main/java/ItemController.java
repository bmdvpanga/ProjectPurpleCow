import static spark.Spark.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class ItemController {

    public ItemController(ItemService itemService) {
        get("/", (req, res) -> "Hello! Please navigate to \"/item\"");

        get("/item", (req, res) -> itemService.getAllItems(), new JsonTransformer());

        //Add data to map, data is not added if the value already exists in the map.
        post("/item", (req, res) -> {
            String value = req.queryParams("value");

            List<Item> items = itemService.getAllItems();
            AtomicBoolean isPresent = new AtomicBoolean(false);

            items.forEach(item -> {
               if(item.getValue().equals(value)) {
                   isPresent.set(true);
               }
            });

            if(!isPresent.get())
                itemService.createItem(req.queryParams("value"));

            res.redirect("/item");
            return res;
        });

        delete("/item", (req,res) -> {
            itemService.deleteAll();
            res.redirect("/item");
            return res;
        });

        get("/item/:id", (req,res) -> {
            Item item = itemService.getItemById(req.params("id"));
            if ( item == null ) {
                res.type("application/json");
                res.status(404);
                return "ID Does not exist";
            } else {
               return item;
            }
        },new JsonTransformer());

        //Update data by id
        put("/item/:id", (req,res) -> {
            Item item = itemService.getItemById(req.params("id"));

            if(item == null) {
                res.type("application/json");
                res.status(404);
                return "ID Does not exist";
            } else {
                itemService.updateItem(req.params("id"), req.queryParams("value"));
                res.redirect("/item");
                return res;
            }
        });

        //delete data by id
        delete("/item/:id", (req,res) -> {
            String id = req.params("id");
            Item item = itemService.getItemById(id);
            if(item == null) {
                res.status(404);
                return "ID Does not exist";
            }
            itemService.deleteItemById(id);
                return "Successfully deleted item with id " + id;
        });
    }


}

