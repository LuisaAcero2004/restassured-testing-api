import org.testng.annotations.Test;
import requests.GetRequest;
import requests.PostRequest;
import requests.DeleteRequest;

public class TMBD {
    @Test
    public void getToken(){
        GetRequest getRequest = new GetRequest();
        getRequest.generateToken();
    }

    @Test
    public void validateToken(){
        PostRequest postRequest = new PostRequest();
        postRequest.validateToken();
    }

    @Test
    public void createSession() {
        PostRequest postRequest = new PostRequest();
        postRequest.createSession();
    }

    @Test
    public void rateMovie() {
        PostRequest postRequest = new PostRequest();
        postRequest.rateMovie(760868, 8);
    }

    @Test
    public void deleteRate() {
        DeleteRequest deleteRequest = new DeleteRequest();
        deleteRequest.deleteRating(760868);
    }

    //Create new list
    @Test
    public void createList(){
        PostRequest postRequest = new PostRequest();
        postRequest.createList("Nombre de la lista","Desc","en");
    }

    //Add new movie to the list
    @Test
    public void addMovieList(){
        PostRequest postRequest = new PostRequest();
        postRequest.createList("Nombre de la lista","Desc","en");
        postRequest.addMovie(760868,postRequest.getList_id());
    }

    //Check item status
    @Test
    public void checkMovieStatus(){
        GetRequest getRequest = new GetRequest();
        PostRequest postRequest = new PostRequest();
        int idMovie = 760868;

        postRequest.createList("Nombre de la lista","Desc","en");
        postRequest.addMovie(idMovie,postRequest.getList_id());
        getRequest.checkMovieStatus(idMovie,postRequest.getList_id());
    }

    //Clear list



    //Delete list



}
