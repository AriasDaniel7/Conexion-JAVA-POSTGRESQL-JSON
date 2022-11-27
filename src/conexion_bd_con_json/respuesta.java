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
public class respuesta {
    protected String texto;
    protected boolean estado;
    
    public respuesta(String texto, boolean estado){
        this.estado = estado;
        this.texto = texto;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    @Override
    public String toString(){
        return getTexto()+"|"+isEstado();
    }
}
