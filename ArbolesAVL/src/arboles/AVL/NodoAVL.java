package arboles.AVL;

public class NodoAVL {

    public int altura;
    public int dato;
    public NodoAVL izq;
    public NodoAVL der;

    public NodoAVL(int dato) {
        altura = 1;
        this.dato = dato;
    }
}
