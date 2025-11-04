
package tads;

public interface ICola<T> {
    
   public boolean esVacia(); 
   public boolean esLlena();
   public void encolar(T dato);
   public void desencolar();
   public T frente();
   public T fondo();
   public int cantElementos();
   public int cantMaxElementos();
}
