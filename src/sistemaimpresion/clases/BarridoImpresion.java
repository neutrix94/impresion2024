
package sistemaimpresion.clases;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.Duration;
import java.time.Instant;

import static sistemaimpresion.form.informeImpresiones.informeImpresion;

/**
 *
 * @author oscarmendoza
 */
public class BarridoImpresion {
    private String path_local;
    public BarridoImpresion( String path ) {
        this.path_local = path; 
    }
    
    public void BarreCarpeta(String comando, String nombre, String ruta_tkt, String extension) throws InterruptedException, IOException{
    //Definicion de path
        String path = this.path_local+ "/" +ruta_tkt + "/";
        String files;
        String osName = System.getProperty("os.name");
        File folder = new File(path);
        boolean es_windows = osName.contains("Windows");
        
        if(folder.exists()){
            File[] listOfFiles = folder.listFiles();
            if(listOfFiles.length <= 0) {
        	
            }else{
        	for (int i = 0; i < listOfFiles.length; i++){

            if (listOfFiles[i].isFile()){
                files = listOfFiles[i].getName();
                //if (files.endsWith(".pdf") || files.endsWith(".PDF"))
    /*implementacion Oscar 2024-09-09 para verificar */
                Path path1 = Paths.get(this.path_local+ "/" +ruta_tkt + "/" + files);
                BasicFileAttributes attrs = Files.readAttributes(path1, BasicFileAttributes.class);
            // Obtener la fecha de creación
                FileTime creationTime = attrs.creationTime();
                Instant creationInstant = creationTime.toInstant();
                Instant now = Instant.now();// Obtener la fecha y hora actual
                Duration duration = Duration.between(creationInstant, now);// Calcular la duración entre la fecha de creación y la fecha actual
                
                if (duration.getSeconds() >= 2) {// Verificar si han pasado 2 segundos
                    System.out.println("Han pasado 2 segundos o más desde la creación del archivo.");
                    if (files.endsWith( extension.toUpperCase() ) || files.endsWith( extension.toLowerCase() ))
                    {
                        String command = comando;
                        if( es_windows ){
                          //  System.out.println("SI REEEMPLAZA");
                            path = path.replace("/", "\\"); // Correcto
                        }else{
                          //  System.out.println("NO REEMPLAZA");
                        }
                        //System.out.println(path + files);
                        command = command.replace("{IMPRESORA}", nombre); 
                        command = command.replace("{ARCHIVO}", path + files);

                        //command = command.replace("\"", "\"");
                        File file = new File(path + files);
                        try{
                            //System.out.println(command);
                            informeImpresion.append("\nImprimiendo archivo: " + path + files);//command
                            informeImpresion.append("\nComando: " + command);//command
                            path = path.replace("\\", "\\\\"); // Correcto
                            Process proceso = Runtime.getRuntime().exec(command);
                             String linea;
                            BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
                            while ((linea = reader.readLine()) != null) {
                                //System.out.println(linea);
                            }

                            // Leer la salida de error del proceso (si hay alguna)
                            BufferedReader errorReader = new BufferedReader(new InputStreamReader(proceso.getErrorStream()));
                            while ((linea = errorReader.readLine()) != null) {
                                System.err.println(linea);
                            }
                            proceso.waitFor();
                            if(proceso.exitValue() == 0){
                                file.delete();//elimina archivo
                                informeImpresion.append("\nEliminando archivo: " + path + files + "\n");
                            }else{
                                informeImpresion.append("\nError al imprimir archivo : " + command + " :: " + proceso.exitValue() + "\n");
                            }
                        }catch(IOException ioe){
                            System.out.println("Error al imprimir : " + ioe);
                        }
                    }
                } else {
                    System.out.println("No han pasado 2 segundos desde la creación del archivo.");
                }
            }
            }
            }
        }else{
            informeImpresion.append("La ruta : " + folder + " no existe\n");
        }
        //Thread.sleep(1000);
    }
}