package sistemaimpresion.clases;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.BufferedReader;
//import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
//import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;

import java.util.HashMap;
import javax.swing.JOptionPane;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ApiServer {

    public static String URL_API, PATH_LOCAL, URL_API_LOCAL;//= //"http://sistemageneralcasa.com/desarrollo_linea_2024";
    
    public ApiServer(String url_api, String path_local, String url_api_local) throws IOException {
        this.URL_API = url_api;
        this.PATH_LOCAL = path_local;
        this.URL_API_LOCAL = url_api_local;
    }

    public static void iniciarApi() throws IOException {
        int port = 8090;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/api/recibir_archivo", new SaludoHandler());
        server.setExecutor(null);
        server.start();
        System.out.println("Servidor iniciado en el puerto " + port);
    }

    static class SaludoHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            try {
                if ("POST".equals(exchange.getRequestMethod())) {
                    String ok_rows = "";
                    DescargaArchivos descargar = new DescargaArchivos(URL_API, PATH_LOCAL);
                    System.out.println("here");
                    InputStream inputStream = exchange.getRequestBody();
                    String jsonInput = readInputStream(inputStream);

                    final JSONObject JArray = new JSONObject(jsonInput.toString());
                    System.out.println(jsonInput);
                    if (JArray.get("files").toString().equals("null") || JArray.get("files").toString().equals(null)) {//no hay archivos
                        System.out.println("ES NULLO");
                    } else {
                        final JSONArray files = new JSONArray(JArray.get("files").toString());
                        JSONObject detalle_file = new JSONObject(files.get(0).toString());
                        System.out.println("Detalle : " + detalle_file.getString("file_name"));
                        System.out.println("Detalle : " + detalle_file.getString("file_destinity"));
                        
                        String descarga = descargar.descarga_archivos( URL_API_LOCAL + "/" + detalle_file.getString("file_destinity"),
                                detalle_file.getString("file_destinity"), detalle_file.getString("file_name"));
                        if( descarga == "ok" ){
                            ok_rows += ( ok_rows.equals( "" ) ? "" : "," );
                            ok_rows += detalle_file.getString("file_id");
                        }
                    }
                    String jsonResponse = "{\"ok_rows\": \"" + ok_rows + "\"}";// Crear un objeto JSON manualmente
                    // Configurar la respuesta HTTP con el tipo de contenido JSON
                    exchange.getResponseHeaders().set("Content-Type", "application/json");

                    exchange.getResponseHeaders().set("Content-Length", String.valueOf(jsonResponse.length()));
                    exchange.sendResponseHeaders(200, 0);//jsonResponse.length()
                    OutputStream os = exchange.getResponseBody();// Obtener el flujo de salida para la respuesta
                    os.write(jsonResponse.getBytes());
                    os.close();// Cerrar el flujo de salida
                } else {
                    // Manejar otros métodos (puedes agregar más lógica según tus necesidades)
                    exchange.sendResponseHeaders(405, -1); // Método no permitido
                }
            } catch (Exception e) {
                System.out.println("Error : " + e);
            }
        }
        // Método para leer el contenido de un InputStream y convertirlo a una cadena

        private String readInputStream(InputStream inputStream) throws IOException {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            return stringBuilder.toString();
        }
    }

    static class FileUploadHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("POST".equals(exchange.getRequestMethod())) {
                InputStream inputStream = exchange.getRequestBody();// Obtener el flujo de entrada para leer el archivo
                String filePath = "ruta/del/servidor/nombrearchivo.ext";// Especificar la ubicación del archivo en el servidor
                try (OutputStream outputStream = new FileOutputStream(new File(filePath))) {// Guardar el archivo en el servidor
                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                }
            // Enviar respuesta al cliente
                String response = "Archivo recibido con éxito";
                System.out.println(response);
                exchange.sendResponseHeaders(200, 0);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            } else {
                exchange.sendResponseHeaders(405, -1);// Método no permitido
            }
        }
    }

}
