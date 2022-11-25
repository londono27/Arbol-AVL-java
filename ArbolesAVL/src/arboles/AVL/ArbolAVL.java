package arboles.AVL;

public class ArbolAVL {

    public NodoAVL nodo;

    public void limpiarArbol() {
        nodo = null;
    }

    //AGREGAR NODOS
    public void agregarNodo(int dato) {
        nodo = agregarNodo(nodo, dato);
    }

    private NodoAVL agregarNodo(NodoAVL nodoActual, int dato) {
        if (nodoActual == null) {
            return (new NodoAVL(dato));
        }

        if (dato < nodoActual.dato) {
            nodoActual.izq = agregarNodo(nodoActual.izq, dato);
        } else if (dato > nodoActual.dato) {
            nodoActual.der = agregarNodo(nodoActual.der, dato);
        } else {
            return nodoActual;
        }

        nodoActual.altura = 1 + CompararMax(getAltura(nodoActual.izq), getAltura(nodoActual.der));

        int equilibrio = getFactorEquilibrio(nodoActual);

        if (equilibrio > 1 && dato < nodoActual.izq.dato) {
            return RotacionDer(nodoActual);
        }

        if (equilibrio < -1 && dato > nodoActual.der.dato) {
            return RotacionIzq(nodoActual);
        }

        if (equilibrio > 1 && dato > nodoActual.izq.dato) {
            nodoActual.izq = RotacionIzq(nodoActual.izq);
            return RotacionDer(nodoActual);
        }

        if (equilibrio < -1 && dato < nodoActual.der.dato) {
            nodoActual.der = RotacionDer(nodoActual.der);
            return RotacionIzq(nodoActual);
        }

        return nodoActual;
    }

    //ELIMINAR
    public void eliminar(int dato) {
        nodo = eliminarAVL(nodo, dato);
    }

    private NodoAVL eliminarAVL(NodoAVL nodoActual, int dato) {
        if (nodoActual == null) {
            return nodoActual;
        }

        if (dato < nodoActual.dato) {
            nodoActual.izq = eliminarAVL(nodoActual.izq, dato);
        } else if (dato > nodoActual.dato) {
            nodoActual.der = eliminarAVL(nodoActual.der, dato);
        } else {
            //Nodo con un solo hijo o es hoja
            if ((nodoActual.izq == null) || (nodoActual.der == null)) {
                NodoAVL temp = null;
                if (temp == nodoActual.izq) {
                    temp = nodoActual.der;
                } else {
                    temp = nodoActual.izq;
                }

                // No tiene hijos
                if (temp == null) {
                    nodoActual = null;
                } else {
                    //Un hijo
                    nodoActual = temp;
                }
            } else {
                //Nodo con dos hijos
                NodoAVL temp = getNodoConValorMaximo(nodoActual.izq);

                //Se copia el dato del predecesor
                nodoActual.dato = temp.dato;

                //Se elimina el predecesor
                nodoActual.izq = eliminarAVL(nodoActual.izq, temp.dato);
            }
        }

        //Si solo tiene un nodo
        if (nodoActual == null) {
            return nodoActual;
        }

        nodoActual.altura = CompararMax(getAltura(nodoActual.izq), getAltura(nodoActual.der)) + 1;

        int equilibrio = getFactorEquilibrio(nodoActual);

        if (equilibrio > 1 && getFactorEquilibrio(nodoActual.izq) >= 0) {
            return RotacionDer(nodoActual);
        }

        if (equilibrio < -1 && getFactorEquilibrio(nodoActual.der) <= 0) {
            return RotacionIzq(nodoActual);
        }

        if (equilibrio > 1 && getFactorEquilibrio(nodoActual.izq) < 0) {
            nodoActual.izq = RotacionIzq(nodoActual.izq);
            return RotacionDer(nodoActual);
        }

        if (equilibrio < -1 && getFactorEquilibrio(nodoActual.der) > 0) {
            nodoActual.der = RotacionDer(nodoActual.der);
            return RotacionIzq(nodoActual);
        }

        return nodoActual;
    }
    
    

    //ROTACIONES
    private NodoAVL RotacionDer(NodoAVL nodoActual) {
        NodoAVL nuevaRaiz = nodoActual.izq;
        NodoAVL temp = nuevaRaiz.der;

        nuevaRaiz.der = nodoActual;
        nodoActual.izq = temp;

        nodoActual.altura = CompararMax(getAltura(nodoActual.izq), getAltura(nodoActual.der)) + 1;
        nuevaRaiz.altura = CompararMax(getAltura(nuevaRaiz.izq), getAltura(nuevaRaiz.der)) + 1;

        return nuevaRaiz;
    }

    private NodoAVL RotacionIzq(NodoAVL nodoActual) {
        NodoAVL nuevaRaiz = nodoActual.der;
        NodoAVL temp = nuevaRaiz.izq;

        nuevaRaiz.izq = nodoActual;
        nodoActual.der = temp;

        nodoActual.altura = CompararMax(getAltura(nodoActual.izq), getAltura(nodoActual.der)) + 1;
        nuevaRaiz.altura = CompararMax(getAltura(nuevaRaiz.izq), getAltura(nuevaRaiz.der)) + 1;

        return nuevaRaiz;
    }

    public void mostrarArbolAVL() {
        mostrarArbol(nodo);
    }

    private void mostrarArbol(NodoAVL nodo) {

        System.out.print(nodo.dato);

        if (nodo.izq != null) {
            System.out.print("(");
            mostrarArbol(nodo.izq);
            System.out.print(",");
        }

        if (nodo.der != null) {
            mostrarArbol(nodo.der);
            System.out.print(")");
        }

    }

    private int getAltura(NodoAVL nodoActual) {
        if (nodoActual == null) {
            return 0;
        }

        return nodoActual.altura;
    }

    private int CompararMax(int a, int b) {
        return (a > b) ? a : b;
    }

    private int getFactorEquilibrio(NodoAVL nodoActual) {
        if (nodoActual == null) {
            return 0;
        }

        return getAltura(nodoActual.izq) - getAltura(nodoActual.der);
    }

    private NodoAVL getNodoConValorMaximo(NodoAVL nodo) {
        while (nodo.der != null) {
            nodo = nodo.der;
        }

        return nodo;
    }

    public int nodosCompletos(NodoAVL nodo) {
        if (nodo == null) {
            return 0;
        } else {
            if (nodo.izq != null && nodo.der != null) {
                return nodosCompletos(nodo.izq) + nodosCompletos(nodo.der) + 1;
            }
            return nodosCompletos(nodo.izq) + nodosCompletos(nodo.der);
        }
    }



}
