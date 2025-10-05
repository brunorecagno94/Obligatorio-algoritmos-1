package dominio;

public class Alquiler implements Comparable{
    private static int id = 0;
    private Usuario usuario;
    private Bicicleta bicicleta;
    private Estacion estacion;
    private boolean finalizado;

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Alquiler.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Bicicleta getBicicleta() {
        return bicicleta;
    }

    public void setBicicleta(Bicicleta bicicleta) {
        this.bicicleta = bicicleta;
    }

    public Estacion getEstacion() {
        return estacion;
    }

    public void setEstacion(Estacion estacion) {
        this.estacion = estacion;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }

    public Alquiler(Usuario usuario, Bicicleta bicicleta, Estacion estacion, boolean finalizado) {
        this.usuario = usuario;
        this.bicicleta = bicicleta;
        this.estacion = estacion;
        this.finalizado = finalizado;
    }

    @Override
    public int compareTo(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
