package dominio;

public class MapaEstaciones {

    public int contarEstacionesPorColumna(String[][] mat, int columna) {
        int contador = 0;

        for (int i = 0; i < mat.length; i++) {
            if (!mat[i][columna].equals("o")) {
                contador++;
            }
        }

        return contador;
    }

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
            int contador = contarEstacionesPorColumna(mat, i);
            
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

    public String tieneColumnasConsecutivas(String[][] mat) {
        int cantidadEstacionesAnterior = contarEstacionesPorColumna(mat, 0);
        int contadorConsecutivas = 1;

        for (int i = 1; i < mat[0].length; i++) {
            int cantidadEstacionesActual = contarEstacionesPorColumna(mat, i);

            if (cantidadEstacionesActual == cantidadEstacionesAnterior + 1) {
                cantidadEstacionesAnterior = cantidadEstacionesActual;
                contadorConsecutivas++;
            } else {
                contadorConsecutivas = 1;
            }

            if (contadorConsecutivas == 3) {
                return "existe";
            }
        }

        return "no existe";
    }
    
    public String devolverInformacionMapa(String [][] mat) {
        return indicarMaxEstaciones(mat) + "|" + tieneColumnasConsecutivas(mat);
    }
}
