import java.text.Normalizer;

public class Vigenere {
    public static void main(String[] args) {
        // LLamada de las dos funciones encode y decode
        String encodePalabra = encode("La cònstatació del fet", "diari");
        System.out.println(encodePalabra);
        String decodePalabra = decode("(PP QXUWF RFD F BKLCKAX) J ZXO EQ USWLVBD (FUWGEJA FMK TRSQSFFMU)", "balears");
        System.out.println(decodePalabra);
    }

    public static String encode(String message, String password) {
        String resultado = ControlCifrado(message, password, "encode");
        return resultado;
    }

    public static String decode(String message, String password) {
        String resultado = ControlCifrado(message, password, "decode");
        return resultado;
    }

    public static String ControlCifrado(String message, String password, String funcion) {
        String palabra = LimpiarPalabra(message).toUpperCase();
        String clave = LimpiarPalabra(password).toUpperCase();
        String resultado = "";
        int suma = 0;
        for (int i = 0, x = 0; i < palabra.length(); i++) {
            if ((int) palabra.charAt(i) < 65 || (int) palabra.charAt(i) > 90) {
                resultado += palabra.charAt(i);
            } else {
                switch (funcion) {
                    case "decode":
                        suma = (palabra.charAt(i) - 64) - (clave.charAt(x) - 64);
                        int valor = (suma <= 0) ? +26 : +0;
                        suma += valor;
                        break;
                    case "encode":
                        suma = (palabra.charAt(i) - 64) + (clave.charAt(x) - 64);
                        valor = (suma > 26) ? -26 : +0;
                        suma += valor;
                        break;
                }
                suma += 64;
                resultado += (char)suma;
                x++;
                if (x == clave.length()) {
                    x = 0;
                }
            }
        }
        return resultado;
    }

    public static String LimpiarPalabra(String palabra) {
        palabra = Normalizer.normalize(palabra, Normalizer.Form.NFD);
        palabra = palabra.replaceAll("\\p{InCombiningDiacriticalMarks}", "");
        return palabra;
    }
}
