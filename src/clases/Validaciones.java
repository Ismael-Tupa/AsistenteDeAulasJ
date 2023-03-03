package clases;

/**
 *
 * @author ISMAEL
 */
public class Validaciones {
    public static boolean Text(String texto){
    boolean resultado = false;
    for (int x = 0; x < texto.length(); x++) {
        char c = texto.charAt(x);

        if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
            resultado = true;
            //System.out.println("El carácter es vocal o consoante no acentuado");
        }
            else if ((c == 'á') || (c == 'é') || (c == 'í') || (c == 'ó') || (c == 'ú')) {
                resultado = true;
                //System.out.println("El carácter es vocal acentuada");
            }
                else if ((c == 'Á') || (c == 'É') || (c == 'Í') || (c == 'Ó') || (c == 'Ú')) {
                    resultado = true;
                    //System.out.println("El carácter es vocal acentuada");
                }
                    else if (c == ' ') {
                        resultado = true;
                        //System.out.println("El carácter es un espacio");
                    }
                        /*else if ((c == ',') || (c == ':') || (c == ';')) {
                            resultado = true;
                            //System.out.println("El carácter es un signo de puntuación");
                        }*/
                            else{
                                resultado = false;
                                //System.out.println("El carácter es un número o símbolo no reconocido");
                            }
        }
        return resultado;
    }
    public static boolean Number(String cadena){
	try {
		Integer.parseInt(cadena);
		return true;
	} catch (NumberFormatException nfe){
		return false;
	}
    }
    public static boolean TextNumber(String cadena){
        boolean res = false;
        for(int i=0 ; i < cadena.length() ; i++){
            char c = cadena.charAt(i);
            String a = ""+c;
            if(Text(a)){
                res = true;
            }else if(Number(a)){
                res = true;
            }else{
                res = false;
            }
        }
        return res;
    }
}
