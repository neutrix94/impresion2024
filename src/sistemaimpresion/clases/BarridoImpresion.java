
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
    
    public void BarreCarpeta(String comando, String nombre, String ruta_tkt, String extension, int antiguedad_archivos, Boolean convertir_a_img) throws InterruptedException, IOException{
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

                        File file = new File(path + files);
                        if (files.endsWith( extension.toUpperCase() ) || files.endsWith( extension.toLowerCase() )){
                            if (duration.getSeconds() >= antiguedad_archivos ) {// Verificar si han pasado 2 segundos 
                                System.out.println("Han pasado " + antiguedad_archivos + " segundos o más desde la creación del archivo.");
                                String command = comando;
                                System.out.println("convertir_a_img : " + convertir_a_img + extension.toLowerCase());
                            //implementacion Oscar 2025-05-07 para conversion del archivo a imagen
                                if(extension.toLowerCase().equals("pdf") && convertir_a_img.equals(true) ){
                                    String comando_conversion = "pdftoppm -jpeg {ARCHIVO} {ARCHIVO}";
                                    comando_conversion = comando_conversion.replace("{ARCHIVO}", path + files); //path + files
                                    try{
                                        //System.out.println(command);
                                        informeImpresion.append("Convirtiendo archivo: " + path + files);//commandpath + files
                                        informeImpresion.append("\nComando: " + comando_conversion);//command
                                        System.out.println("\nComando: " + comando_conversion);
                                        path = path.replace("\\", "\\\\"); // Correcto
                                        Process proceso_conversion = Runtime.getRuntime().exec(comando_conversion);
                                        String linea;
                                        BufferedReader reader = new BufferedReader(new InputStreamReader(proceso_conversion.getInputStream()));
                                        while ((linea = reader.readLine()) != null) {
                                            //System.out.println(linea);
                                        }
                                        System.out.println("Pasa 1");
                                    // Leer la salida de error del proceso (si hay alguna)
                                        BufferedReader errorReader = new BufferedReader(new InputStreamReader(proceso_conversion.getErrorStream()));
                                        while ((linea = errorReader.readLine()) != null) {
                                            System.err.println(linea);
                                        }
                                        proceso_conversion.waitFor();
                                        System.out.println("Pasa 2");
                                        
                                        if(proceso_conversion.exitValue() == 0){
                                            file.delete();//elimina archivo
                                            informeImpresion.append("\nEliminando archivo: " + path + files + "\n");
                                        }else{
                                            informeImpresion.append("\nError al convertir archivo : " + command + " :: " + proceso_conversion.exitValue() + "\n");
                                        }
                                       // Thread.sleep(500);
                                        files = files += "-1.jpg";//actualiza el nombre del archivo
                                        System.out.println("files : " + files);
                                    }catch(IOException ioe){
                                        System.out.println("Error al convertir : " + ioe);
                                    }
                                    
                                }
                                if( es_windows ){
                                  //  System.out.println("SI REEEMPLAZA");
                                    path = path.replace("/", "\\"); // Correcto
                                }else{
                                  //  System.out.println("NO REEMPLAZA");
                                }
                                //System.out.println(path + files);
                                command = command.replace("{IMPRESORA}", nombre); 
                                command = command.replace("{ARCHIVO}", path + files);//path + 

                                //command = command.replace("\"", "\"");
                              //  File file = new File(path + files);
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
                            }else {
                                System.out.println("No han pasado " + antiguedad_archivos + " segundos desde la creación del archivo.");
                            }
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