
function cargarComentarios(desde)
{
  
	
		xmlhttp = new XMLHttpRequest();
		xmlhttp.onreadystatechange = function() {
	//alert(this.readyState);
    if (this.readyState == 4 && this.status == 200) {
		
    tratarJSON(this.responseText);
	
    }
}
xmlhttp.open("POST", "ServletChat", true);
xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
xmlhttp.send("accion=cargar&id_ultimo="+desde);
}
function recagoAutomatico()
{
   var lista_comentarios=document.getElementsByClassName("clase_comentario");
        if (lista_comentarios.length>0)
        {
             var ultimo_comentario=lista_comentarios[lista_comentarios.length-1];
             var id_ultimo_comentario=ultimo_comentario.id;
             var desde=id_ultimo_comentario.split("_")[1];
             cargarComentarios(desde);
         }
         else
         {
             cargarComentarios(0);
         }
}
function tratarJSON(txt_json)
{
    alert(txt_json);
    var obj_json=JSON.parse(txt_json);
    lista_comentarios=obj_json.comentarios;
    for (i=0; i<lista_comentarios.length;i++)
        {
                var objeto_comentario=lista_comentarios[i];
                var id=objeto_comentario.id;
                var usuario=objeto_comentario.usuario;
                var comentario=objeto_comentario.comentario;
                var obj_div=document.createElement("div");
                obj_div.className="clase_comentario";
                obj_div.id="comentario_"+id;
                var obj_parrafo_usr=document.createElement("p");
                obj_parrafo_usr.innerHTML="El usuario "+usuario+" dijo:";
                var obj_parrafo_comentario=document.createElement("p");
                obj_parrafo_comentario.innerHTML=comentario;
                obj_div.appendChild(obj_parrafo_usr);
                obj_div.appendChild(obj_parrafo_comentario);
                document.getElementsByTagName("body")[0].appendChild(obj_div);
                /* <div id=comentario3><p>Usuario dijo:</p><p>comentario</p></div>
                 
                 */
                
        }
}
function enviarComentario()
{
    var comentario=document.getElementById("comentario").value;
   var lista_comentarios=document.getElementsByClassName("clase_comentario");
   var ultimo_comentario=lista_comentarios[lista_comentarios.length-1];
   var id_ultimo_comentario=ultimo_comentario.id;
   var desde=id_ultimo_comentario.split("_")[1];
    xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function() {

    if (this.readyState == 4 && this.status == 200) {
		
    tratarJSON(this.responseText);
	
    }
}
xmlhttp.open("POST", "ServletChat", true);
xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
xmlhttp.send("accion=insertar&comentario="+comentario+"&desde="+desde);
}
