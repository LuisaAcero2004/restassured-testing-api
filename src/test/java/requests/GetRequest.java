package requests;

import Utilities.SetProperties;
import io.restassured.response.Response;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class GetRequest extends SetProperties {

    String request_token;
    private String token_path = "/authentication/token/new";
    private String list = "/list";

    public GetRequest(){super();}

    public void generateToken(){

        Response response = given()
                .queryParam("api_key",getApi_key())
                .when()
                .get(getBase_url()+ token_path)
                .then()
                .statusCode(200)
                .extract().
                response();

        Assert.assertEquals("true",response.jsonPath().getString("success"));
        this.request_token = response.jsonPath().getString("request_token");
    }

    public void checkMovieStatus(int idMovie,String idList){

        Response response = given()
                .queryParam("api_key",getApi_key())
                .queryParam("movie_id",idMovie)
                .when()
                .get(getBase_url() + list + "/" + idList + "/item_status")
                .then()
                .statusCode(200)
                .extract()
                .response();

        Assert.assertEquals("true",response.jsonPath().getString("item_present"));

    }






}
