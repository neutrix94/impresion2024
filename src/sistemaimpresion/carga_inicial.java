package sistemaimpresion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;//librería de archivos
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/*import static sincronizacionsistema.main.main;
import static sincronizacionsistema.main.retardo_inicial;
import static sincronizacionsistema.main.ruta_config;*/

public class carga_inicial {
    
    public static int puerto_sinc;
    public static String ruta_config = "";
    public static Long retardo_inicial;
    //metodo encargado de leer la ruta del archivo de configuracion
    public static void leer_ruta() throws FileNotFoundException, InterruptedException, SQLException {
	   String linea = "";
	   File archivo = new File ("ruta.txt");
           
	   FileReader fr = new FileReader (archivo);
	   BufferedReader br = new BufferedReader(fr);
	   try {
		linea = br.readLine();
                if(linea == null){
                    archivo.delete();
                    //main(null);
                }
                File comprueba_ruta = new File(linea); 
                if (!comprueba_ruta.exists()) {
                   JOptionPane.showMessageDialog(null,"No se encontro el archivo de configuracion con la ruta: " + ruta_config);
                   crea_config();
                }
	   } catch (IOException e) {
		e.printStackTrace();
	   }
           File comprueba_ruta = new File(linea); 
           if (!comprueba_ruta.exists()) {
               JOptionPane.showMessageDialog(null,"No se en contro el archivo de configuracion con la ruta: " + ruta_config);
               crea_config();
           }
	   ruta_config = linea;	   
    }
    
    //Carga datos de impresoras y comando de impresion
    public static void carga_inicial(String ruta_config) throws IOException {
        // System.out.println(ruta_config);
            File archivo = new File (ruta_config);

            File comprueba_ruta = new File(ruta_config); 
            if (!comprueba_ruta.exists()) {
                JOptionPane.showMessageDialog(null,"No se encontro el archivo de configuracion con la ruta: " + ruta_config);
                crea_config();
            }
            FileReader fr = new FileReader (archivo);
            BufferedReader br = new BufferedReader(fr);
            String linea = br.readLine();
            String arreglo [] = linea.split("<>");
            retardo_inicial = Long.parseLong(arreglo[7]);
            puerto_sinc = Integer.parseInt(arreglo[8]);
    }
    
    public static void crea_config() {
	   String ruta_sistema = JOptionPane.showInputDialog("Ingrese la ruta de configuracion del sistema: ");
	   try {
                String ruta_txt = "ruta.txt";
                String contenido = "Contenido de ejemplo";

                File file = new File(ruta_txt);
            // Si el archivo no existe es creado
                if (!file.exists()) {
                    file.createNewFile();
                }
                FileWriter fw = new FileWriter(file);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(ruta_sistema);
                bw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
	   ruta_config = ruta_sistema;
   }   
}
