/*Version Junio 2024*/
package sistemaimpresion;
/*Importaciones*/
import java.io.File;
import java.io.FileNotFoundException;

import java.io.IOException;
import javax.swing.JOptionPane;
import java.net.ServerSocket;
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;
import org.json.simple.parser.ParseException;
import sistemaimpresion.clases.ApiServer;
import sistemaimpresion.clases.BarridoImpresion;
import sistemaimpresion.form.informeImpresiones;

import sistemaimpresion.clases.Configuracion;
import sistemaimpresion.clases.ConfigurationUpdates;
import sistemaimpresion.clases.DescargaArchivos;

public class SistemaImpresion {
//Variables globales
    public static DescargaArchivos descarga_archivos;
    public static String comando, impresora, ruta_config, local_path;
    public static HashMap<String, Object> rutas_tkt = new HashMap<String, Object>();
    
    public static String PATH_LOCAL, URL_API, URL_API_LOCAL, tipo_impresion;
    public static long intervalo_impresion, intervalo_descarga;
    public static int PUERTO_IMPRESION;
    private static ServerSocket SERVER_SOCKET;
    public static int id_sucursal, antiguedad_archivos;
    public static HashMap<String, Object> CONFIGURACION_GLOBAL = new HashMap<String, Object>();
    public static DefaultTableModel modelo_tabla_impresoras;
    
    public static Long tiempo_inicio, tiempo_final, tiempo_transcurrido = (long)0;

    public static void main(String[] args) throws InterruptedException, IOException, FileNotFoundException, ParseException {
        /*ConfigurationUpdates Cu = new ConfigurationUpdates();
        Cu.comparePrinters();
        System.exit(0);*/
        
        Configuracion c = new Configuracion();//instancia clase de configuracion
        File configuracion = new File("config_impresion.json");//comprueba si ya existe el archivo de configuracion
    	if (!configuracion.exists()) {
            c.solicitarUrlApi();
    	}else {
            CONFIGURACION_GLOBAL = c.obtenerValoresConfiguracion();
            PATH_LOCAL = CONFIGURACION_GLOBAL.get("path_local").toString();
            intervalo_impresion =  Integer.parseInt(CONFIGURACION_GLOBAL.get("intervalo_impresion").toString());
            intervalo_descarga = Integer.parseInt(CONFIGURACION_GLOBAL.get("intervalo_descarga").toString());
            PUERTO_IMPRESION = Integer.parseInt( CONFIGURACION_GLOBAL.get("puerto_impresion").toString() );
            URL_API_LOCAL = CONFIGURACION_GLOBAL.get("url_api_local").toString();
            tipo_impresion = CONFIGURACION_GLOBAL.get("tipo_impresion").toString();
            id_sucursal = Integer.parseInt( CONFIGURACION_GLOBAL.get("id_sucursal").toString() );
            antiguedad_archivos = Integer.parseInt( CONFIGURACION_GLOBAL.get("antiguedad_archivos").toString() );

            URL_API = CONFIGURACION_GLOBAL.get( "url_api" ).toString();
            descarga_archivos = new DescargaArchivos( URL_API, PATH_LOCAL );
//Verifica el puerto dinamico del socket y lo ocupa si esta desocupado
            try{
                SERVER_SOCKET = new ServerSocket(PUERTO_IMPRESION);//ocupa socket
            }catch(IOException x){
                JOptionPane.showMessageDialog(null,
                        "El sistema de impresión ya se encuentra en ejecución");
                System.exit(0);//cierra programa cuando hay otro en ejecucion
            }
            rutas_tkt = (HashMap<String, Object>) CONFIGURACION_GLOBAL.get("printers");
            informeImpresiones i = new informeImpresiones( URL_API, PATH_LOCAL, id_sucursal );
            modelo_tabla_impresoras = (DefaultTableModel) i.tabla_barrido_impresiones.getModel();//.addRow( new Object[]{
            for (HashMap.Entry<String,Object> carpetas : rutas_tkt.entrySet()) {
                    String claveExterna = carpetas.getKey().toString();
                    HashMap<String, Object> carpeta = (HashMap<String, Object>)carpetas.getValue();
                    modelo_tabla_impresoras.addRow( new Object[]{
                        carpeta.get( "ruta" ).toString(),
                        carpeta.get( "impresora" ).toString(),
                        carpeta.get( "extension_archivo" ).toString()
                    });
            }
            i.informeImpresion.append(i.carga_inicial());//muestra ventana de informe de impresion
            i.setVisible(true);
        //hilos
           APIServer();
           i.api_status.setSelected(true);
           i.api_status.setEnabled(false);
           Barrer();
    	}
    }
    
    public static void Barrer() throws InterruptedException, IOException{
            BarridoImpresion BC =  new BarridoImpresion( PATH_LOCAL );
            while(true){
                tiempo_inicio = System.currentTimeMillis();
                for (HashMap.Entry<String,Object> carpetas : rutas_tkt.entrySet()) {
                    String claveExterna = carpetas.getKey().toString();
                    HashMap<String, Object> carpeta = (HashMap<String, Object>)carpetas.getValue();
                    BC.BarreCarpeta( carpeta.get( "comando_impresion" ).toString(), carpeta.get( "impresora" ).toString(), carpeta.get( "ruta" ).toString(),
                            carpeta.get( "extension_archivo" ).toString(), antiguedad_archivos ); 
                }
            //busca si es el caso con descarga
                if( tipo_impresion.equals( "3" ) && (   tiempo_transcurrido >= ( intervalo_descarga * 1000 ) ) ){
                    //System.out.println("Entra en descarga de archivo");
                    descarga_archivos.busqueda_remota_archivos( id_sucursal );
                    //Thread.sleep( 1000);
                    tiempo_transcurrido = (long)0;
                }
                Thread.sleep(intervalo_impresion * 1000 );//intervalo de busqueda
                tiempo_final = System.currentTimeMillis();
                tiempo_transcurrido += ( tiempo_final - tiempo_inicio );
            }
    }
    
    public static void APIServer() throws IOException{
       ApiServer api = new ApiServer( URL_API, PATH_LOCAL, URL_API_LOCAL ); 
       api.iniciarApi();
    }
}