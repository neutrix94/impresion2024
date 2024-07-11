
package sistemaimpresion.clases;

//import java.util.List;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import javax.swing.JOptionPane;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import org.json.simple.parser.*;
import sistemaimpresion.form.ConfiguracionFormulario;


public class Configuracion {
    String URL_API;
    public Configuracion() {
        
    }
/*sobrecarga de constructor*/
    public Configuracion( String urlApi ) {
        this.URL_API = urlApi;
    }
    public String obtenerUrlApi(){
        
        return "";
    }
    
    public void consumirApiConfiguracion() throws MalformedURLException, IOException{
        //System.out.println("consumirApiConfiguracion");
        String[] registrosPendientes;
        String urlParaVisitar = URL_API;//info.logArea.append( "URL API : " + urlParaVisitar + "\n" );
        StringBuilder resultado = new StringBuilder();
        URL url = new URL(urlParaVisitar + "/rest/print/obtener_configuracion_impresion");
        HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
        conexion.setRequestMethod("POST");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
        String linea;
        while ((linea = rd.readLine()) != null) {
          resultado.append(linea);
        }
        rd.close();
        String tmp = resultado.toString();//procesa resultados
    }
    
//solicita url del API
    public String solicitarUrlApi() throws IOException, ParseException{
        Object [] sucursales ={ "0 - Seleccionar Sucursal", "1 - Matriz", "2 - San Miguel", "3 - Trojes", "4 - Casa", "5 - Checo",
        "6 - Palma", "7 - Viveros", "8 - Lopez", "9 - Lago", "10 - Centro Urbano", "11 - Satelite" };
        Object opcion = JOptionPane.showInputDialog(null,"Selecciona una sucursal", "Elegir",JOptionPane.QUESTION_MESSAGE,null,sucursales, sucursales[0]);;
        while( opcion.toString().equals( null ) || opcion.toString().equals( "0 - Seleccionar Sucursal" ) ){
            opcion = JOptionPane.showInputDialog(null,"Selecciona una sucursal", "Elegir",JOptionPane.QUESTION_MESSAGE,null,sucursales, sucursales[0]);
        }
//System.out.println("opcion : " + opcion );
        String[] sucursal = opcion.toString().split( " - " );
        //System.exit(0);
        this.URL_API = "";
        Boolean url_valida = false;
        while( url_valida == false ){
            this.URL_API = JOptionPane.showInputDialog( "Ingresa la URL del API de Configuración" );
            if( this.URL_API == "" || ( ! this.URL_API.contains( "http://" ) && ! this.URL_API.contains( "https://" ) ) ){
         JOptionPane.showMessageDialog( null, "La url '" + this.URL_API + "' es incrorrecta en !" );
                url_valida = false;//url = JOptionPane.showInputDialog( "Ingresa la URL del API de Configuración" );
            }else{
                url_valida = this.validaUrlApi( this.URL_API, sucursal );
            }
        }
        return this.URL_API;
    }
    
//validacion de URL de Configuracion de API
    public Boolean validaUrlApi( String url_api, String[] sucursal ) throws MalformedURLException, ProtocolException, IOException, ParseException{
        String[] registrosPendientes;
        String urlParaVisitar = url_api;
        StringBuilder resultado = new StringBuilder();
        URL url = new URL(urlParaVisitar + "/rest/print/obtener_configuracion_impresion" );
        try{
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("POST");
            //conexion.setRequestProperty("Content-Type", "multipart/form-data;id_sucursal=" + 1);
            String urlParameters = "id_sucursal=" + sucursal[0];
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
        }catch( Exception e ){
            JOptionPane.showMessageDialog( null, "La url '" + url + "' es incrorrecta!" + resultado );
            return false;
        }
       //procesa resultados
        String tmp = resultado.toString();
        System.out.println( "Reg pendientes : " + tmp );
        final JSONObject JArray = new JSONObject(tmp);
        if( JArray.getString("message").equals("ok") ){
            crearArchivoConfiguracion(JArray, sucursal );
            return true;
        }else{
            return false;
        }
    }
    
    public Boolean crearArchivoConfiguracion( JSONObject Arreglo, String[] sucursal ) throws IOException, ParseException{
        String path = "config_impresion.json";
 
        JSONObject json = new JSONObject();
        try {
            json.put( "tipo_impresion", "2");
            json.put( "url_api", this.URL_API );
            json.put( "intervalo_impresion", "3");
            json.put( "intervalo_descarga", "30");
            json.put( "puerto_impresion", "1337");
            json.put( "path_local", "");
            json.put( "id_sucursal", sucursal[0]);
            json.put( "nombre_sucursal", sucursal[1]);
            json.put( "url_api_local", "");
            json.put( "path_windows", "");
            json.put("modulos", Arreglo.getJSONArray( "modulos" ) );
        } catch (JSONException e) {
            e.printStackTrace();
        }
        
        try (PrintWriter out = new PrintWriter(new FileWriter(path))) {
            out.write(json.toString(2));
        } catch (Exception e) {
            e.printStackTrace();
        }
        cargarConfiguracion();
        return true;
    }
    public void cargarConfiguracion() throws FileNotFoundException, IOException, ParseException{
        ConfiguracionFormulario formulario = new ConfiguracionFormulario();
    //leer archivo de configuracion
        JSONParser parser = new JSONParser();
        FileReader reader = new FileReader("config_impresion.json");
        Object obj = parser.parse(reader);
        JSONObject configuraciones = new JSONObject ( obj.toString() );
        String tipo_impresion = configuraciones.getString("tipo_impresion");
        String url_api = configuraciones.getString("url_api");
        String intervalo_impresion = configuraciones.getString("intervalo_impresion");
        String intervalo_descarga = configuraciones.getString("intervalo_descarga");
        String puerto_impresion = configuraciones.getString("puerto_impresion");
        String path_local = configuraciones.getString("path_local");
        String id_sucursal = configuraciones.getString("id_sucursal");
        String nombre_sucursal = configuraciones.getString("nombre_sucursal");
        String url_api_local = configuraciones.getString("url_api_local");
        String path_windows = configuraciones.getString("path_windows");
        String sucursal = id_sucursal + " - " + nombre_sucursal;
       //libreria de verificacion de carpetas
       Carpetas carpetas = new Carpetas( path_local, url_api );
       //consultar api (impresoras)
        //String urlParaVisitar = URL_API;//info.logArea.append( "URL API : " + urlParaVisitar + "\n" );
/*Oscar para actualizar modulos de impresion*/
        String urlParaVisitar = url_api;
        StringBuilder resultado = new StringBuilder();
        URL url = new URL(urlParaVisitar + "/rest/print/obtener_configuracion_impresion" );
        try{
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("POST");
            //conexion.setRequestProperty("Content-Type", "multipart/form-data;id_sucursal=" + 1);
            String urlParameters = "id_sucursal=" + id_sucursal;
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
        }catch( Exception e ){
            JOptionPane.showMessageDialog( null, "La url '" + url + "' es incrorrecta!" + resultado );
            System.exit(0);
        }
       //procesa resultados
        String tmp = resultado.toString();
        //System.out.println( "Reg pendientes : " + tmp );
        final JSONObject JArray = new JSONObject(tmp);
        JSONArray modulos_nuevos = new JSONArray( JArray.getJSONArray("modulos") );
       // System.out.println("Nuevos modulos : ");
        //System.out.println(modulos_nuevos);
        JSONArray modulos_anteriores = new JSONArray( configuraciones.getJSONArray("modulos") );
        for (int i = 0; i < modulos_nuevos.length(); i++) {
            JSONObject contenido_modulos = new JSONObject ( modulos_nuevos.get(i).toString() );
        /*Comparacion para ver si el modulo estaba habilitado*/
            Boolean habilitado = false;//( contenido_modulos.getString("habilitado").equals("1") ? true : false );
            for (int j = 0; j < modulos_anteriores.length(); j++) {
                JSONObject contenido_anterior = new JSONObject ( modulos_anteriores.get(j).toString() );
                if( contenido_anterior.getString("id").equals( contenido_modulos.getString("id") )
                    && contenido_anterior.getString("tipo").equals( contenido_modulos.getString("tipo") )
                    && contenido_anterior.getString("habilitado").equals( "1" ) ){
                    habilitado = true;
                }
            }
            String comando = contenido_modulos.getString("comando_impresion");
            String comando_ = comando;
            String id_modulo = null;
            if (contenido_modulos.has("id")) {
                id_modulo = contenido_modulos.getString("id");
            } 
            if( ! "".equals(path_windows) ){//reemplaza ruta Sumatra en Windows
                comando_ = comando.replace( "WINDOWS___ROUTE", path_windows);
            }
            formulario.modelo_tabla_impresoras.addRow( new Object[]{
            contenido_modulos.getString("nombre_modulo"),
            contenido_modulos.getString("usuario"),
            contenido_modulos.getString("ruta"),
            contenido_modulos.getString("impresora"),
            contenido_modulos.getString("extension_archivo"),
            comando_,
            habilitado,
            contenido_modulos.getString("endpoint_api_destino"),
            contenido_modulos.getString("tipo"),
            id_modulo} );
            carpetas.verificacion_existencia_carpeta( contenido_modulos.getString("ruta") );
        }
/*Oscar para actualizar modulos de impresion*/
/*Esta era la version anterior*/
    //carpetas generales
       /* JSONArray modulos = new JSONArray( configuraciones.getJSONArray("modulos") );
        System.out.println("Viejos modulos : ");
        System.out.println(modulos);
        for (int i = 0; i < modulos.length(); i++) {
            JSONObject contenido_modulos = new JSONObject ( modulos.get(i).toString() );
            Boolean habilitado = ( contenido_modulos.getString("habilitado").equals("1") ? true : false );
            String comando = contenido_modulos.getString("comando_impresion");
            String comando_ = comando;
            String id_modulo = null;
            if (contenido_modulos.has("id")) {
                id_modulo = contenido_modulos.getString("id");
            } 
            if( ! "".equals(path_windows) ){//reemplaza ruta Sumatra en Windows
                comando_ = comando.replace( "WINDOWS___ROUTE", path_windows);
            }
            formulario.modelo_tabla_impresoras.addRow( new Object[]{
            contenido_modulos.getString("nombre_modulo"),
            contenido_modulos.getString("usuario"),
            contenido_modulos.getString("ruta"),
            contenido_modulos.getString("impresora"),
            contenido_modulos.getString("extension_archivo"),
            comando_,
            habilitado,
            contenido_modulos.getString("endpoint_api_destino"),
            contenido_modulos.getString("tipo"),
            id_modulo} );
            carpetas.verificacion_existencia_carpeta( contenido_modulos.getString("ruta") );
        }*/
        if( tipo_impresion.equals( "2" ) ){
            //formulario.tipo_impresion_1.setSelected( false );
            formulario.tipo_impresion_2.setSelected( true );
            formulario.tipo_impresion_3.setSelected( false );
        }else if( tipo_impresion.equals( "3" ) ){
            //formulario.tipo_impresion_1.setSelected( false );
            formulario.tipo_impresion_2.setSelected( false );
            formulario.tipo_impresion_3.setSelected( true );
        }else{
            formulario.tipo_impresion_2.setSelected( false );
            formulario.tipo_impresion_3.setSelected( false );
        }
        formulario.path_api_local.setText( url_api_local );
        formulario.url_api_general.setText( url_api );
        formulario.intervalo_local.setText( intervalo_impresion );
        formulario.intervalo_busqueda.setText( intervalo_descarga );
        formulario.puerto_impresion.setText( puerto_impresion );
        formulario.path_local.setText( path_local );
        formulario.path_windows.setText( path_windows );
        formulario.id_sucursal.setSelectedItem(sucursal); //setText( id_sucursal );
        formulario.setVisible(true);
    }
    
    public static HashMap<String, Object> obtenerValoresConfiguracion(  ) throws FileNotFoundException, IOException, ParseException{
        ConfiguracionFormulario formulario = new ConfiguracionFormulario();
        HashMap<String, Object> arreglo_configuracion = new HashMap<>();
    //leer archivo de configuracion
        JSONParser parser = new JSONParser();
        FileReader reader = new FileReader("config_impresion.json");
        Object obj = parser.parse(reader);
        JSONObject configuraciones = new JSONObject ( obj.toString() );
        //valores.put( configuraciones.getString("tipo_impresion").toString() );
        arreglo_configuracion.put( "tipo_impresion", configuraciones.getString("tipo_impresion"));
        arreglo_configuracion.put( "url_api", configuraciones.getString("url_api"));
        arreglo_configuracion.put( "intervalo_impresion", configuraciones.getString("intervalo_impresion"));
        arreglo_configuracion.put( "intervalo_descarga", configuraciones.getString("intervalo_descarga"));
        arreglo_configuracion.put( "path_local", configuraciones.getString("path_local"));
        arreglo_configuracion.put( "puerto_impresion", configuraciones.getString("puerto_impresion"));
        arreglo_configuracion.put( "nombre_sucursal", configuraciones.getString("nombre_sucursal"));
        arreglo_configuracion.put( "id_sucursal", configuraciones.getString("id_sucursal"));
        arreglo_configuracion.put( "url_api_local", configuraciones.getString("url_api_local"));
        arreglo_configuracion.put( "path_windows", configuraciones.getString("path_windows"));
    //carpetas generales
        JSONArray modulos = new JSONArray( configuraciones.getJSONArray("modulos") );
        HashMap<String, HashMap<String, Object>> arreglo_impresoras = new HashMap<>();
        int contador_impresoras = 0;
        for (int i = 0; i < modulos.length(); i++) {
            JSONObject contenido_modulos = new JSONObject ( modulos.get(i).toString() );
            Boolean habilitado = ( contenido_modulos.getString("habilitado").equals("1") ? true : false );
            HashMap<String, Object> impresora = new HashMap<>();
            if( habilitado == true ){
                String comando = contenido_modulos.getString("comando_impresion");
                String comando_ = comando.replace( "WINDOWS___ROUTE", configuraciones.getString("path_windows"));
                String llave = "impresora_" + contador_impresoras;
                impresora.put( "nombre_modulo", contenido_modulos.getString("nombre_modulo") );            
                impresora.put( "usuario", contenido_modulos.getString("usuario") );
                impresora.put( "ruta", contenido_modulos.getString("ruta") );
                impresora.put( "impresora", contenido_modulos.getString("impresora") );
                impresora.put( "extension_archivo", contenido_modulos.getString("extension_archivo") );
                impresora.put( "comando_impresion", comando_ );//contenido_modulos.getString("comando_impresion")
                impresora.put( "endpoint_api_destino", contenido_modulos.getString("endpoint_api_destino") );
                arreglo_impresoras.put(llave, impresora );
                contador_impresoras ++;
            }
        }
        arreglo_configuracion.put("printers", arreglo_impresoras );
        return arreglo_configuracion;
    }
}
