package dominio;

public class Bicicleta implements Comparable {

    private String codigo;
    private TipoBiciEnum tipo;
    private String estado; //Alquilada, Mantenimiento, Disponible
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

    public TipoBiciEnum getTipo() {
        return tipo;
    }

    public void setTipo(TipoBiciEnum tipo) {
        this.tipo = tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

    public Bicicleta() {
    }

    public Bicicleta(String codigo) {
        this.codigo = codigo;
        this.tipo = null;
        this.estado = "Disponible";
        this.estacionAsignada = null;
        this.usuarioAsignado = null;
        this.motivoDeMantenimiento = null;
    }

    public Bicicleta(String codigo, TipoBiciEnum tipo) {
        this.codigo = codigo;
        this.tipo = tipo;
        this.estado = "Disponible";
        this.estacionAsignada = null;
        this.usuarioAsignado = null;
        this.motivoDeMantenimiento = null;
    }

    public Bicicleta(String codigo, TipoBiciEnum tipo, String estado) {
        this.codigo = codigo;
        this.tipo = tipo;
        this.estado = estado;
        this.estacionAsignada = null;
        this.usuarioAsignado = null;
        this.motivoDeMantenimiento = null;
    }

    public boolean isEnDeposito() {
        return this.getEstacionAsignada() == null;
    }

    @Override
    public int compareTo(Object o) {
        Bicicleta b2 = (Bicicleta) o;
        return this.codigo.compareTo(b2.codigo);
    }

    @Override
    public boolean equals(Object obj) {
        Bicicleta b2 = (Bicicleta) obj;
        return this.codigo.equals(b2.codigo);
    }

    @Override
    public String toString() {
        return codigo + "#" + tipo.name() + "#" + estado;
    }

}
