package GUI;
public class Mesa{   
    // << Atributos de clase >>
    private static final int MAX_PEDIDOS_POR_MESA=10;
    
    // << Atributos de instancia >>
    private int numero;
    private ColCombos pedidosMesa;
    private boolean ocupada;
   
    // << Constructor >>
    public Mesa(int n){
        numero=n;
        ocupada=false;
        pedidosMesa=null;
    }

    // << Comandos >>
    public void ocupar(){
        if (!ocupada){
            ocupada=true;
            pedidosMesa= new ColCombos(MAX_PEDIDOS_POR_MESA);
        }
    }
    
    public void liberar(){
        if (ocupada){
            pedidosMesa=null;
            ocupada=false;        
        }
    }  

    // << Consultas >>
    public int obtenerNumero(){
        return numero;
    }
    
    public ColCombos obtenerPedido(){
        return pedidosMesa;
    }
    
    public boolean estaOcupada(){
        return ocupada;
    }
    
    public boolean alcanzoMaximoPedidos(){
        boolean rta=false;
        if (ocupada && pedidosMesa.cantCombos()==MAX_PEDIDOS_POR_MESA)
            rta=true;
        return rta;
    }
    
    public String generarTicketCuenta(){
        String ticket;
        if (ocupada){
           ticket= "<html>TICKET PEDIDO MESA<br/>";
           for (int i=0; i<pedidosMesa.cantCombos(); i++){
                ticket=ticket+ "<br/>"+(i+1) + ". " + pedidosMesa.obtenerCombo(i).getNombre() + ", precio: $" +pedidosMesa.obtenerCombo(i).getPrecio() ;    
            }
            ticket=ticket+ "<br/><br/> TOTAL: $" + pedidosMesa.precioTotal() + "<br/><br/>";    
            ticket=ticket+"</html>";
        }
        else  ticket= "<html><br/>MESA LIBRE<br/><br/>";
        return ticket;
    }
    
    public String generarDetalleParcial(){
        String detalle;
        if (ocupada){
            detalle= "<html>DETALLE PARCIAL PEDIDO MESA<br/>";
            for (int i=0; i<pedidosMesa.cantCombos(); i++){
                detalle=detalle+ "<br/>"+(i+1) + ". " + pedidosMesa.obtenerCombo(i).getNombre() + ", precio: $" +pedidosMesa.obtenerCombo(i).getPrecio() ;    
            }
            detalle=detalle+ "<br/><br/> TOTAL PARCIAL: $" + pedidosMesa.precioTotal() + "<br/><br/>";    
            detalle=detalle+"</html>";
        }
        else  detalle= "<html><br/>PEDIDO VACÍO<br/><br/>";
        return detalle;
    }
     
}
