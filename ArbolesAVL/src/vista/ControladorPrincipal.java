package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.print.event.PrintEvent;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class ControladorPrincipal implements ActionListener {

    private VistaPrincipal vistaPrincipal;
    private Modelo modelo;

    public ControladorPrincipal(VistaPrincipal aThis) {
        vistaPrincipal = aThis;
        modelo = vistaPrincipal.getModelo();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton boton;
        try {
            if (e.getSource() instanceof JButton) {
                boton = (JButton) e.getSource();
                if (boton.getText().equals("Agregar")) {

                    modelo.agregarNodo(Integer.parseInt(vistaPrincipal.getValorUsu().getText()));
                }
                if (boton.getText().equals("Eliminar")) {
                    modelo.eliminarNodo(Integer.parseInt(vistaPrincipal.getValorUsu().getText()));
                }
                if (boton.getText().equals("Limpiar")) {
                    modelo.limpiarArbol();
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Coloque un valor númerico");
        }
    }

}
