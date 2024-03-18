
package sistemaimpresion.clases;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import javax.swing.JOptionPane;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author oscarmendoza
 */
public class DescargaArchivos {
    private String URL_API;
    private String PATH_LOCAL;
    public DescargaArchivos( String url_api, String path_local ){
        this.URL_API = url_api;
        this.PATH_LOCAL = path_local;
        //System.out.println("URL : " + URL_API);
    }
    
//busqueda de archivos
    public String busqueda_remota_archivos( int id_sucursal ){
        HashMap<String, Object> archivos = new HashMap<String, Object>();
        StringBuilder resultado = new StringBuilder();
        String archivos_descargados = "";
        try{
            URL url = new URL( this.URL_API + "/rest/print/get_print_files" );
            System.out.println("URL : " + url);
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("POST");
            //conexion.setRequestProperty("Content-Type", "multipart/form-data;id_sucursal=" + 1);
            String urlParameters = "destinity_store_id=" + id_sucursal;
        // Enviamos los datos por POST
            conexion.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(conexion.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();
           // conexion.setRequestProperty("id_sucursal", "1");
            BufferedReader rd = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
            String linea;
            while ((linea = rd.readLine()) != null) {
              resultado.append(linea);
            }
            rd.close();
        //recibe los JSON
             //procesa resultados
        String tmp = resultado.toString();
        //System.out.println( "Reg pendientes : " + tmp );
        if( tmp.trim().equals("ok") ){//JArray.getString("message").equals("ok");
            //crearArchivoConfiguracion(JArray );
            //return true;
        }else{
            JSONObject JArray = new JSONObject(tmp);
            JSONArray arreglo_archivos = new JSONArray( JArray.getJSONArray("files") );
            System.out.println(JArray);
            //HashMap<String, Object> a = new (HashMap<String, Object>())JArray;
            System.out.println( "a : " + arreglo_archivos );//arreglo.get(0)
            for( int i = 0; i < arreglo_archivos.length(); i++ ){
                JSONObject archivo = arreglo_archivos.getJSONObject(i);
                String origen = archivo.get("file_origin").toString().replace("http://", "https://");
                String descarga = descarga_archivos( origen, archivo.get("file_destinity").toString(), 
                        archivo.get("file_name").toString() );
                if( descarga.equals("ok") ){
                    archivos_descargados += ( archivos_descargados.equals("") ? "" : "," );
                    archivos_descargados += archivo.get("file_id").toString();
                }
            }
        }
        }catch( Exception e ){
            System.out.println(e);
            //JOptionPane.showMessageDialog( null, "La url '" + this.URL_API + "/print/get_printFiles" + "' es incrorrecta!" );
            //return false;
        }
        if( archivos_descargados != "" ){
            String actualizacion = actualiza_descarga_archivo( archivos_descargados  );
        }
        return "ok";
    } 
//descarga archivos
    public String descarga_archivos( String ruta_origen,String ruta_destino,String nombre ) throws IOException{
        //System.out.println("here : " + ruta_origen + " here :  " + ruta_destino + " here " + nombre );
        ruta_origen+=nombre;
    //Definimos el directorio de descarga del archivo
        try{
            //System.out.println("HERE : :::" + this.PATH_LOCAL + "/" + ruta_destino);
            File dir = new File(this.PATH_LOCAL + "/" + ruta_destino);
            System.out.println(this.PATH_LOCAL + "/" + ruta_destino);
            if (!dir.exists()){
                if (!dir.mkdir()){
                    return "No se encontró el archivo!!!"+ruta_origen+nombre; // no se pudo crear la carpeta de destino
                }
            }
            //System.out.println("HERE IS ___");
            File file = new File(this.PATH_LOCAL + "/" + ruta_destino + "/" + nombre);
            //System.out.println("ok ::: " + ruta_origen);
    //establecemos conexión con la url
            URLConnection conn = new URL(ruta_origen).openConnection();
            conn.connect();
            System.out.println("\nEmpezando descarga: \n");
            System.out.println(">> URL: " + ruta_origen);
            System.out.println(">> Nombre: " + nombre);
            System.out.println(">> tamaño: " + conn.getContentLength() + " bytes");
        //abrimos los streams
            InputStream in = conn.getInputStream();
            OutputStream out = new FileOutputStream(file);
            int b = 0;
        //leeemos y escribimos stream
            while (b != -1) {
                b = in.read();
                if (b != -1)
                out.write(b);
            }
        //cerramos los streams
            out.close();
            in.close();
            return "ok";
        }catch( Exception e ){
            JOptionPane.showMessageDialog( null, "Error : " + e );
            return "no";
        }
    }
    
    public String actualiza_descarga_archivo( String archivos_descargados ){
        StringBuilder resultado = new StringBuilder();
        //ruta_origen+=nombre;
    //Definimos el directorio de descarga del archivo
        try{
            URL url = new URL( this.URL_API + "/rest/print/actualizar_status_archivos" );
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("POST");
            //conexion.setRequestProperty("Content-Type", "multipart/form-data;id_sucursal=" + 1);
            String urlParameters = "ok_rows=" + archivos_descargados;
        // Enviamos los datos por POST
            conexion.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(conexion.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();
           // conexion.setRequestProperty("id_sucursal", "1");
            BufferedReader rd = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
            String linea;
            while ((linea = rd.readLine()) != null) {
              resultado.append(linea);
            }
            rd.close();
            System.out.println("REsultado : " + resultado.toString());
            return "ok";
        }catch( Exception e ){
            JOptionPane.showMessageDialog( null, "Error : " + e );
            return "no";
        }
    }
    
}
