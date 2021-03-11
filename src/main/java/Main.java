import java.util.HashMap;
import com.fasterxml.jackson.databind.ObjectMapper;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        port(3000);
        new ItemController(new ItemService());

    }
}