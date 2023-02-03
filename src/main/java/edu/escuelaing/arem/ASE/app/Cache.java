package edu.escuelaing.arem.ASE.app;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This is where the information of the movies is stored
 * so the external API don't have to be called every time
 */
public class Cache {
 
    static private Cache cache = null;
    
    private static final HttpConnection httpConnection = new HttpConnection();
    ConcurrentHashMap<String, String> movies = new ConcurrentHashMap<String,String>();

    /**
     * Constructor
     */
    private Cache() {
    }

    /**
     * If an instance of Cache is already created, it is returned.
     * If not, a new one is created.
     * @return Cache instance
     */
    static public Cache getInstance() {

        if (cache == null) {
            cache = new Cache();
        }
        return cache;
    }

    /**
     * Search the movie, if it's not in the storage, the external API is consulted
     * @param link link to search for it in the api
     * @param title title of the movie
     * @return Information of the movie
     * @throws IOException
     */
    public String searchMovie(String link, String title) throws IOException {
        if (movies.containsKey(title)){
            return movies.get(title);
        } else{
            String movieBody = httpConnection.searchOnAPI(link);
            movies.put(title, movieBody);
            return movieBody;
        }
    }

    /**
     * Verifies if there's a movie in the Cache
     * @param title title of the movie to search
     * @return True if the movie is in the Cache, false if not
     */
    public boolean isInCache(String title){
        return movies.contains(title);
    }
}
