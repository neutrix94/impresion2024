/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemaimpresion.clases;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

/**
 *
 * @author oscarmendoza
 */
public class Carpetas {
    private static String PATH_LOCAL, URL_API;
    public Carpetas( String path, String url_api ){
        this.PATH_LOCAL = path;
        this.URL_API = url_api;
    }
    
    public Boolean recorre_todas_las_carpetas( HashMap<String, HashMap<String, Object>> carpetas ){
        
        return true;
    }
    public Boolean verificacion_existencia_carpeta( String ruta_carpeta ){
        Path carpeta_path = Paths.get( PATH_LOCAL + "/" + ruta_carpeta );
        
        if( !Files.exists( carpeta_path ) ){
            creacion_carpeta( carpeta_path );
            DarPermisosCarpeta( PATH_LOCAL + "/" + ruta_carpeta );
        }
        return true;
    }
    
    public Boolean creacion_carpeta( Path carpeta_path ){
        try{
            Files.createDirectories(carpeta_path);
        }catch( Exception e ){
            
        }
        return true;
    }
    
    public static void DarPermisosCarpeta(String ruta_carpeta ) {
        // Ejecuta el comando chmod para establecer permisos 0777
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("chmod", "0777", ruta_carpeta);
            Process process = processBuilder.start();

            // Espera a que el proceso termine
            int exitCode = process.waitFor();

            if (exitCode == 0) {
                System.out.println("Permisos establecidos con éxito.");
            } else {
                System.out.println("Error al establecer permisos. Código de salida: " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            System.out.println("Error al ejecutar el comando.");
        }
    }

    
    public HashMap<String, HashMap<String, Object>> buscar_actualizacion_carpetas(){
        HashMap<String, HashMap<String, Object>> actualizaciones = new HashMap<String, HashMap<String, Object>>(); 
        
        return actualizaciones;
    }
}
