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
        Alquiler other = (Alquiler) o;
        return this.usuario.compareTo(other.usuario);
    }
    
    @Override
    public String toString(){
        return bicicleta +"#"+usuario+"#"+estacion;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;            
        if (obj == null) return false; 
        
        Alquiler a2 = (Alquiler) obj;
        return this.getId() == (a2.getId());
    }
}
