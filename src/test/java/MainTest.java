import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainTest {

    @BeforeClass
    public static void ensureAppIsRunning(){
        String [] args = {"4000"};
        Main.main(args);
    }

    @Test
    public void getIs200() throws IOException {
        HttpURLConnection con = (HttpURLConnection)new URL("http","localhost", 4000, "/item").openConnection();

        int status = con.getResponseCode();
        Assert.assertEquals(200, status);
    }

    @Test
    public void getIs404() throws IOException {
        HttpURLConnection con = (HttpURLConnection)new URL("http","localhost", 4000, "/random").openConnection();
        int status = con.getResponseCode();
        Assert.assertEquals(404, status);
    }

    @Test
    public void validatePost1() throws IOException {
        String POST_ENDPOINT = "http://localhost:4000/item";
        String POST_PARAMS = "value=Test1";
        String EXPECTED = "{\"0\":\"Test1\"}";

        StringBuffer response = new StringBuffer();

        URL obj = new URL(POST_ENDPOINT);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        OutputStream os = con.getOutputStream();
        os.write(POST_PARAMS.getBytes());
        os.flush();
        os.close();
        testHelper(con, EXPECTED);
    }
    @Test
    public void validatePost2() throws IOException {
        String POST_ENDPOINT = "http://localhost:4000/item";
        String POST_PARAMS = "value=Test2";
        String EXPECTED = "{\"0\":\"Test1\",\"1\":\"Test2\"}";

        StringBuffer response = new StringBuffer();

        URL obj = new URL(POST_ENDPOINT);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        OutputStream os = con.getOutputStream();
        os.write(POST_PARAMS.getBytes());
        os.flush();
        os.close();
        testHelper(con, EXPECTED);
    }

    @Test
    public void getbyID() throws IOException {
        HttpURLConnection con = (HttpURLConnection)new URL("http","localhost", 4000, "/item/0").openConnection();

        StringBuffer response = new StringBuffer();
        String EXPECTED = "Test1";
        testHelper(con, EXPECTED);
    }

    @Test
    public void getbyID2() throws IOException {
        HttpURLConnection con = (HttpURLConnection)new URL("http","localhost", 4000, "/item/1").openConnection();
        StringBuffer response = new StringBuffer();
        String EXPECTED = "Test2";

        testHelper(con, EXPECTED);
    }

    public static void testHelper(HttpURLConnection con, String expected) throws IOException {
        int status = con.getResponseCode();
        StringBuffer response = new StringBuffer();

        if (status == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response.toString());
        } else {
            System.out.println("GET request failed");
        }
        con.disconnect();
        Assert.assertEquals(200, status);
        Assert.assertEquals(expected, response.toString());
    }
}

