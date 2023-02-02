package edu.escuelaing.arem.ASE.app;

import java.io.IOException;
import java.util.HashMap;

public class Cache {
 
    static private Cache cache = null;
    
    private static HttpConnection httpConnection = new HttpConnection();
    HashMap<String, String> movies = new HashMap<String,String>();

    private Cache() {
    }

    static public Cache getInstance() {

        if (cache == null) {
            cache = new Cache();
        }
        return cache;
    }

    public String searchMovie(String link, String title) throws IOException{
        if (movies.containsKey(title)){
            return movies.get(title);
        } else{
            String movieBody = httpConnection.searchOnAPI(link);
            movies.put(title, movieBody);
            return movieBody;
        }
    } 
}
