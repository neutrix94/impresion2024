/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package sistemaimpresion.form;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.json.JSONArray;
import org.json.JSONObject;
import sistemaimpresion.clases.Configuracion;
import sistemaimpresion.form.informeImpresiones;

/**
 *
 * @author oscarmendoza
 */
public class ConfiguracionFormulario extends javax.swing.JFrame {
        public static Configuracion configuracion;
        public static DefaultTableModel modelo_tabla_impresoras;
    /**
     * Creates new form ConfiguracionFormulario
     */
    public ConfiguracionFormulario() {
        initComponents();
        setLocationRelativeTo(null);
        configuracion = new Configuracion();
        modelo_tabla_impresoras = (DefaultTableModel) tabla_impresoras.getModel();
       // Sucursal sucursal = new Sucursal();
        id_sucursal.addItem("1 - Matriz");
        id_sucursal.addItem("2 - San Miguel");
        id_sucursal.addItem("3 - Trojes");
        id_sucursal.addItem("4 - Casa");
        id_sucursal.addItem("5 - Checo");
        id_sucursal.addItem("6 - Palma");
        id_sucursal.addItem("7 - Viveros");
        id_sucursal.addItem("8 - Lopez");
        id_sucursal.addItem("9 - Lago");
        id_sucursal.addItem("10 - Centro Urbano");
        id_sucursal.addItem("11 - Satélite");
        id_sucursal.addItem("12 - San Bartolo");
        id_sucursal.addItem("13 - Centella");
        id_sucursal.addItem("14 - NuevaSuc");
        //modelo_tabla_impresoras.addRow( new Object[]{"Example 001", } );
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tipo_impresion_2 = new javax.swing.JCheckBox();
        tipo_impresion_3 = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_impresoras = new javax.swing.JTable();
        puerto_impresion = new javax.swing.JTextField();
        url_api_general = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        intervalo_local = new javax.swing.JTextField();
        tipo_impresion_1 = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        path_local = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        intervalo_busqueda = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        id_sucursal = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        path_api_local = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        path_windows = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        antiguedad_archivos = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        tipo_impresion_2.setFont(new java.awt.Font("Noteworthy", 0, 20)); // NOI18N
        tipo_impresion_2.setText("Envia linea a local (NOIP)");
        tipo_impresion_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipo_impresion_2ActionPerformed(evt);
            }
        });

        tipo_impresion_3.setFont(new java.awt.Font("Noteworthy", 0, 20)); // NOI18N
        tipo_impresion_3.setText("Busqueda de Local a Linea");
        tipo_impresion_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipo_impresion_3ActionPerformed(evt);
            }
        });

        tabla_impresoras.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Módulo", "Usuario", "Ruta", "Impresora", "Extensión", "Comando", "Habilitado", "URL API", "Tipo", "Id", "Convertir a img"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabla_impresoras);
        if (tabla_impresoras.getColumnModel().getColumnCount() > 0) {
            tabla_impresoras.getColumnModel().getColumn(9).setResizable(false);
            tabla_impresoras.getColumnModel().getColumn(9).setPreferredWidth(0);
        }

        url_api_general.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                url_api_generalActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Kokonor", 0, 18)); // NOI18N
        jLabel1.setText("Intervalo impresion ( sg ) :");

        jLabel2.setFont(new java.awt.Font("Kokonor", 0, 18)); // NOI18N
        jLabel2.setText("Puerto Impresión :");

        jLabel3.setFont(new java.awt.Font("Kokonor", 0, 18)); // NOI18N
        jLabel3.setText("URL API SINCRONIZACION :");

        intervalo_local.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                intervalo_localActionPerformed(evt);
            }
        });

        tipo_impresion_1.setFont(new java.awt.Font("Noteworthy", 0, 20)); // NOI18N
        tipo_impresion_1.setSelected(true);
        tipo_impresion_1.setText("Impresion Local");
        tipo_impresion_1.setEnabled(false);
        tipo_impresion_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipo_impresion_1ActionPerformed(evt);
            }
        });

        jButton1.setText("Guardar Configuración");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        path_local.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                path_localActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Kokonor", 0, 18)); // NOI18N
        jLabel4.setText("PATH LOCAL :");

        jLabel5.setFont(new java.awt.Font("Kokonor", 0, 18)); // NOI18N
        jLabel5.setText("Intervalo descarga ( sg ) :");

        jButton2.setText("Cancelar y cerrar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        id_sucursal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                id_sucursalActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Kokonor", 0, 18)); // NOI18N
        jLabel6.setText("PATH SERVIDOR LOCAL :");

        path_api_local.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                path_api_localActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Kokonor", 0, 18)); // NOI18N
        jLabel7.setText("Path Sumatra (Windows) :");

        path_windows.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                path_windowsActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Kokonor", 0, 18)); // NOI18N
        jLabel8.setText("Segundos antigüedad archivos :");

        antiguedad_archivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                antiguedad_archivosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1318, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tipo_impresion_2, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(tipo_impresion_3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tipo_impresion_1, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(id_sucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(148, 148, 148))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(207, 207, 207))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(75, 75, 75)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(path_local, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(url_api_general, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(path_api_local, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(37, 37, 37))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(160, 160, 160)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel8))
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(puerto_impresion, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(intervalo_local, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(intervalo_busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(path_windows, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(antiguedad_archivos, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 631, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 631, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(34, 34, 34))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(intervalo_local, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(url_api_general, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(intervalo_busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tipo_impresion_1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tipo_impresion_2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(path_local, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(path_api_local, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tipo_impresion_3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(id_sucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(puerto_impresion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(path_windows, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(antiguedad_archivos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 23, Short.MAX_VALUE)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tipo_impresion_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipo_impresion_2ActionPerformed
        if( tipo_impresion_2.isSelected() ){
            tipo_impresion_3.setSelected(false );
        }
    }//GEN-LAST:event_tipo_impresion_2ActionPerformed

    private void tipo_impresion_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipo_impresion_3ActionPerformed
        if( tipo_impresion_3.isSelected() ){
            tipo_impresion_2.setSelected(false );
        }
    }//GEN-LAST:event_tipo_impresion_3ActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
      /*  try {
            informeImpresiones i = new informeImpresiones();
            i.setVisible( true );
            this.setVisible( false );
        } catch (InterruptedException ex) {
            Logger.getLogger(ConfiguracionFormulario.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
                                     
       /* try {
            informeImpresiones i = new informeImpresiones();
            i.setVisible( true );
            this.setVisible( false );
        } catch (InterruptedException ex) {
            Logger.getLogger(ConfiguracionFormulario.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }//GEN-LAST:event_formWindowClosing

    private void url_api_generalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_url_api_generalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_url_api_generalActionPerformed

    private void intervalo_localActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_intervalo_localActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_intervalo_localActionPerformed

    private void tipo_impresion_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipo_impresion_1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tipo_impresion_1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        DefaultTableModel modelo_tabla_impresoras = (DefaultTableModel) tabla_impresoras.getModel();
        JSONArray arreglo_impresoras = new JSONArray();
        //JSONArray arreglo_impresoras_especificas = new JSONArray();
        int especificos = 0;
        for (int i = 0; i < modelo_tabla_impresoras.getRowCount(); i++) {
            JSONObject fila = new JSONObject();
            String habilitado = ( tabla_impresoras.getValueAt( i, 6 ).equals(true) ? "1" : "0" );
            String convertir_a_img = ( tabla_impresoras.getValueAt( i, 10 ).equals(true) ? "1" : "0" );
            
            fila.put( "nombre_modulo", tabla_impresoras.getValueAt( i, 0 ) );
            fila.put( "usuario", tabla_impresoras.getValueAt( i, 1 ) );
            fila.put( "ruta", tabla_impresoras.getValueAt( i, 2 ) );
            fila.put( "impresora", tabla_impresoras.getValueAt( i, 3 ) );
            fila.put( "extension_archivo", tabla_impresoras.getValueAt( i, 4 ) );
            fila.put( "comando_impresion", tabla_impresoras.getValueAt( i, 5 ) );
            fila.put( "habilitado", habilitado );
            fila.put( "endpoint_api_destino", tabla_impresoras.getValueAt( i, 7 ) );
            fila.put( "tipo", tabla_impresoras.getValueAt( i, 8 ) );
            fila.put( "id", tabla_impresoras.getValueAt( i, 9 ) );
            fila.put( "convertir_pdf_a_imagen", convertir_a_img );
            // if( especificos == 0 ){
            arreglo_impresoras.put( fila );
                //}else{
                  //  arreglo_impresoras_especificas.put( fila );
                //}
           // }
        }
    //arreglo_impresoras.put( "" );
        String nombre_configuracion = "config_impresion.json";
        JSONObject configuracion = new JSONObject();
        String tipo_impresion = ( tipo_impresion_2.isSelected() == true ? "2" : ( tipo_impresion_3.isSelected() == true ? "3" : "1" ) );
        configuracion.put("tipo_impresion", tipo_impresion );
        configuracion.put("url_api", url_api_general.getText() );
        configuracion.put("intervalo_impresion", intervalo_local.getText() );
        configuracion.put("intervalo_descarga", intervalo_busqueda.getText() );
        //configuracion.put( "impresoras_especificas", arreglo_impresoras_especificas );
        configuracion.put( "path_local", path_local.getText() );
        configuracion.put( "puerto_impresion", puerto_impresion.getText() );
        String[] sucursal = id_sucursal.getSelectedItem().toString().split(" - ");
        configuracion.put( "id_sucursal", sucursal[0] );
        configuracion.put( "nombre_sucursal", sucursal[1] );
        configuracion.put( "url_api_local", path_api_local.getText() );
        configuracion.put( "path_windows", path_windows.getText() );//path de windows
        configuracion.put( "antiguedad_archivos", ( ! antiguedad_archivos.getText().equals("" ) ? antiguedad_archivos.getText() : "0") );//antigueda de archivos para enviarlos a imprimir ( Oscar 2024-09-09 )
        
        configuracion.put( "modulos", arreglo_impresoras );
        try(FileWriter fileWriter = new FileWriter(nombre_configuracion)){
            //System.out.println("HERE : " + arreglo_impresoras.toString(2));
            fileWriter.write( configuracion.toString(2) );
        } catch (IOException ex) {
            Logger.getLogger(ConfiguracionFormulario.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog( null, "Este programa se va a cerrar, vuelvelo a abrir para regrescar la configuración" );
        System.exit(0);
        //this.dispose();
        //main( null );
    }//GEN-LAST:event_jButton1ActionPerformed

    private void path_localActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_path_localActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_path_localActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.setVisible( false );
    }//GEN-LAST:event_jButton2ActionPerformed

    private void id_sucursalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_id_sucursalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_id_sucursalActionPerformed

    private void path_api_localActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_path_api_localActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_path_api_localActionPerformed

    private void path_windowsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_path_windowsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_path_windowsActionPerformed

    private void antiguedad_archivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_antiguedad_archivosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_antiguedad_archivosActionPerformed

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
            java.util.logging.Logger.getLogger(Configuracion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Configuracion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Configuracion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Configuracion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConfiguracionFormulario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTextField antiguedad_archivos;
    public static javax.swing.JComboBox<String> id_sucursal;
    public static javax.swing.JTextField intervalo_busqueda;
    public static javax.swing.JTextField intervalo_local;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTextField path_api_local;
    public static javax.swing.JTextField path_local;
    public static javax.swing.JTextField path_windows;
    public static javax.swing.JTextField puerto_impresion;
    private javax.swing.JTable tabla_impresoras;
    public static javax.swing.JCheckBox tipo_impresion_1;
    public static javax.swing.JCheckBox tipo_impresion_2;
    public static javax.swing.JCheckBox tipo_impresion_3;
    public static javax.swing.JTextField url_api_general;
    // End of variables declaration//GEN-END:variables
}
