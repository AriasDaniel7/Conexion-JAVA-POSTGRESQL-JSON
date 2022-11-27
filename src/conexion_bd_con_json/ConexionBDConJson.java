/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion_bd_con_json;

/**
 *
 * @author Daniel Arias
 */
public class ConexionBDConJson {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        lista_preguntas lp = new lista_preguntas();
        json gson = new json();
        conexionSQL conec = new conexionSQL();
        
        lp = conec.leer_BD_ArrayList();
        gson.crear_json(lp);
    }
}
