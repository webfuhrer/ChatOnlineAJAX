/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquetechatajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author luis
 */
@WebServlet(name = "ServletChat", urlPatterns = {"/ServletChat"})
public class ServletChat extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    String accion=request.getParameter("accion");//insertar omentario o cargar comentarios
    if (accion.equals("insertar"))//Insertar comentario
            {
               // ("accion=insertar&comentario="+comentario+"&desde="+desde);
                String comentario=request.getParameter("comentario");
                //Por ahora no tengo usuario así que lo pongo a mano
               // Comentario c=new Comentario("pepe", "sin avatar", comentario);
               HttpSession sesion=request.getSession();
                String usuario=(String)sesion.getAttribute("usuario");
                 if(usuario==null)
                {
                    request.getRequestDispatcher("entrada.html").forward(request, response);
                }
                 else
                     {
                 AccesoBD.insertarComentario(usuario, comentario);
                
                //Recupero comentarios posteriores a desde
                String desde=request.getParameter("desde");
                int numero_desde=Integer.parseInt(desde);
                
                //Esto de debajo se repite. Podría sacerse de los if
                List<Comentario> lista_comentarios=AccesoBD.recuperarComentarios(numero_desde);
                String json=CreaJSON.crearJSON(lista_comentarios);
                request.setAttribute("json", json);
                request.getRequestDispatcher("pintajson.jsp").forward(request, response);
            }
                        
            }
    if (accion.equals("cargar"))//Cargar comentarios
            {
                String id_ultimo_comentario=request.getParameter("id_ultimo");
                int desde=Integer.parseInt(id_ultimo_comentario);
                List<Comentario> lista_comentarios=AccesoBD.recuperarComentarios(desde);
                String json=CreaJSON.crearJSON(lista_comentarios);
                request.setAttribute("json", json);
                request.getRequestDispatcher("pintajson.jsp").forward(request, response);            
    }
    /*accion=login&usuario="+usuario+"&password="+password)*/
     if (accion.equals("login"))
     {
         String usuario=request.getParameter("usuario");
         String password=request.getParameter("password");
         boolean correcto=AccesoBD.comprobarUsuario(usuario, password);
         //Pongo al usr en sesión (session)
         if (correcto)
         {
             HttpSession sesion=request.getSession();
             sesion.setAttribute("usuario", usuario);
         }
         String json=CreaJSON.pintarJSONLogin(correcto);
         request.setAttribute("json", json);
            request.getRequestDispatcher("pintajson.jsp").forward(request, response);      
         /* {"respuesta":"ok"}
         {"respuesta":"ko"}*/
     }
    }
    
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
