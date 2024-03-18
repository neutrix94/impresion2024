package sistemaimpresion.form;

//import java.awt.SystemTray;
//import java.awt.TrayIcon;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import org.json.simple.parser.ParseException;
import sistemaimpresion.clases.Configuracion;
import sistemaimpresion.clases.DescargaArchivos;

/**
 *
 * @author Neutrix
 */
public class informeImpresiones extends javax.swing.JFrame {
/*Variables globales*/
   /* private ImageIcon imageicon;
    private TrayIcon trayicon;
    private SystemTray systemtray;
    public int minimizado = 0;
    */
    
    private String URL_API;
    private String PATH_LOCAL;
    private int ID_SUCURSAL;
    
    public informeImpresiones( String url_api, String path_local, int id_sucursal ) throws InterruptedException {
       // imageicon = new ImageIcon(this.getClass().getResource("impresion-png.png"));
        initComponents();
        //this.setIconImage(imageicon.getImage());
        setLocationRelativeTo(null);
        this.URL_API = url_api;
        this.PATH_LOCAL = path_local;
        this.ID_SUCURSAL = id_sucursal;
        //tray_icon();
    }
    
   /* private void tray_icon(){
        trayicon = new TrayIcon(imageicon.getImage(),"Printing Server V1.1 (Developed by Graftware 2020)", popup);
        trayicon.setImageAutoSize(true);
        systemtray = SystemTray.getSystemTray();
    }
    public void envia_barra_tareas(){
        try{
            if(SystemTray.isSupported() && minimizado == 0){
                systemtray.add(trayicon);
                this.setVisible(false);
                minimizado = 1;
            }else{
                minimizado = 0;
            }
        }catch(Exception e){
            
        }
    }*/
    public  void Inicia(String comando, String nombre, String ruta_tkt, String extension) throws InterruptedException, IOException{
    //Definicion de path
        String path = ruta_tkt;
//
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
        Thread.sleep(1000);
    }
    
    public static String carga_inicial(){
        String msg="                               Development by Graftware"; 
        msg += "                   _\n" +
"                  /`\\\n" +
"           _n   /` , `\\\n" +
"          |  |/` /` `\\ `\\---------,________\n" +
"          | /` /;-----;\\ `\\        `\\      `\\\n" +
"          /` /` ||_|_|| `\\ `\\        `\\      `\\\n" +
"        /` /`   ||_|_||   `\\ `\\________`\\______`\\\n" +
"      /`_/`     '-----'     `\\_`\\-------;      |\"\n" +
"      \"| .---.   .---.   .---. |  .--.  | .--. |\n" +
"       | |T_T|   |T_T|   |T_T| |  |LI|  | |LI| |\n" +
"       | |L_I|   |L_I|   |L_I| |  |LI|  | |LI| |\n" +
"       | '---'   '---' _.--._' |  '--'  | '--' |\n" +
"      _|____________,-\" _.._ \"-;________|______|_\n" +
"     /______________,-\" __ \"-.__________________\\\n" +
"     \"||   .----.   ||  |LI|  ||  .--.  | .--. ||\"\n" +
"      ||   |LILI|   ||  | .|  ||  |LI|  | |LI| ||\n" +
"      ||   |LILI|  _JL_ |  | _JL_ |LI|  | |LI| ||\n" +
"      ||   '----'  |\"\"|_|__|_|\"\"| '--'  | '--' ||\n" +
"      ||TTTTTTTTTTT|  |======|  |TTTTTTTTTTTTTTTT\n" +
"      ||||||||||||||  |======|  |||||||||||||||||\n" +
"     ^^^^^^^^^^^^^^^^^^      ^^^^^^^^^^^^^^^^^^^^^\n\n\nCarga finalizada...\n";
      msg = "";
      return msg;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popup = new java.awt.PopupMenu();
        primar_plano = new java.awt.MenuItem();
        cierra_ventana = new java.awt.MenuItem();
        jMenu1 = new javax.swing.JMenu();
        jScrollPane1 = new javax.swing.JScrollPane();
        informeImpresion = new javax.swing.JTextArea();
        boton_configuracion = new javax.swing.JButton();
        jProgressBar1 = new javax.swing.JProgressBar();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla_barrido_impresiones = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        boton_descarga_manual = new javax.swing.JButton();
        api_status = new javax.swing.JRadioButton();

        popup.setLabel("popupMenu1");

        primar_plano.setLabel("Mostrar Ventana");
        primar_plano.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                primar_planoActionPerformed(evt);
            }
        });
        popup.add(primar_plano);

        cierra_ventana.setLabel("Salir");
        cierra_ventana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cierra_ventanaActionPerformed(evt);
            }
        });
        popup.add(cierra_ventana);

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Servidor Impresion V3.0 (Desarrollado por Grafware 2024) Visita graftware.com");
        setBackground(new java.awt.Color(204, 204, 255));
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowIconified(java.awt.event.WindowEvent evt) {
                formWindowIconified(evt);
            }
        });

        informeImpresion.setBackground(new java.awt.Color(0, 0, 0));
        informeImpresion.setColumns(20);
        informeImpresion.setForeground(new java.awt.Color(0, 153, 0));
        informeImpresion.setRows(5);
        jScrollPane1.setViewportView(informeImpresion);

        boton_configuracion.setBackground(new java.awt.Color(0, 75, 207));
        boton_configuracion.setFont(new java.awt.Font("Krungthep", 0, 13)); // NOI18N
        boton_configuracion.setText("Configuración");
        boton_configuracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_configuracionActionPerformed(evt);
            }
        });

        tabla_barrido_impresiones.setFont(new java.awt.Font("Krungthep", 0, 12)); // NOI18N
        tabla_barrido_impresiones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Ruta", "Impresora", "Extension"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tabla_barrido_impresiones);

        jLabel1.setFont(new java.awt.Font("Krungthep", 0, 18)); // NOI18N
        jLabel1.setText("Sistema de Impresión V3.0");

        jLabel2.setFont(new java.awt.Font("Krungthep", 0, 18)); // NOI18N
        jLabel2.setText("Descarga de Archivos :");

        boton_descarga_manual.setBackground(new java.awt.Color(204, 204, 204));
        boton_descarga_manual.setFont(new java.awt.Font("Krungthep", 0, 13)); // NOI18N
        boton_descarga_manual.setText("Descarga Remota");
        boton_descarga_manual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_descarga_manualActionPerformed(evt);
            }
        });

        api_status.setText("API Encendida");
        api_status.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                api_statusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(boton_descarga_manual, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(boton_configuracion, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 935, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(228, 228, 228)
                                .addComponent(api_status))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 935, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(api_status))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(boton_configuracion, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(boton_descarga_manual, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void primar_planoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_primar_planoActionPerformed
        /*if(minimizado == 1){
            systemtray.remove(trayicon);
            this.setVisible(true);
            this.toFront();
            return;
        }*/
    }//GEN-LAST:event_primar_planoActionPerformed

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        
    }//GEN-LAST:event_formWindowGainedFocus
    
    private void formWindowIconified(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowIconified
        //envia_barra_tareas();
    }//GEN-LAST:event_formWindowIconified

    private void cierra_ventanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cierra_ventanaActionPerformed
        System.exit(0);
    }//GEN-LAST:event_cierra_ventanaActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        //envia_barra_tareas();
    }//GEN-LAST:event_formWindowClosing

    private void boton_configuracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_configuracionActionPerformed
        Configuracion c = new Configuracion();
        //ConfiguracionFormulario cF = new ConfiguracionFormulario();
        try {
            c.cargarConfiguracion();
        } catch (IOException ex) {
            Logger.getLogger(informeImpresiones.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(informeImpresiones.class.getName()).log(Level.SEVERE, null, ex);
        }
        //cF.setVisible( true );
        //this.setVisible( false );
    }//GEN-LAST:event_boton_configuracionActionPerformed

    private void boton_descarga_manualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_descarga_manualActionPerformed
        DescargaArchivos descarga =  new DescargaArchivos( this.URL_API, this.PATH_LOCAL );
        descarga.busqueda_remota_archivos( this.ID_SUCURSAL );
    }//GEN-LAST:event_boton_descarga_manualActionPerformed

    private void api_statusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_api_statusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_api_statusActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(informeImpresiones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(informeImpresiones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(informeImpresiones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(informeImpresiones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                    //Inicia();
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JRadioButton api_status;
    private javax.swing.JButton boton_configuracion;
    private javax.swing.JButton boton_descarga_manual;
    private java.awt.MenuItem cierra_ventana;
    public static javax.swing.JTextArea informeImpresion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private java.awt.PopupMenu popup;
    private java.awt.MenuItem primar_plano;
    public static javax.swing.JTable tabla_barrido_impresiones;
    // End of variables declaration//GEN-END:variables
}
