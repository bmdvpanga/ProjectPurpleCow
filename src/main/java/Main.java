import org.apache.log4j.BasicConfigurator;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        BasicConfigurator.configure();

        //set up port
        port(3000);

        get("/item", (req, res) -> "Hello World");
    }
}