/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion_bd_con_json;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel Arias
 */
public class json {
    public void crear_json(lista_preguntas objeto){
        lista_preguntas lp = objeto;
        JsonArray lista_final = new JsonArray();
        
        for(int i=0; i<lp.getPreguntas().size(); i++){
            JsonArray lista_preguntas = new JsonArray();
            for(int k=0; k<lp.getPreguntas().get(i).preguntas.size();k++){
                JsonArray lista_rtas = new JsonArray();
                for(int j=0; j<lp.getPreguntas().get(i).preguntas.get(k).lista_rta.size();j++){
                    JsonObject respuesta = new JsonObject();
                    respuesta.addProperty("texto", lp.getPreguntas().get(i).preguntas.get(k).lista_rta.get(j).texto);
                    respuesta.addProperty("estado", lp.getPreguntas().get(i).preguntas.get(k).lista_rta.get(j).estado);
                    lista_rtas.add(respuesta);
                }
                JsonObject pregunta = new JsonObject();
                pregunta.addProperty("id",lp.getPreguntas().get(i).preguntas.get(k).id);
                pregunta.addProperty("enun",lp.getPreguntas().get(i).preguntas.get(k).enunciado);
                pregunta.add("rtas", lista_rtas);
                lista_preguntas.add(pregunta);
            }
            JsonObject preg = new JsonObject();
            preg.addProperty("titulo",lp.getPreguntas().get(i).titulo);
            preg.add("preguntas",lista_preguntas);
            lista_final.add(preg);
        }
        
        BufferedWriter bw = null;
        
        try {
            bw = new BufferedWriter(new FileWriter("datos.json"));
            String gson = lista_final.toString();
            System.out.println("Fichero creado!");
            bw.write(gson);
        } catch (IOException ex) {
            Logger.getLogger(json.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                bw.close();
            } catch (IOException ex) {
                Logger.getLogger(json.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
