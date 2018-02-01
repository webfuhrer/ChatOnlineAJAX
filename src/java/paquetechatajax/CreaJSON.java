/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquetechatajax;

import java.util.List;

/**
 *
 * @author luis
 */
class CreaJSON {

    static String crearJSON(List<Comentario> lista_comentarios) {
        /*
            {"comentarios":[{"usuario":"PEPE", "comentario":"Viva el Lega", "id":5},
            {"usuario":"ana", "comentario":"Viva el madrid", "id":5}]*/
        
        String aux="{\"comentarios\":[";
        for (Comentario c: lista_comentarios)
        {
            int id=c.getId();
            String usuario=c.getNombre();
            String comentario=c.getComentario();
            aux+="{\"usuario\":"+"\""+usuario+"\""+", "+"\"comentario\":"+"\""+comentario+"\""+","+"\"id\":"+id+"},";
        }
        aux=aux.substring(0, aux.length()-1);
        aux+="]}";
        return aux;
    }

    static String pintarJSONLogin(boolean correcto) {
        String json="";
       if (correcto)
       {
           json="{\"respuesta\":\"ok\"}";
       }
       else
       {
         json="{\"respuesta\":\"ko\"}";  
       }
       return json;
    }
    
}
/* {"respuesta":"ok"}
    {"respuesta":"ko"}*/