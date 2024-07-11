/*Version 3.1 Actualizacion de Configuracion*/
package sistemaimpresion.clases;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author oscarmendoza
 */
public class ConfigurationUpdates {
    public void comparePrinters() throws IOException, FileNotFoundException, ParseException{
        Configuracion c = new Configuracion();
    //comparacion de impresoras
        HashMap<String, Object> arreglo_configuracion = getCurrentPrinters();
        System.out.println("Arreglo : ");        
        System.out.println(arreglo_configuracion);

    }
    
    public static HashMap<String, Object> getCurrentPrinters() throws FileNotFoundException, IOException, ParseException{
    //Lee archivo para obtener impresoras
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
            //if( habilitado == true ){
                String comando = contenido_modulos.getString("comando_impresion");
                String comando_ = comando.replace( "WINDOWS___ROUTE", configuraciones.getString("path_windows"));
                String llave = "impresora_" + contador_impresoras;
                impresora.put( "nombre_modulo", contenido_modulos.getString("nombre_modulo") );            
                impresora.put( "usuario", contenido_modulos.getString("usuario") );
                impresora.put( "ruta", contenido_modulos.getString("ruta") );
                impresora.put( "impresora", contenido_modulos.getString("impresora") );
                impresora.put( "extension_archivo", contenido_modulos.getString("extension_archivo") );
                impresora.put( "comando_impresion", contenido_modulos.getString("comando_impresion") );
                impresora.put( "endpoint_api_destino", contenido_modulos.getString("endpoint_api_destino") );
                arreglo_impresoras.put(llave, impresora );
                contador_impresoras ++;
            //}
        }
        arreglo_configuracion.put("printers", arreglo_impresoras );
        return arreglo_configuracion;
    }
    
    
    public void getApiPrinters(){
    //consume API para obtener impresoras
        
    }
}
