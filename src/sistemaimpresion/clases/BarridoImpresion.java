
package sistemaimpresion.clases;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
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

            if (listOfFiles[i].isFile())
            {
                files = listOfFiles[i].getName();
                //if (files.endsWith(".pdf") || files.endsWith(".PDF"))
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