package vista;

import arboles.AVL.*;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Lienzo extends JPanel {

    private ArbolAVL objArbol;
    public static final int DIAMETRO = 30;
    public static final int RADIO = DIAMETRO / 2;
    public static final int ANCHO = 50;

    public void setObjArbol(ArbolAVL objArbol) {
        this.objArbol = objArbol;
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        pintar(g, getWidth() / 2, 20, objArbol.nodo);
    }

    private void pintar(Graphics g, int x, int y, NodoAVL nodo) {
        if (nodo != null) {
            int EXTRA = objArbol.nodosCompletos(nodo) * (ANCHO / 2);
            if (nodo.izq != null) {
                g.setColor(Color.black);
                g.drawLine(x + RADIO, y + RADIO, x - ANCHO - EXTRA + RADIO, y + ANCHO + RADIO);
            }

            if (nodo.der != null) {
                g.setColor(Color.black);
                g.drawLine(x + RADIO, y + RADIO, x + ANCHO + EXTRA + RADIO, y + ANCHO + RADIO);
            }
            g.setColor(Color.PINK);
            g.fillOval(x, y, DIAMETRO, DIAMETRO);
            g.setColor(Color.black);
            g.drawString(String.valueOf(nodo.dato), x + 8, y + 18);

            pintar(g, x - ANCHO - EXTRA, y + ANCHO, nodo.izq);
            pintar(g, x + ANCHO + EXTRA, y + ANCHO, nodo.der);
        }
    }

}
