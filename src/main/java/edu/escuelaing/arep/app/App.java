package edu.escuelaing.arep.app;
import spark.Request;
import spark.Response;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import static spark.Spark.*;

public class App {
    /**
     * Metodo principal main que se encarga del funcionamiento de toda la clase App.
     * @param args Parametro que indica la lista de los elementos a evaluar.
     */
    public static void main(String[] args) {

        port(getPort());
        get("/inputdata", (req, res) -> inputDataPage(req, res));
        get("/results", (req, res) -> resultsPage(req, res));
    }
    /**
    * @param req Parametro que se encarga de almacenar la informacion de la peticion.
    * @param res Parametro que se encarga de almacenar la informacion de la respuesta del servidor.
    * @return pageContent Retorna la pagina HTML que contiene la interfaz de usuario.
    */
    private static String inputDataPage(Request req, Response res) {
        String pageContent
                = "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "<title>Convertir de Fahrenheit a Celsius</title>"
                + "</head>"
                + "<body>"
                + "<form action=\"/results\">"
                + "<div style=\"padding:30px; width:96.9%; background:#008000; color:white; font-size:400%; position:absolute ; top:0px ; left:0px\">"
                + "Convertir de Fahrenheit a Celsius"
                + "</div>"
                + "<div style=\"padding:30px; width:96.9%; background:#005A80; color:white; font-size:150%; position:absolute ; top:120px ; left:0px\">"
                + "Ingrese el valor que quiere convertir de Fahrenheit a Celsius en el espacio provisto."
                + "</div>"
                + "<input type=\"text\" name='numbers' placeholder=\"Ingrese aquí el valor en Fahrenheit.\" style=\"width:500px ; font-size:150% ; position:absolute ; top:207px ; text-align:center; left:0px \">"
                + "<input type=\"submit\" value=\"Convertir\" style=\"width:253px ; font-size:150% ; position:absolute ; top:242px ; text-align:center; left:0px\">"
                + "<input type=\"reset\" value=\"Limpiar\" style=\"width:254px ; font-size:150% ; position:absolute ; top:242px ; text-align:center; left:254px\">"
                + "<body style = \"background:#005A80\">"
                + "</body>"
                + "</form>"
                + "</body>"
                + "</html>";
        return pageContent;
    }
    /**
    * @param req Parametro que se encarga de almacenar la informacion de la peticion.
    * @param res Parametro que se encarga de almacenar la informacion de la respuesta del servidor.
    * @return pageResponse Retorna la pagina HTML que contiene la interfaz de usuario.
    */
    private static String resultsPage(Request req, Response res) {
        int digit= Integer.parseInt(String.valueOf(req));
        String result="";
        try {
            URL url = new URL("https://sr7u3afts5.execute-api.us-east-1.amazonaws.com/Beta" + "?value=" + digit);
            
            String temp;
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(url.openStream()));
            while ((temp = reader.readLine()) != null) {
                result = result + temp;
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }
    /**
     * Metodo encargado de ejecutar el programa de manera local con un puerto predeterminado.
     * @return Puerto asignado por defecto para ejecutar el programa de forma local, que es 4567.
     */
    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}