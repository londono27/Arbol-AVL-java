package test;

import vista.Lienzo;
import arboles.AVL.ArbolAVL;
import javax.swing.JFrame;
import vista.Modelo;
import vista.VistaPrincipal;

public class PruebaAVL {

    private Modelo miApp; // nace con ese launcher 

    public PruebaAVL() {
        miApp = new Modelo();
        miApp.iniciar();
    }

    public static void main(String[] args) {
        new PruebaAVL();
    }

}
