/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquetechatajax;

/**
 *
 * @author luis
 */
public class Comentario {
    private String nombre, avatar, comentario;
    private int id;
    public Comentario(String nombre, String avatar, String comentario) {
        this.nombre = nombre;
        this.avatar = avatar;
        this.comentario = comentario;
    }

    public Comentario(String nombre, String avatar, String comentario, int id) {
        this.nombre = nombre;
        this.avatar = avatar;
        this.comentario = comentario;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getComentario() {
        return comentario;
    }

    public int getId() {
        return id;
    }
    
    
}
