package requests;
import Utilities.SetProperties;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import static io.restassured.RestAssured.given;

public class DeleteRequest extends SetProperties {

    private String deleteMovieRate = "/movie/";
    PostRequest postRequest = new PostRequest();

    public DeleteRequest(){
        super();
    }

    public void deleteRating(int idMovie){
        postRequest.createSession();
        Response response = given()
                .queryParam("api_key",getApi_key())
                .queryParam("session_id",postRequest.session_id)
                .when()
                .delete(getBase_url() + deleteMovieRate + idMovie + "/rating")
                .then()
                .statusCode(200)
                .extract()
                .response();
        Assert.assertEquals("The item/record was deleted successfully.", response.jsonPath().getString("status_message"));

    }


}
