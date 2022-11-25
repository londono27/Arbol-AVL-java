package vista;

import arboles.AVL.ArbolAVL;

public class Modelo {

    private VistaPrincipal vistaPrincipal;
    private ArbolAVL arbol;
    private Lienzo lienzo;

    public void iniciar() {
        getArbolAVLD();
        getLienzo();
        lienzo.setObjArbol(arbol);
        getVistaPrincipal().setVisible(true);
    }

    public VistaPrincipal getVistaPrincipal() {
        if (vistaPrincipal == null) {
            vistaPrincipal = new VistaPrincipal(this);
        }
        return vistaPrincipal;
    }

    public Lienzo getLienzo() {
        if (lienzo == null) {
            lienzo = new Lienzo();
        }
        return lienzo;
    }

    public ArbolAVL getArbolAVLD() {
        if (arbol == null) {
            arbol = new ArbolAVL();
        }
        return arbol;
    }

    public void agregarNodo(int dato) {
        arbol.agregarNodo(dato);
        lienzo.setObjArbol(arbol);
        vistaPrincipal.dibujarArbol(lienzo);
    }

    public void eliminarNodo(int dato) {
        arbol.eliminar(dato);
        lienzo.setObjArbol(arbol);
        vistaPrincipal.dibujarArbol(lienzo);
    }

    public void limpiarArbol() {
        arbol.limpiarArbol();
        lienzo.setObjArbol(arbol);
        vistaPrincipal.dibujarArbol(lienzo);
    }

}
