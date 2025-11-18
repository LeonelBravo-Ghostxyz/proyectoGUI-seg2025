package GUI;

public class Resto{
    
    // << Atributos de instancia >>
    private Mesa[] mesas;
    private ColCombos stockMenu;
    
    // << Constructor >>
    public Resto(int cantMesas, ColCombos stockMenu){
        mesas= new Mesa[cantMesas];
        this.stockMenu = stockMenu;

        inicializarResto();
    }

    private void inicializarResto(){
        for (int i=0; i<cantMesas(); i++)
            mesas[i]=new Mesa(i+1);
    }

    // << Consultas >>
    
    public Mesa obtenerMesa(int numero){
        return mesas[numero-1];
    }
    
    public int cantMesas(){
        return mesas.length;
    }

    public ColCombos obtenerStockMenu() {
        return stockMenu;
    }
    
}
