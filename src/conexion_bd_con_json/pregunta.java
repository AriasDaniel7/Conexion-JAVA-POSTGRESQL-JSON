/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion_bd_con_json;

import java.util.ArrayList;

/**
 *
 * @author Daniel Arias
 */
public class pregunta {
    protected int id;
    protected String enunciado;
    protected ArrayList<respuesta> lista_rta;
    
    public pregunta(){
        this.id = 0;
        this.enunciado = "";
        this.lista_rta = new ArrayList<>();
    }
    
    public void crear_pregunta(int id, String enunciado){
        this.id = id;
        this.enunciado = enunciado;
    }
    
    public void crear_respuesta(String texto,boolean estado){
        respuesta rta = new respuesta(texto, estado);
        this.lista_rta.add(rta);
    }
    
    @Override
    public String toString(){
        String salida = "";
        salida = String.valueOf(this.id).intern();
        salida += "|"+this.enunciado+"| [";
        for(int i=0; i<this.lista_rta.size(); i++){
            salida += this.lista_rta.get(i).toString()+" ";
        }
        salida += "]";
        return salida;
    }
}
