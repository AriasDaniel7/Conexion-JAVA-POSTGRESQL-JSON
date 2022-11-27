/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion_bd_con_json;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Daniel Arias
 */
public class conexionSQL {
    Connection conexion = null;
    String url = "jdbc:postgresql://localhost:5432/temas_preguntas";
    String usuario = "postgres";
    String clave = "arix07";
    Statement sentencia = null;
    
    public void conectar(){
        try {
            Class.forName("org.postgresql.Driver");
            conexion = DriverManager.getConnection(url,usuario,clave);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Conexion fallida"+e,"conexion",JOptionPane.ERROR_MESSAGE); 
        }
    }
    
    public void cerrar(){
        try {
            conexion.close();   
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "desconexion fallida"+e,"conexion",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void imprimir_tabla(String consulta){
        conectar();
        ResultSet rs;
        try {
            sentencia = conexion.createStatement();
            rs = sentencia.executeQuery(consulta);
            System.out.format("%3s| %20s| %29s| %17s| %n","ID_RESPUESTA","CAMPO_RESPUESTA","ESTADO","ID_PREGUNTA");
            while(rs.next()){
                System.out.format("%12s| %20s| %29s| %17s| %n"
                                    ,rs.getString("id_respuesta")
                                    ,rs.getString("campo_resp")
                                    ,rs.getString("estado")
                                    ,rs.getString("pregunta_id_respuesta"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e,"conexion",JOptionPane.ERROR_MESSAGE);
        }finally{
            cerrar();
        }   
    }
    
    public lista_preguntas leer_BD_ArrayList(){
        lista_preguntas lp = new lista_preguntas();
        /*
        lp.crear_tema("Electro");
        lp.crear_pregunta(1,"Electro","Pregunta 1");
        lp.crear_rta(1,"Respuesta 1", false);
        lp.crear_rta(1,"Respuesta 2", true);
        lp.crear_rta(1,"Respuesta 3", false);
        lp.crear_rta(1,"Respuesta 4", false);
        lp.crear_pregunta(2,"Electro","Pregunta 2");
        lp.crear_rta(2,"Respuesta 1", false);
        lp.crear_rta(2,"Respuesta 2", false);
        lp.crear_rta(2,"Respuesta 3", true);
        lp.crear_rta(2,"Respuesta 4", false);
        lp.crear_tema("Calculo");
        lp.crear_pregunta(3,"Calculo","Pregunta 1");
        lp.crear_rta(3,"Respuesta 1", false);
        lp.crear_rta(3,"Respuesta 2", true);
        lp.crear_rta(3,"Respuesta 3", false);
        lp.crear_rta(3,"Respuesta 4", false);
        */
        
        conectar();
        ResultSet rs;
        try {
            sentencia = conexion.createStatement();
            rs = sentencia.executeQuery("SELECT * FROM tema");
            while(rs.next()){
                lp.crear_tema(rs.getString("titulo"));
            }
            rs = sentencia.executeQuery("SELECT * FROM preguntas");
            while(rs.next()){
                lp.crear_pregunta(rs.getInt("cod"),rs.getString("titulo").intern(),rs.getString("enunciado").intern());
            }
            rs = sentencia.executeQuery("SELECT * FROM respuestas");
            while(rs.next()){
                lp.crear_rta(rs.getInt("pregunta"),rs.getString("texto").intern(),rs.getBoolean("estado"));
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e,"conexion",JOptionPane.ERROR_MESSAGE);
        }finally{
            cerrar();
        }
        
        return lp;
    }
}
