package GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;
import java.awt.image.BufferedImage;

public class GUIResto extends JFrame{
    
    // << Atributos de la aplicación >>
    private Resto resto;
    private int numeroMesaSeleccionada;
    private Container contenedor = getContentPane();
    
    //Objetos Gráficos
    //PANEL MENU
    private JButton [] boton;        
    private JLabel [] etiqueta;
    private JPanel panelMenu;    
    
    // PANEL RESTO
    private JPanel panelResto;    
    private JButton [] botonMesas;

    // PANEL MESA
    private JPanel panelMesa;
    private JLabel etiquetaMesaSeleccionada;    
    private JPanel panelDetalle;  
    private JLabel etiquetaDetallePedido; 
    private JButton botonAgregarItem;
    private JPanel panelOcuparDesocupar; 
    private JButton botonOcuparMesa;
    private JButton botonDesocuparMesa;

    //AT
    ColCombos combos;
    private int lengthBotones,lengthEtiquetas;
    
    public GUIResto(Resto r){
        super("Bienvenido al IPOO-Resto: Mesas");
        resto= r;
        numeroMesaSeleccionada=-1;
        combos = resto.obtenerStockMenu();
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(new Dimension(1100, 800));
        setVisible(true);
        
        inicializarpanelResto();
        inicializarPanelMenu();
        inicializarpanelMesa();
        armarBotones();
        armarEtiquetas();

        contenedor.setLayout(new BorderLayout()); 

        //Agrego cada panel al panel de contenido
        contenedor.add(panelResto, BorderLayout.CENTER);
        contenedor.add(panelMesa, BorderLayout.EAST);
        contenedor.add(panelMenu, BorderLayout.SOUTH);
        
        this.setVisible(true);
        this.setResizable(true);        //Recordar cambiar a false
    }

    
    private void inicializarpanelResto(){

        //Crea el panel y setea el layout
        panelResto = new JPanel();
        panelResto.setLayout(new GridLayout(resto.cantMesas()/4, 4)); 
    
        //Crea el arreglo de botones
        botonMesas = new JButton[resto.cantMesas()];
        for (int i = 0; i < resto.cantMesas(); i++) {            
            botonMesas[i] = new JButton();
            botonMesas[i].setBackground(Color.WHITE);
            botonMesas[i].setPreferredSize(new Dimension(200, 200)); 
            botonMesas[i].setIcon(escalarIcono("images/mesaLibre2.png", 200, 200));
            botonMesas[i].addActionListener(new OyenteMesa());
            botonMesas[i].setActionCommand(String.valueOf(i + 1));
            panelResto.add(botonMesas[i]);
        }
    }
    
    private void activarBotonesMesas(boolean activar){
        for (int i=0; i<resto.cantMesas(); i++)
            botonMesas[i].setEnabled(activar);
    }
    
    /*COMPLETADO
     * Implementar el método inicializarPanelMenu(). Crea los botones correspondientes a los distintos combos del menú, 
     * registra sus oyentes y los inserta en el panel del menú. Además, genera las etiquetas con las descripciones de cada combo y las agrega al mismo panel.
     */
    private void inicializarPanelMenu(){

        //Crear paneles y establecer diagramado
        panelMenu = new JPanel();
        panelMenu.setLayout(new GridLayout(2,5));
        panelMenu.setPreferredSize(new Dimension(1100, 230));
        
        //Crear botones, registrar oyentes e insertar en el panel de Menu
        boton = new JButton[5];
        lengthBotones = boton.length;
        for(int i=0; i<boton.length;i++){
            boton[i] = new JButton("");
            OyenteCombo oyenteCombo = new OyenteCombo();
            boton[i].addActionListener(oyenteCombo);
            panelMenu.add(boton[i]);
        }
        
        //Crear etiquetas e insertarlas en el panel de Menu
        etiqueta = new JLabel[5];
        lengthEtiquetas = etiqueta.length;
        for(int i=0;i<etiqueta.length;i++){
            etiqueta[i] = new JLabel("");
            panelMenu.add(etiqueta[i]);
        }

        //Al inicio, el panel del Menu no está visible
        panelMenu.setVisible(false);
        panelMenu.setEnabled(true);
    }
    
    
    private void inicializarpanelMesa(){

        //Crear paneles y establecer diagramado
        panelMesa= new JPanel();
        panelMesa.setLayout(new BorderLayout()); 
        panelMesa.setBorder(new EmptyBorder(10, 0, 0, 10)); // top, left, bottom, right
        etiquetaMesaSeleccionada= new JLabel("Mesa seleccionada");    
   
        panelDetalle= new JPanel();
        panelDetalle.setLayout(new BorderLayout()); 
        etiquetaDetallePedido= new JLabel("La mesa aún no ha realizado ningún pedido. "); 
        botonAgregarItem = new JButton("Agregar pedido");

        botonAgregarItem.addActionListener(new OyenteAgregarItem());
        panelDetalle.add(etiquetaDetallePedido,BorderLayout.CENTER);
        panelDetalle.add(botonAgregarItem,BorderLayout.PAGE_END);
  
        panelOcuparDesocupar= new JPanel(); 
        
        /*COMPLETADO
         * Crear los botones botonOcuparMesa y botonDesocuparMesa e insertarlos en el panel correspondiente. 
         * Declarar, crear y registrar los oyentes para esos botones.*/
        botonOcuparMesa = new JButton("Ocupar Mesa");
        panelOcuparDesocupar.add(botonOcuparMesa);
        OyenteOcuparMesa oyenteOcupar = new OyenteOcuparMesa();
        botonOcuparMesa.addActionListener(oyenteOcupar);

        botonDesocuparMesa = new JButton("Liberar Mesa");
        panelOcuparDesocupar.add(botonDesocuparMesa);
        OyenteLiberarMesa oyenteLiberar = new OyenteLiberarMesa();
        botonDesocuparMesa.addActionListener(oyenteLiberar);
        
        panelMesa.add(etiquetaMesaSeleccionada, BorderLayout.PAGE_START);
        panelMesa.add(panelDetalle, BorderLayout.CENTER);     
        panelMesa.add(panelOcuparDesocupar,BorderLayout.PAGE_END);  
        panelMesa.setVisible(false);        // Habilita el panel de la mesa seleccionada
         
    }

    /*COMPLETADO
     * Implementar el método armarBotones(). Configura los botones creados en el método inicializarPanelMenu() asignándoles la 
     * información específica de cada combo, como la imagen, la descripción y otros datos relevantes. Además setea el actionCommand con el
     * nombre del combo.
     */
    private void armarBotones(){
        for(int i=0;i<lengthBotones;i++){
            Combo comboActual = combos.obtenerCombo(i);
            boton[i].setIcon(escalarIcono("images/combo "+ (i+1)+".png", 200, 80));
            boton[i].setText("Combo "+ (i+1) +": "+ comboActual.getDescripcion());
            boton[i].setPreferredSize(new Dimension(300,120));
            boton[i].setVerticalTextPosition(JLabel.BOTTOM);
            boton[i].setHorizontalTextPosition(JLabel.CENTER);
            boton[i].setActionCommand(comboActual.getNombre());
            if(comboActual.getCantidad() < 1){
                boton[i].setEnabled(false);
            }else{
                boton[i].setEnabled(true);
            }
        }
    }

    /*COMPLETADO
     * Implementar el método armarEtiquetas(). Configura las etiquetas generadas en el método inicializarPanelMenu(), 
     * incorporando en cada una el precio del combo y la cantidad de unidades disponibles en stock.
     */
    private void armarEtiquetas(){
        for(int i=0;i<lengthEtiquetas;i++){
            Combo comboActual = combos.obtenerCombo(i);
            etiqueta[i].setText("$"+comboActual.getPrecio()+" Quedan: "+comboActual.getCantidad());
            etiqueta[i].setHorizontalAlignment(JLabel.CENTER);
        }
    }

    private ImageIcon escalarIcono(String ruta, int ancho, int alto) {
        ImageIcon iconoOriginal = new ImageIcon(ruta);
        Image imagenOriginal = iconoOriginal.getImage();
    
        // Crear una imagen compatible con la pantalla
        BufferedImage imagenEscalada = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = imagenEscalada.createGraphics();
    
        // Dibujar imagen escalada
        g2d.drawImage(imagenOriginal, 0, 0, ancho, alto, null);
        g2d.dispose();
 
        // Activar interpolación de alta calidad
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
    
        return new ImageIcon(imagenEscalada);
    }
    
    /*COMPLETADO
     * Implementar el oyente OyenteMesa de manera que al seleccionar una mesa, actualice las etiquetas con la información correspondiente, y
     * ajuste la visibilidad y el estado de los botones según si la mesa está ocupada o libre.
     */
    private class OyenteMesa implements ActionListener{
        public void actionPerformed(ActionEvent e){
            //Obtener el número de la mesa seleccionada y la mesa del resto con dicho número
            String indiceString = e.getActionCommand();
            numeroMesaSeleccionada = Integer.parseInt(indiceString);
            Mesa mesaSeleccionada = resto.obtenerMesa(numeroMesaSeleccionada);
            
            //Modificar la etiqueta con la mesa seleccionada 
            etiquetaMesaSeleccionada.setText("Mesa seleccionada: "+numeroMesaSeleccionada);

            //Modificar la etiqueta con el detalle parcial
            etiquetaDetallePedido.setText(mesaSeleccionada.generarDetalleParcial());
            //Hacer visible el panel de la Mesa
            panelMesa.setVisible(true);
            
            //Si la mesa no está ocupada entonces se oculta el panel de detalle, y se setea la visibilidad de los botones ocupar/desocupar mesa 
            //Sino se muestra el panel de detalle, y si la mesa no alcanzó el máximo de pedidos posibles, se setea la visibilidad del botón para agregar
            //un nuevo item. Además se setea la visibilidad de los botones ocupar/desocupar mesa
            if(mesaSeleccionada.estaOcupada()) {
                panelDetalle.setVisible(true);
                botonDesocuparMesa.setVisible(true);
                botonOcuparMesa.setVisible(false);
                if(!mesaSeleccionada.alcanzoMaximoPedidos())
                    botonAgregarItem.setEnabled(true);
                else botonAgregarItem.setEnabled(false);
            } else {
            // MESA LIBRE
                panelDetalle.setVisible(false);
                botonDesocuparMesa.setVisible(false);
                botonOcuparMesa.setVisible(true); 
            }
        }
    }
    
     /*COMPLETADO
      * Completar los oyentes OyenteOcuparMesa, OyenteLiberarMesa y OyenteCombo para que 
      * la aplicación opere conforme a las funcionalidades descritas.*/
    private class OyenteCombo implements ActionListener{
        public void actionPerformed(ActionEvent e){
            /*Vender el combo seleccionado, actualizar la etiqueta y si ya no quedan bandejas deshabilitar el botón*/
            String nombreComboSeleccionado = e.getActionCommand();
            Combo comboSeleccionado = combos.obtenerCombo(nombreComboSeleccionado);
            comboSeleccionado.vender();
            armarEtiquetas();
            
            
            //Obtener la mesa seleccionada y el combo seleccionado. Vender el combo seleccionado. Actualizar la etiqueta
            Mesa mesaSeleccionada = resto.obtenerMesa(numeroMesaSeleccionada);
            
            //Si luego de vender el combo no hay más stock, entonces se deshabilita el boton del combo correspondiente 
            if(comboSeleccionado.getCantidad() == 0){
                armarBotones();
            }
            
            //Se agrega el combo a la mesa seleccionada.}
             mesaSeleccionada.obtenerPedido().agregarCombo(comboSeleccionado);
            //Si luego de agregar el combo, la mesa alcanzó el máximo de pedidos, se deshabilita el botón para agregar nuevos items. 
            if(mesaSeleccionada.alcanzoMaximoPedidos()){
                botonAgregarItem.setEnabled(false);
            }
            else{
                botonAgregarItem.setEnabled(true);
            }
            
            //Se setea la etiqueta con el detalle parcial del pedido
            etiquetaDetallePedido.setText(mesaSeleccionada.generarDetalleParcial());
            
            //Se activan los botones de las mesas, se visibiliza el boton para desocupar la mesa y se oculta el panel del menu.
            for(int i=0;i<botonMesas.length;i++){
                botonMesas[i].setEnabled(true);
                botonDesocuparMesa.setVisible(true);
                panelMenu.setVisible(false);
            }

        } 
    }
    
    private class OyenteAgregarItem implements ActionListener{
        public void actionPerformed(ActionEvent e){
           panelMenu.setVisible(true);
           activarBotonesMesas(false);
           botonDesocuparMesa.setVisible(false);
        } 
    }
    
     /* COMPLETADO
      * Completar los oyentes OyenteOcuparMesa, OyenteLiberarMesa y OyenteCombo para que 
      * la aplicación opere conforme a las funcionalidades descritas.*/
     private class OyenteOcuparMesa implements ActionListener{
        public void actionPerformed(ActionEvent e){
            //Se ocupa la mesa seleccionada
            Mesa mesaSeleccionada = resto.obtenerMesa(numeroMesaSeleccionada);
            mesaSeleccionada.ocupar();

            //Se visibiliza el boton para agregar nuevos ítems
            botonAgregarItem.setVisible(true);
            
            botonMesas[numeroMesaSeleccionada-1].setIcon(escalarIcono("images/mesaOcupada2.png", 200, 200));
            //Se actualiza el detalle del pedido
            etiquetaDetallePedido.setText(mesaSeleccionada.generarDetalleParcial());

            //Se visibiliza el panel del detalle y se setea la visibilidad de los bootnes ocupar/desocupar mesa        
            panelDetalle.setVisible(true);
            botonOcuparMesa.setVisible(false);
            botonDesocuparMesa.setVisible(true);
        } 
    }
    
     /*COMPLETADO
      * Completar los oyentes OyenteOcuparMesa, OyenteLiberarMesa y OyenteCombo para que 
      * la aplicación opere conforme a las funcionalidades descritas.*/
     private class OyenteLiberarMesa implements ActionListener{
        public void actionPerformed(ActionEvent e){   
            Mesa mesaOcupada = resto.obtenerMesa(numeroMesaSeleccionada);
            
            //Se muestra un cuadro de diálogo con la información de la mesa a liberar de acuerdo a lo expuesto en el enunciado del proyecto. 
            JOptionPane.showMessageDialog(contenedor, mesaOcupada.generarTicketCuenta());
            botonMesas[numeroMesaSeleccionada-1].setIcon(escalarIcono("images/mesaLibre2.png", 200, 200));
            //Se libera la mesa seleccionada
            mesaOcupada.liberar();
            //Se oculta el panel de detalle, y se setean los botones para ocupar/desocupar la mesa  
            panelDetalle.setVisible(false);
            botonOcuparMesa.setVisible(true);
            botonDesocuparMesa.setVisible(false);
           
        } 
    }

}
