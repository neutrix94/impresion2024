package sistemaimpresion;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;//codificacion 64
import javax.swing.JOptionPane;
public class conexion_doble{
        String[] parametrosDecod=new String[10];
        String ruta_arch_jar="";
        private static final String driver="com.mysql.jdbc.Driver";
    //variables globales de conexión local
        private static Connection conn_local;
        private static String user_local,pass_local,url_local;
    //variables globales de conexión en línea
        private static Connection conn_linea;
        private static String user_linea,pass_linea,url_linea;
    //variables globales de resultados
        private Connection con;
        private ResultSet rs;
        private ResultSetMetaData mtd;
        private String[] columna;
        private String consulta,error;
    
//constructor
    public conexion_doble(String ruta) {
        leer_txt(ruta);//obtenemos los datos del archivo de configuaracion de conexiones
    }

/******************************************************************************
*                                                                             *
*                  Métodos de configuración y conexiones                      *
*                                                                             *
/*****************************************************************************/
    public void leer_txt(String ruta_archivo_inicial){
        try{
            System.out.println(ruta_archivo_inicial);
            BufferedReader br= new BufferedReader(new FileReader(ruta_archivo_inicial));
            String temporal="",bRead;
        //recorremos líneas de archivo txt
            while((bRead=br.readLine())!=null){
                temporal+=bRead;//guardamos datos de txt en variable temporal
            }
            String[] conexiones=temporal.split("<>");
            int cont_conex=0,cont_posic=0;
            while(cont_conex<=1){
                String[] parametros64=conexiones[cont_conex].split("~");
                for(int i=0;i<parametros64.length;i++){
                    //guardamos el parámetro decodificado
                    System.out.println("Valor " + decodifica(parametros64[i]));
                    parametrosDecod[cont_posic]=decodifica(parametros64[i]);
                    cont_posic++;//incrementamos contador de opciones de arreglo decodificado 
                    //if(cont_posic==4){
                        //parametrosDecod[cont_posic]="";  
                      //  cont_posic++;//
                    //}
                }//fin de for i
                cont_conex++;//incrementamos el contador de conexiones
            }//fin de while
        //asignamos la ruta del archivo jar
            ruta_arch_jar=conexiones[3];
            
            br.close();
        }catch(IOException e){
            System.out.println("No se encontó el archivo de configuración inicial del sistema!!!\n"+e);
        }
       }
    
//método que descodifica encriptación 64
    public static String decodifica(String codificado){
        Base64.Decoder decod= Base64.getDecoder();
        byte[] descodificado= decod.decode(codificado);
        return new String(descodificado); 
    }

//método de conexión local
    public Connection conecta_local(){//asignamos datos de conexion local
        url_local="jdbc:mysql://"+parametrosDecod[0]+":3306/"+parametrosDecod[2]+"?autoReconnect=false";//+"?autoReconnect=true"
        user_local=parametrosDecod[3];
        pass_local="";//parametrosDecod[4]
        conn_local=null;
        try{
            Class.forName(driver);
            conn_local=DriverManager.getConnection(url_local,user_local,pass_local);
            try{
                conn_local.setAutoCommit(false);//desactivamos el autocommit
            }catch(Exception e){
                System.out.println("Error al declarar el autocommit local en falso!!! "+e);
            }
            if(conn_local!=null){
                System.out.println("Conectado en local!!!");
            }   
            
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("Error al conectar con servidor local "+e);
        }       
        return conn_local;
    }
//método de desconexión local
    public void desconectar_local(){
        conn_local=null;
        if(conn_local==null){
            System.out.println("Conexion local terminada!!!");
        }
    }
    
//método de conexión en línea
    public Connection conecta_linea() throws SQLException, IOException{
    //asignamos datos de conexion linea
        url_linea="jdbc:mysql://"+parametrosDecod[4]+":3306/"+parametrosDecod[6]+"?autoReconnect=false";//false
        System.out.println("URL LINEA : " + url_linea );
        user_linea=parametrosDecod[7];
        pass_linea=parametrosDecod[8];
        conn_linea=null;
        
        /*System.out.println("url : " +  parametrosDecod[4]);
        System.out.println("name : " +  parametrosDecod[6]);
        System.out.println("user : " +  parametrosDecod[7]);
        System.out.println("pass : " +  parametrosDecod[8]);*/
        try{
            Class.forName(driver);
            conn_linea=DriverManager.getConnection(url_linea,user_linea,pass_linea);
            /*try{
                conn_linea.setAutoCommit(false);//desactivamos el autocommit
            }catch(Exception e){
                System.out.println("Error al declarar el autocommit de linea en falso!!! "+e);
            }*/
            if(conn_linea!=null){
                System.out.println("Conectado en línea!!!");
            }    
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("Error al conectar con servidor en línea "+e);
            return null;
        } 
        return conn_linea;
    }
//método de desconexión línea
    public void desconectar_linea() throws SQLException{
        conn_linea.close();
        conn_linea=null;
        if(conn_linea==null){
            System.out.println("Conexion en linea terminada!!!");
        }
    }

//método para obtener error
    public String getError(){
        return "Error: "+this.error;
    }
    
//método para obtener resultados de la consulta
    public ResultSet getResultado(){
        return this.rs;
    }

/******************************************************************************
*                                                                             *
*                   Métodos de procesamientos de consultas                    *
*                                                                             *
/*****************************************************************************/
//Método para conteo de registtros (COUNT)
    public String cuenta_registros(String querty,Connection conexion){
        String respuesta="ok~";
        ResultSet rs;
            try{
                this.consulta=querty;
                System.out.println(consulta);
                Statement st= conexion.createStatement();
                rs=st.executeQuery(consulta);
                mtd=rs.getMetaData();
                int no_cols= mtd.getColumnCount();
                String[] res=getNombresColumnas(no_cols,mtd);
                rs.next();
                for(int j=0;j<res.length;j++){
            //capturamos valor del contador
                    respuesta+=rs.getObject(res[0]);
                }
                rs.close();//cerramos ResultSet
            }catch(SQLException ex){
                error=ex.getMessage();
                System.out.println("Error al contar registros!!!\n"+getError());
                return "error";
            }
        return respuesta;
    }
    
//método para consulta de datos (SELECT)
    public String consultar(String query,Connection conexion,String nom_tabla){
        String respuesta="";
        ResultSet rs;
            try{
                this.consulta=query;
                System.out.println(query);
                Statement st= conexion.createStatement();
                rs=st.executeQuery(consulta);
                mtd=rs.getMetaData();
                int no_cols= mtd.getColumnCount();
                String[] res=getNombresColumnas(no_cols,mtd);
            
                while(rs.next()){//recorremos resultados
                    for(int j=0;j<res.length;j++){
                        respuesta+=rs.getObject(res[j])+"~";
                    }
                    respuesta+="°";
                }
                rs.close();
            }catch(SQLException ex){
                error=ex.getMessage();
                System.out.println("Tabla: "+nom_tabla+"\n"+getError()+" "+query);
                return "error";
            }
            if(respuesta==""||respuesta==null){
                respuesta="0";
            }
        return respuesta;
    }

//método para obtener nombres de columnas
    public String[] getNombresColumnas(int numColumnas,ResultSetMetaData mt){
        try{
            this.columna=new String[numColumnas];
        //recorremos 
            for(int i=0;i<numColumnas;i++){
                columna[i]=mt.getColumnLabel(i+1);//obtenemos el nombre de la columna
            }
        }catch(SQLException e){
            System.out.println("Error al consultar nombres de columnas");
        }
        return columna;
    }

//método para actualización/eliminar (UPDATE, DELETE)
    public String actualiza(String query,Connection conexion) throws SQLException{
        int respuesta=0;
        try{
            PreparedStatement st=conexion.prepareStatement(query);
            respuesta=st.executeUpdate();
            /*if(respuesta==0){
                throw new SQLException("No se actualizó/eliminó ningún registro!!!");
            }*/
        //cerramos el Statement
            st.close();
        }catch(SQLException e){
            //conexion.rollback();
            //JOptionPane.showMessageDialog(null,e);
            return "error";
        }
        return "ok~"+respuesta;
    }
    
//método para inserción
    public String inserta(String query,Connection conexion){
        String respuesta="ok~";
        try{
            PreparedStatement st=conexion.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            int reg_afectados=st.executeUpdate();
        //verificamos que el registro se haya insertado
            if(reg_afectados==0){
                throw new SQLException("Error al insertar el registro!!!");
            }
        //obtenemos el id asignado al registro
            ResultSet generatedKeys = st.getGeneratedKeys();
            if(generatedKeys.next()){
                respuesta += generatedKeys.getInt(1);//concatenamos el id equivalente
            }
            st.close();//cerramos el statement
        }catch(SQLException e){
            //JOptionPane.showMessageDialog(null,e);
            return "error:\n"+e+"\n"+query;
        }
        //System.out.println(query);
        return respuesta;//returnamos el id asignado al nuevo registro insertado
    }//fin de método de inserción
    
}//fin de la clase proceso
