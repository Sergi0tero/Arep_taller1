package edu.escuelaing.arem.ASE.app;

import java.io.IOException;

public class AppTestThreads extends Thread{
    private static Cache cache = Cache.getInstance();
    private String link;
    private String title;
    private String res;

    public AppTestThreads(String link, String title){
        this.link = link;
        this.title = title;
    }

    @Override
    public void run(){
        try {
            res = cache.searchMovie(link, title);
        } catch (IOException e) {
            throw new RuntimeException("Fallo al buscar pelicula");
        }
    }

    public String getRes(){
        return res;
    }
}
