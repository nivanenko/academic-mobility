package academ.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileUtil {
    private void setURL(String URL) {
        this.URL = URL;
    }

    private void setUser(String user) {
        this.user = user;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    public String getURL() {
        return URL;
    }

    public String getPassword() {
        return password;
    }

    public String getUser() {
        return user;
    }

    private String URL;
    private String user;
    private String password;
    private Path filePath = Paths.get("D:\\DBconn.txt"); // Here the file's path
    private ArrayList<String> connDetails = new ArrayList<>();

    public void readConnection() {
        try (BufferedReader reader = Files.newBufferedReader
                (filePath, Charset.defaultCharset())) {
            String line;
            while ((line = reader.readLine()) != null) {
                connDetails.add(line);
            }
        } catch (IOException e) {
            System.err.println("IO error: " + e.getMessage());
        }

        setURL(connDetails.get(0));
        setUser(connDetails.get(1));
        setPassword(connDetails.get(2));
    }
}