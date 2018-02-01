/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function login()
{
    var usuario=document.getElementById("usuario").value;
    var password=document.getElementById("password").value;

xmlhttp = new XMLHttpRequest();
xmlhttp.onreadystatechange = function() {
//alert(this.readyState);
    if (this.readyState == 4 && this.status == 200) {
		
    tratarJSONLogin(this.responseText);
	
    }
}
xmlhttp.open("POST", "ServletChat", true);
xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
xmlhttp.send("accion=login&usuario="+usuario+"&password="+password);
}

function tratarJSONLogin(txt_json)
{
    obj_json=JSON.parse(txt_json);
    var respuesta=obj_json.respuesta;
    if (respuesta=="ko")
    {
        alert ("Error de autenticación . Innténtelo de nuevo");
    }
    else
    {
        window.location="vercomentarios.jsp";
    }
    
    //
   /* {"respuesta":"ok"}
    {"respuesta":"ko"}*/
}