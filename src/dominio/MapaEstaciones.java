package dominio;

public class MapaEstaciones {

    public int maxEstacionesFila(String[][] mat) {
        int cantidadEstaciones = 0;

        for (int i = 0; i < mat.length; i++) {
            int contador = 0;

            for (int j = 0; j < mat[i].length; j++) {
                if (!mat[i][j].equals("o")) {
                    contador++;
                }
            }

            if (contador > cantidadEstaciones) {
                cantidadEstaciones = contador;
            }
        }

        return cantidadEstaciones;
    }

    public int maxEstacionesColumna(String[][] mat) {
        int cantidadEstaciones = 0;

        for (int i = 0; i < mat[0].length; i++) {
            int contador = 0;
            for (int j = 0; j < mat.length; j++) {
                if (!mat[j][i].equals("o")) {
                    contador++;
                }
            }
            if (contador > cantidadEstaciones) {
                cantidadEstaciones = contador;
            }
        }

        return cantidadEstaciones;
    }

    public String indicarMaxEstaciones(String[][] mat) {
        if (mat.length != 0) {
            int maxFilas = maxEstacionesFila(mat);
            int maxColumnas = maxEstacionesColumna(mat);

            if (maxFilas == 0 && maxColumnas == 0) {
                return "0#ambas";
            }
            if (maxFilas > maxColumnas) {
                return maxFilas + "#fila";
            } else if (maxFilas < maxColumnas) {
                return maxColumnas + "#columna";
            } else {
                return maxFilas + "#ambas";
            }
        } else {
            return "0#ambas";
        }
    }

    public boolean tieneColumnasConsecutivas(String[][] mat) {
        return false;
    }
}
