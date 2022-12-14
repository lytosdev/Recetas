package tools;

public class Fraccion {

    public static String getFraccion(double numero) {

        String numeroStr = String.valueOf(numero);
        int indexOfDecimal = numeroStr.indexOf(".");
        int len = numeroStr.substring(indexOfDecimal).length();

        int parteImag = (int) Math.pow(10, len - 1);
        int parteReal = (int) (numero * parteImag);

        long gcm = gcm(parteReal, parteImag);
    
        return parteReal / gcm + "/" + parteImag / gcm;
    }

    private static long gcm(long a, long b) {
        return b == 0 ? a : gcm(b, a % b);
    }

}
