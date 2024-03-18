
package sistemaimpresion.clases;

import java.io.File;
import java.io.IOException;
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
        File folder = new File(path);
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
                    System.out.println(path + files);
                    String command = comando;
                    command = command.replace("{IMPRESORA}", nombre); 
                    command = command.replace("{ARCHIVO}", path + files);
                    //command = command.replace("\"", "\"");
                    File file = new File(path + files);
                    try{
                        System.out.println(command);
                        informeImpresion.append("\nImprimiendo archivo: " + path + files);//command
                        Process proceso = Runtime.getRuntime().exec(command);
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
