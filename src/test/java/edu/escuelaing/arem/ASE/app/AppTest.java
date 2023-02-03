package edu.escuelaing.arem.ASE.app;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase{

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Should find the given movies
     * @throws IOException
     * @throws InterruptedException
     */
    public void testDeberiaEncontrarPelicula() throws InterruptedException {
        ArrayList<AppTestThreads> threads = new ArrayList<AppTestThreads>();
        threads.add(new AppTestThreads("http://www.omdbapi.com/?t=Messi&apikey=d36df8d8", "Messi"));
        threads.add(new AppTestThreads("http://www.omdbapi.com/?t=maximus&apikey=d36df8d8", "Maximus"));
        threads.add(new AppTestThreads("http://www.omdbapi.com/?t=hotline&apikey=d36df8d8", "Hotline"));
        for (AppTestThreads t : threads){
            t.start();
        }
        for (AppTestThreads t : threads){
            t.join();
        }
        assertEquals(threads.get(0).getRes(), "{\"Title\":\"Messi\",\"Year\":\"2014\",\"Rated\":\"TV-Y\",\"Released\":\"01 Jan 2015\",\"Runtime\":\"93 min\",\"Genre\":\"Documentary, Biography, Sport\",\"Director\":\"Álex de la Iglesia\",\"Writer\":\"Jorge Valdano Sáenz, Jorge Valdano\",\"Actors\":\"Lionel Messi, Johan Cruijff, Kike Domínguez\",\"Plot\":\"Lionel Messi from early life to international stardom.\",\"Language\":\"Spanish\",\"Country\":\"Spain\",\"Awards\":\"1 nomination\",\"Poster\":\"https://m.media-amazon.com/images/M/MV5BZDA1NDdkM2UtM2M2ZS00NzJjLTk4YmQtNDhlYjM2MWVlOWE5XkEyXkFqcGdeQXVyMTA0MjU0Ng@@._V1_SX300.jpg\",\"Ratings\":[{\"Source\":\"Internet Movie Database\",\"Value\":\"7.2/10\"}],\"Metascore\":\"N/A\",\"imdbRating\":\"7.2\",\"imdbVotes\":\"4,127\",\"imdbID\":\"tt3538766\",\"Type\":\"movie\",\"DVD\":\"24 Feb 2018\",\"BoxOffice\":\"N/A\",\"Production\":\"N/A\",\"Website\":\"N/A\",\"Response\":\"True\"}");
        assertEquals(threads.get(1).getRes(), "{\"Title\":\"Maximus\",\"Year\":\"2021\",\"Rated\":\"N/A\",\"Released\":\"N/A\",\"Runtime\":\"16 min\",\"Genre\":\"Short, Action, Drama\",\"Director\":\"Richard Prendergast\",\"Writer\":\"Richard Prendergast\",\"Actors\":\"Albert Clogston, Daniel Connor, Gaynor Fraser\",\"Plot\":\"A young warrior's fight for his life, told through the eyes of his younger sister. Infused with imagination, tragedy and magic.\",\"Language\":\"English\",\"Country\":\"United Kingdom\",\"Awards\":\"1 win & 6 nominations\",\"Poster\":\"https://m.media-amazon.com/images/M/MV5BOWMwNGJhOTYtZTllNC00MDYxLTliYTktODllNzFiYzAwYmJjXkEyXkFqcGdeQXVyNTg1Mjc3NTA@._V1_SX300.jpg\",\"Ratings\":[],\"Metascore\":\"N/A\",\"imdbRating\":\"N/A\",\"imdbVotes\":\"16\",\"imdbID\":\"tt13198146\",\"Type\":\"movie\",\"DVD\":\"N/A\",\"BoxOffice\":\"N/A\",\"Production\":\"N/A\",\"Website\":\"N/A\",\"Response\":\"True\"}");
        assertEquals(threads.get(2).getRes(), "{\"Title\":\"Hotline\",\"Year\":\"1982\",\"Rated\":\"Not Rated\",\"Released\":\"16 Oct 1982\",\"Runtime\":\"96 min\",\"Genre\":\"Drama, Horror, Thriller\",\"Director\":\"Jerry Jameson\",\"Writer\":\"David E. Peckinpah, Stancil E.D. Johnson\",\"Actors\":\"Lynda Carter, Steve Forrest, Granville Van Dusen\",\"Plot\":\"A beautiful telephone operator is stalked by a murderous madman.\",\"Language\":\"English\",\"Country\":\"United States\",\"Awards\":\"N/A\",\"Poster\":\"https://m.media-amazon.com/images/M/MV5BY2YxY2I1MzctYmNiYy00NTBmLWFkMTctZjc2MDFjZTBiMDAwXkEyXkFqcGdeQXVyMzU0NzkwMDg@._V1_SX300.jpg\",\"Ratings\":[{\"Source\":\"Internet Movie Database\",\"Value\":\"5.9/10\"}],\"Metascore\":\"N/A\",\"imdbRating\":\"5.9\",\"imdbVotes\":\"343\",\"imdbID\":\"tt0084095\",\"Type\":\"movie\",\"DVD\":\"N/A\",\"BoxOffice\":\"N/A\",\"Production\":\"N/A\",\"Website\":\"N/A\",\"Response\":\"True\"}");
    }

    /**
     * Shouldn't find the given movies
     * @throws InterruptedException
     */
    public void testNoDeberiaEncontrarPelicula() throws InterruptedException {
        ArrayList<AppTestThreads> threads = new ArrayList<AppTestThreads>();
        threads.add(new AppTestThreads("http://www.omdbapi.com/?t=arrozconleche&apikey=d36df8d8", "arrozconleche"));
        threads.add(new AppTestThreads("http://www.omdbapi.com/?t=probando&apikey=d36df8d8", "probando"));
        threads.add(new AppTestThreads("http://www.omdbapi.com/?t=noDebeServir&apikey=d36df8d8", "noDebeServir"));
        for (AppTestThreads t : threads){
            t.start();
        }
        for (AppTestThreads t : threads){
            t.join();
        }
        assertEquals(threads.get(0).getRes(), "{\"Response\":\"False\",\"Error\":\"Movie not found!\"}");
        assertEquals(threads.get(1).getRes(), "{\"Response\":\"False\",\"Error\":\"Movie not found!\"}");
        assertEquals(threads.get(2).getRes(), "{\"Response\":\"False\",\"Error\":\"Movie not found!\"}");
    }

    public void testEstaEnCache(){
        
    }

    public void testNoEstaEnCache(){

    }
}
