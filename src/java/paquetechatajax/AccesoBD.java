/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquetechatajax;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luis
 */
class AccesoBD {
    static Connection c=null;
    static Statement stmt=null;
    private static void abrirConexion()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            c=DriverManager.getConnection("jdbc:mysql://localhost:3306/chat", "root", "");
            stmt=c.createStatement();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccesoBD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AccesoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void cerrarConexion()
    {
        try {
            stmt.close();
            c.close();
        } catch (SQLException ex) {
            Logger.getLogger(AccesoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       
    public static void insertarComentario(String usuario,String comentario)
    {
        abrirConexion();
        String sql="INSERT INTO `t_comentarios` (usuario,comentario) VALUES ('"+usuario+"', '"+comentario+"')";
        
        try
        {
            stmt.executeUpdate(sql);
        }
        catch(SQLException e)
        {
            
        }
        cerrarConexion();
    }

  
    static List<Comentario> recuperarComentarios(int id_desde) {
        List<Comentario> lista=new ArrayList<>();
        try {
            abrirConexion();
            String sql="SELECT * FROM t_comentarios WHERE id>"+id_desde;
            ResultSet resultados=stmt.executeQuery(sql);
            while(resultados.next())
            {
                String nombre=resultados.getString("usuario");
                String avatar="";
                String comentario=resultados.getString("comentario");
                int id=resultados.getInt("id");
                Comentario c=new Comentario(nombre, avatar, comentario, id);
                lista.add(c);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccesoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    public static boolean comprobarUsuario(String usuario, String password)
    {
        try {
            abrirConexion();
            String sql="SELECT * FROM t_usuarios WHERE nombre_usuario='"+usuario+"' AND password='"+password+"'";
            ResultSet rs=stmt.executeQuery(sql);
            if (rs.next())
            {
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AccesoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
