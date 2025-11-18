package GUI;
public class Combo {
    
    // << Atributos de instancia >>
    private String nombre;
    private String descripcion;
    private int precio;
    private int cantidad;
    
    // << Constructor >>
    public Combo( String n, int  p,int c, String d){
        nombre=n;
        precio = p;
        cantidad = c;
        descripcion=d;
    }
    
    // << Comandos >>
    public void vender(){
      cantidad--;  
    }  
    
    // << Consultas >>
    public String getNombre(){
        return nombre;
    }
    
     public int getPrecio(){
        return precio;
    }    
    
    public int getCantidad(){
        return cantidad;
    }   
    
    public String getDescripcion(){
        return descripcion;
    }
}
