/* Fuente http://fgualambo.blogspot.mx/2011/09/validar-campo-de-texto-solo-letras.html
 * viernes, 16 de septiembre de 2011
Validar campo de texto sólo letras, números con javascript
En algunos casos necesitaremos que el usuario sólo ingrese letras en un campo de texto impidiendo así el ingreso de números y caracteres especiales. Gracias a Javascript esto lo podríamos hacer con el siguiente método que funciona sin problemas con todos los navegadores.

    Crear una lista con todas las letras permitidas (a,b,c ... etc).
    Crear una lista de teclas especiales tales como (BackSpace , flecha izquierda, flecha derecha, Supr) las cuales deben ser permitidas aparte de las letras permitidas.
    Hacer uso de la propiedad IndexOf (Con esto verificamos si un caracter está dentro de otra cadena) .
    Hacer uso del evento onkeypress del campo de texto para hacer la llamada a la función que utilize los pasos 1,2 y 3.

Código:
<script>
    function soloLetras(e){
 key = e.keyCode || e.which;
 tecla = String.fromCharCode(key).toLowerCase();
 letras = " áéíóúabcdefghijklmnñopqrstuvwxyz";
 especiales = [8,37,39,46];

 tecla_especial = false
 for(var i in especiales){
     if(key == especiales[i]){
  tecla_especial = true;
  break;
            } 
 }
 
        if(letras.indexOf(tecla)==-1 && !tecla_especial)
     return false;
     }
</script>

<input type="text" onkeypress="return soloLetras(event)">


Demostración:
<<----- Sólo acepta ingresar letras

    En la línea 4  la sentencia String.fromCharCode(key) obteniene el caracter presionado por el usuario que añadiendo la sentencia toLowerCase() convertiríamos la letra a minúscula. Con esto guardamos la letra presionada por el usuario en la variable tecla (línea 4).
    En la línea 5 guardamos en la variable "letras" las letras permitidas por nosotros.
    En la línea 6 guardamos los keyCode de las teclas especiales como  (BackSpace , flecha izquierda, flecha derecha, Supr).
    De la línea 8 a la 15 se busca si está la tecla presionada por el usuario en el array de teclas especiales "especiales"(línea 6)
    En la línea 17 dentro del condicional hacemos uso de la propiedad indexOf() que averigua si una cadena se encuentra dentro de otra cadena devolviendo como valor la posición de la cadena encontrada o el valor de -1 si es que no la encuentra , que para este caso queremos averiguar si el caracter presionado se encuentra entre las letras permitidas. 
    En la línea 17 diríamos que el condicional retorna falso si la tecla presionada no está en la lista de letras permitidas hecha por nosotros (línea 5)


Sólo números
Podríamos usar en la variable "letras" sólo las letras que queramos permitir , incluso si sólo queremos que se permitan números bastaría con sólo cambiar la variable "letras" de esta forma:
letras = "0123456789";

Puede ver todos los Keycode en la siguiente tabla mostrada aquí  <------


Nota : Si hay algo que no podemos evitar es que la persona que visita tu página copie y pegue un texto no deseado al "input text" . Para eso podemos agregarle el atributo "onblur" a la etiqueta input llamando a una función que limpie el "input text" si es que encuentra un caracter no deseado.
Código : d

<script>
function soloLetras(e){
    key = e.keyCode || e.which;
    tecla = String.fromCharCode(key).toLowerCase();
    letras = " áéíóúabcdefghijklmnñopqrstuvwxyz";
    especiales = [8,37,39,46];

    tecla_especial = false
    for(var i in especiales){
 if(key == especiales[i]){
     tecla_especial = true;
     break;
        } 
    }
 
    if(letras.indexOf(tecla)==-1 && !tecla_especial)
        return false;
}
function limpia(){
    var val = document.getElementById("miInput").value;
    var tam = val.length;
    for(i=0;i<tam;i++){
 if(!isNaN(val[i]))
 document.getElementById("miInput").value='';
    }
}
</script>

<input type="text" onkeypress="return soloLetras(event)" onblur="limpia()" id="miInput">

 * 
 */
function soloNumeros(e){
    key = e.keyCode || e.which;
    tecla = String.fromCharCode(key).toLowerCase();
    letras = "1234567890.";
    especiales = [8,9,37,39,46];

    tecla_especial = false
    for(var i in especiales){
        if(key == especiales[i]){
            tecla_especial = true;
            break;
        } 
    }
 
    if(letras.indexOf(tecla)==-1 && !tecla_especial)
        return false;
}
function limpiaPrecio(){
    var val = document.getElementById("precio").value;
    var tam = val.length;
    for(i=0;i<tam;i++){
        if(!isNaN(val[i]))
            document.getElementById("precio").value='';
    }
}

function limpiaCantidad(){
    var val = document.getElementById("cantidad").value;
    var tam = val.length;
    for(i=0;i<tam;i++){
        if(!isNaN(val[i]))
            document.getElementById("cantidad").value='';
    }
}

//<input type="text" onkeypress="return soloLetras(event)" onblur="limpia()" id="miInput">

