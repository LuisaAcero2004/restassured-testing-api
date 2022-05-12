package Utilities;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class SetProperties {
    private String api_key;
    private String base_url;
    private String user;
    private String password;

    public SetProperties(){
        Properties properties = new Properties();
        try{
            properties.load(new FileReader("src/test/java/resources/data.properties.properties.properties"));
        } catch (IOException e) {
            e.printStackTrace();

        }

        setApi_key(properties.getProperty("api_key"));
        setBase_url(properties.getProperty("base_url"));
        setUser(properties.getProperty("user"));
        setPassword(properties.getProperty("password"));
    }

    public String getApi_key() {
        return api_key;
    }

    public void setApi_key(String api_key) {
        this.api_key = api_key;
    }

    public String getBase_url() {
        return base_url;
    }

    public void setBase_url(String base_url) {
        this.base_url = base_url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
