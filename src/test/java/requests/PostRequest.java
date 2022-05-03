package requests;

import Utilities.SetProperties;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class PostRequest extends SetProperties {
    JSONObject jsonObject = new JSONObject();
    GetRequest getRequest = new GetRequest();
    String request_token;
    String session_id;
    String list_id;
    private String validateTokenPath = "/authentication/token/validate_with_login";
    private String createSession = "/authentication/session/new";
    private String rateMovie = "/movie/";
    private String list = "/list";



    public PostRequest(){
        super();
    }

    public void validateToken(){
        getRequest.generateToken();
        jsonObject
                .put("username",getUser())
                .put("password",getPassword())
                .put("request_token",getRequest.request_token);

        Response response = given()
                .contentType(ContentType.JSON)
                .queryParam("api_key",getApi_key())
                .body(jsonObject.toString())
                .when()
                .post(getBase_url() + validateTokenPath)
                .then()
                .statusCode(200)
                .extract().response();
        Assert.assertEquals("true",response.jsonPath().getString("success"));
        this.request_token = response.jsonPath().getString("request_token");

    }

    public void createSession(){
        validateToken();
        jsonObject
                .put("request_token",this.request_token);

        Response response = given()
                .contentType(ContentType.JSON)
                .queryParam("api_key",getApi_key())
                .body(jsonObject.toString())
                .when()
                .post(getBase_url() + createSession)
                .then()
                .statusCode(200)
                .extract()
                .response();
        Assert.assertEquals("true", response.jsonPath().getString("success"));
        this.session_id = response.jsonPath().getString("session_id");

    }

    public void rateMovie(int idMovie, int rate){
        createSession();
        jsonObject
                .put("value",rate);
        Response response = given()
                .contentType(ContentType.JSON)
                .queryParam("api_key",getApi_key())
                .queryParam("session_id",this.session_id)
                .body(jsonObject.toString())
                .when()
                .post(getBase_url() + rateMovie + idMovie + "/rating")
                .then()
                .statusCode(201)
                .extract().response();
        Assert.assertEquals("true", response.jsonPath().getString("success"));
    }

    public void createList(String listName, String listDescription, String listLanguage){
        createSession();
        jsonObject
                .put("name",listName)
                .put("description",listDescription)
                .put("language",listLanguage);
        Response response = given()
                .contentType(ContentType.JSON)
                .queryParam("api_key",getApi_key())
                .queryParam("session_id",this.session_id)
                .body(jsonObject.toString())
                .when()
                .post(getBase_url() + list)
                .then()
                .statusCode(201)
                .extract().response();
        this.list_id = response.jsonPath().getString("list_id");
        Assert.assertEquals("true", response.jsonPath().getString("success"));
        Assert.assertEquals("The item/record was created successfully.", response.jsonPath().getString("status_message"));

    }

    public void addMovie(int idMovie, String idList){
        createSession();

        jsonObject
                .put("media_id",idMovie);
        Response response = given()
                .contentType(ContentType.JSON)
                .queryParam("api_key",getApi_key())
                .queryParam("session_id",this.session_id)
                .body(jsonObject.toString())
                .when()
                .post(getBase_url() + list + "/"+ idList + "/add_item")
                .then()
                .statusCode(201)
                .extract().response();

        Assert.assertEquals("true", response.jsonPath().getString("success"));

    }

    public void clearList(String idList){
        Response response = given()
                .queryParam("api_key",getApi_key())
                .queryParam("session_id",this.session_id)
                .queryParam("confirm",true)
                .post(getBase_url() + list + "/" + idList + "/clear")
                .then()
                .statusCode(201)
                .extract().response();

        Assert.assertEquals("true", response.jsonPath().getString("success"));
        Assert.assertEquals("The item/record was updated successfully.", response.jsonPath().getString("status_message"));

    }

    public String getList_id() {
        return list_id;
    }

}
