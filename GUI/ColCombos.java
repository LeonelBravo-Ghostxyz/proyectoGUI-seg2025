package GUI;

public class ColCombos {
    // << Atributos de instancia >>
    private Combo [] c;
    private int cant;

    // << Constructor >>
    public ColCombos(int cant){
        c = new Combo[cant];       
        cant = 0;
    }   
    
    // << Comandos >>
    public void agregarCombo(Combo e){
        //Agrega un Combo al final
        c[cant]=e;
        cant++;
    }
    
    // << Consultas >>
    public int cantCombos(){
        return cant;
    }
    
    public Combo obtenerCombo(int pos){
        return c[pos];
    }

    public Combo obtenerCombo(String n){
        //retorna el primer combo que encuentra con nombre n. Si no existe combo con nombre e en la colección retorna nulo.
        Combo r = null;
        for (int i=0;i<cantCombos() && r==null;i++)
          if (c[i].getNombre().equals(n))
            r = c[i];
        return r;
    }

    public int obtenerPosCombo(String n){
        //retorna la posición del primer combo que encuentra con nombre n. Si no existe combo con nombre e en el menú retorna -1.
        int r = -1;
        for (int i=0;i<cantCombos() && r==-1;i++)
          if (c[i].getNombre().equals(n))
            r = i;
        return r;
    }
   
    public float precioTotal(){
        float p=0;
        for (int i=0; i<cant; i++)
         p+=c[i].getPrecio();
        return p;
    }
    
}
