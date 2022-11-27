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
public class datos_pregunta {

    protected String titulo;
    protected ArrayList<pregunta> preguntas;

    public datos_pregunta() {
        this.titulo = "";
        this.preguntas = new ArrayList<>();
    }
    
    public void crear_tema(String titulo){
        this.titulo = titulo;
    }

    public void crear_datos_pregunta(int id, String enunciado) {
        pregunta p = new pregunta();
        p.crear_pregunta(id, enunciado); 
        this.preguntas.add(p);
    }

    public boolean validar_existencia(int id) {
        for (int i = 0; i < this.preguntas.size(); i++) {
            if (this.preguntas.get(i).id == id) {
                return true;
            }
        }
        return false;
    }

    public void crear_rta(int id, String texto, boolean estado) {
        if (validar_existencia(id) == true) {
            for (int i = 0; i < this.preguntas.size(); i++) {
                if (this.preguntas.get(i).id == id) {
                    this.preguntas.get(i).crear_respuesta(texto, estado);
                }
            }
        }else{
            System.out.println("Id No existe!");
        }
    }

    public String toString() {
        String salida = this.titulo+" ";
        for (int i = 0; i < this.preguntas.size(); i++) {
            salida += this.preguntas.get(i).toString();
        }
        return salida;
    }
}
