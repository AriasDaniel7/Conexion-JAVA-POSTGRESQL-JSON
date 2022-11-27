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
public class lista_preguntas {

    protected ArrayList<datos_pregunta> preguntas;

    public lista_preguntas() {
        this.preguntas = new ArrayList<>();
    }

    public ArrayList<datos_pregunta> getPreguntas() {
        return preguntas;
    }
    
    public void crear_tema(String titulo){
        datos_pregunta dp = new datos_pregunta();
        dp.crear_tema(titulo);
        this.preguntas.add(dp);
    }
 
    public void crear_pregunta(int id,String tema,String enunciado) {
        if(validar_existencia(tema)){
            this.getPreguntas().get(buscar_index(tema)).crear_datos_pregunta(id, enunciado);
        }else{
            System.out.println("El tema no existe!");
        }
    }

    public boolean validar_existencia(int id) {
        for (int i = 0; i < this.preguntas.size(); i++) {
            if (this.preguntas.get(i).validar_existencia(id)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean validar_existencia(String titulo) {
        for (int i = 0; i < this.preguntas.size(); i++) {
            if (this.preguntas.get(i).titulo.intern() == titulo) {
                return true;
            }
        }
        return false;
    }
    
    public int buscar_index(int id) {
        for (int i = 0; i < this.preguntas.size(); i++) {
            if (this.preguntas.get(i).validar_existencia(id)) {
                return i;
            }
        }
        return -1;
    }
    
    public int buscar_index(String titulo) {
        for (int i = 0; i < this.preguntas.size(); i++) {
            if (this.preguntas.get(i).titulo.intern() == titulo) {
                return i;
            }
        }
        return -1;
    }

    public void crear_rta(int id, String texto, boolean estado) {
        int index = buscar_index(id);
        this.preguntas.get(index).crear_rta(id, texto, estado);
    }

    public String toString() {
        String salida = "";
        for (int i = 0; i < this.preguntas.size(); i++) {
            salida += this.preguntas.get(i).toString() + "\n";
        }
        return salida;
    }
}
