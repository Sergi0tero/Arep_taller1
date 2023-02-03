package edu.escuelaing.arem.ASE.app;

import java.net.*;
import java.io.*;

/**
 * Web server which user interacts with
 */
public class HttpServer {

    private static final String omdapi = "http://www.omdbapi.com/";
    private static final String APIKEY = "&apikey=d36df8d8";
    private static final String searchTitle = "?t=";
    private static final Cache cache = Cache.getInstance();

    /**
     * This class is the one that creates the web server and runs it on port 35000,
     * it receives the title of the movie and send it to the cache. Then take the result
     * and show it to the user
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        boolean loadPage = false;
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
            }

        boolean running = true;
        while(running) {
            Socket clientSocket = null;
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            clientSocket.getInputStream()));
            String response = "";
            String inputLine, outputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Received: " + inputLine);
                if (inputLine.startsWith("GET /hello?name=")){
                    inputLine = inputLine.replace("GET /hello?name=", "");
                    inputLine = inputLine.replace(" HTTP/1.1", "");
                    response = inputLine;
                } else if (inputLine.contains("GET / HTTP/1.1") || inputLine.contains("GET /favicon.ico HTTP/1.1")){
                    loadPage = true;
                }
                if (!in.ready()) {
                    break;
                }
            }
            if (loadPage){
                loadPage = false;
                outputLine = "HTTP/1.1 200 \r\n" +
                        "Content-Type: text/html \r\n" +
                        "\r\n" +
                        htmlWithForms();
            } else{
                outputLine = "HTTP/1.1 200 \r\n" +
                        "Content-Type: text/html \r\n" +
                        "\r\n" +
                        JSONResponse(response);
            }

            out.println(outputLine);

            out.close();
            in.close();
            clientSocket.close();
        }
        serverSocket.close();
    }

    /**
     * Gets the title of the movie and return the information of the movie
     *
     * @param response title of the movie
     * @return returns th information of the movie
     * @throws IOException
     */
    public static String JSONResponse(String response) throws IOException{
        System.out.println("response" + response);
        return cache.searchMovie(omdapi + searchTitle + response + APIKEY, response);
    }

    /**
     * Create the form where the user can consult the movie
     * @return form
     */
    public static String htmlWithForms(){
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "    <head>\n" +
                "        <title>Form Example</title>\n" +
                "        <meta charset=\"UTF-8\">\n" +
                "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    </head>\n" +
                "    <body>\n" +
                "        <h1>Form with GET</h1>\n" +
                "        <form action=\"/hello\">\n" +
                "            <label for=\"name\">Name:</label><br>\n" +
                "            <input type=\"text\" id=\"name\" name=\"name\"><br><br>\n" +
                "            <input type=\"button\" value=\"Submit\" onclick=\"loadGetMsg()\">\n" +
                "        </form> \n" +
                "        <div id=\"getrespmsg\"></div>\n" +
                "\n" +
                "        <script>\n" +
                "            function loadGetMsg() {\n" +
                "                let nameVar = document.getElementById(\"name\").value;\n" +
                "                const xhttp = new XMLHttpRequest();\n" +
                "                xhttp.onload = function() {\n" +
                "                    document.getElementById(\"getrespmsg\").innerHTML =\n" +
                "                    this.responseText;\n" +
                "                }\n" +
                "                xhttp.open(\"GET\", \"/hello?name=\"+nameVar);\n" +
                "                xhttp.send();\n" +
                "            }\n" +
                "        </script>\n" +
                "\n" +
                "    </body>\n" +
                "</html>";
    }
}