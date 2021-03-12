import java.util.HashMap;
import com.fasterxml.jackson.databind.ObjectMapper;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        //grab port as a parameter
        int port = args.length == 0 ? 3000 : Integer.parseInt(args[0]);
//        //set up port
        port(port);
        new ItemController(new ItemService());

    }
}