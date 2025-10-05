package dominio;

public class Bicicleta implements Comparable {
    private String codigo;
    private String tipo;
    private String estado; //Alquilada, Mantenimiento, Disponible
    private boolean enDeposito;
    private Estacion estacionAsignada;
    private Usuario usuarioAsignado;
    private String motivoDeMantenimiento;

    // Getters
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public boolean isEnDeposito() {
        return enDeposito;
    }

    public void setEnDeposito(boolean enDeposito) {
        this.enDeposito = enDeposito;
    }

    public Estacion getEstacionAsignada() {
        return estacionAsignada;
    }

    public void setEstacionAsignada(Estacion estacionAsignada) {
        this.estacionAsignada = estacionAsignada;
    }

    public Usuario getUsuarioAsignado() {
        return usuarioAsignado;
    }

    public void setUsuarioAsignado(Usuario usuarioAsignado) {
        this.usuarioAsignado = usuarioAsignado;
    }

    public String getMotivoDeMantenimiento() {
        return motivoDeMantenimiento;
    }

    public void setMotivoDeMantenimiento(String motivoDeMantenimiento) {
        this.motivoDeMantenimiento = motivoDeMantenimiento;
    }
    
    
    public Bicicleta(String codigo){
        this.codigo = codigo;
        this.tipo = null;
        this.estado = null;
        this.enDeposito = true;
        this.estacionAsignada = null;
        this.usuarioAsignado = null;
        this.motivoDeMantenimiento = null;
    }
   
    public Bicicleta(String codigo, String tipo) {
        this.codigo = codigo;
        this.tipo = tipo;
        this.estado = "Disponible";
        this.enDeposito = true;
        this.estacionAsignada = null;
        this.usuarioAsignado = null;
        this.motivoDeMantenimiento = null;
    }
    
    public Bicicleta(String codigo, String tipo, String estado){
        this.codigo = codigo;
        this.tipo = tipo;
        this.estado = estado;
    }
    
    @Override
    public int compareTo(Object o) {
        Bicicleta b2 = (Bicicleta)o;
        return this.codigo.compareTo(b2.codigo);
    }
    
    @Override
    public boolean equals(Object obj) {
        Bicicleta b2 = (Bicicleta) obj;
        return this.codigo.equals(b2.codigo);
    }

    @Override
    public String toString() {
        return codigo + "#" + tipo + "#" + estado; 
    }
    
    
}
