package dominio;

public class Alquiler implements Comparable{
    private static int id = 0;
    private String usuario;
    private String bicicleta;
    private String estacion;    

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Alquiler.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getBicicleta() {
        return bicicleta;
    }

    public void setBicicleta(String bicicleta) {
        this.bicicleta = bicicleta;
    }

    public String getEstacion() {
        return estacion;
    }

    public void setEstacion(String estacion) {
        this.estacion = estacion;
    }

    
    public Alquiler(String usuario, String estacion) {
        this.usuario = usuario;
        this.bicicleta = null;
        this.estacion = estacion;
    }

    public Alquiler(String usuario, String bicicleta, String estacion) {
        this.usuario = usuario;
        this.bicicleta = bicicleta;
        this.estacion = estacion;
    }

    @Override
    public int compareTo(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public String toString(){
        return "Alquiler"+ id + 
                "- usuario: " + usuario + 
                "| bicicleta: " + bicicleta + 
                "| estación: " + estacion;
    }
}
