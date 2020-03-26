import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {


        try {
            URL url = new URL("http://jsonplaceholder.typicode.com/posts/");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int responseCode = connection.getResponseCode();

            System.out.println("Response code is: " + responseCode);
            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {
                Scanner scanner = new Scanner(url.openStream());
                StringBuilder line = new StringBuilder();

                while (scanner.hasNext()) {
                    line.append(scanner.nextLine());
                }
                String jsonArray = line.toString();

                ObjectMapper objectMapper = new ObjectMapper();
                List<Post> posts = objectMapper.readValue(jsonArray, new TypeReference<List<Post>>() {});
                posts.forEach(System.out::println);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

