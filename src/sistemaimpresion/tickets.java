
package sistemaimpresion;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.SQLException;

public class tickets {
   /* conexion_doble conecta;
    private Connection conexion_local,conexion_linea,conexion_extraer,conexion_insertar;
    public int id_sucursal,tiempo_buscar;
    int sincronizando=0;*/
//constructor
    public tickets() throws IOException{
        
    }
//busqueda de tickets
    public String descarga_archivos( String ruta_origen,String ruta_destino,String nombre ) throws IOException{
      ruta_origen+=nombre;
    //Definimos el directorio de descarga del archivo
        File dir = new File(ruta_destino);
        if (!dir.exists()){
            if (!dir.mkdir()){
                return "No se encontró el archivo!!!"+ruta_origen+nombre; // no se pudo crear la carpeta de destino
            }
        }
        File file = new File(ruta_destino+nombre);
    //establecemos conexión con la url
        URLConnection conn = new URL(ruta_origen).openConnection();
            conn.connect();
            System.out.println("\nempezando descarga: \n");
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
    }
/*
    conecta = new conexion_doble(ruta_conexion);
*/
    /*public String proceso_archivos() throws IOException, SQLException{
    //instanciamos clase de tickets
        String respuesta="ok";
        String [] respuesta_actualizar;
        tickets tk= new tickets();
        conexion_extraer=conexion_linea;
            
        String sql="SELECT id_archivo,nombre_archivo,ruta_origen,ruta_destino"
                + " FROM sys_archivos_descarga WHERE descargado=0 AND id_sucursal="+id_sucursal;
        
        String[] resp=conecta.consultar(sql, conexion_extraer, "sincronizacion de archivos").split("°");
    //System.out.println(resp.length+"\n"+resp[0]);System.exit(0);
       if("0".equals(resp[0])){
           return "ok";
       }    
        //System.out.println("si pasa!!!");
        for(int i=0;i<resp.length;i++){
            String[] resp_2=resp[i].split("~");
            //System.out.println("");
        //mandamos a descargar archhivo
        try{    
            tk.descarga_archivos(resp_2[2],resp_2[3],resp_2[1]);
        }catch(Exception e){
            //System.out.println("Catch descarga archivos: "+e);
        }
        //actuaizamos el registro del archivo como descargado
            sql="UPDATE sys_archivos_descarga SET descargado=1 WHERE id_archivo="+resp_2[0];
            conecta.actualiza(sql, conexion_extraer);
            try{
                conexion_extraer.commit();
            }catch(Exception e){
                return "Error al hacer commit "+e;
            }
        }//fin de for i
        return respuesta;
    }//fin de método de sincronización de archivos*/
}