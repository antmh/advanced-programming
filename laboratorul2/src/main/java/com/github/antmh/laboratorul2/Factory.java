package com.github.antmh.laboratorul2;

/**
 * @author Antonio Mihăeș
 */
public class Factory extends Source {
    @Override
    public String toString() {
        return "Factory [name=" + name + ", supply=" + supply + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }

        return ((Factory) obj).name.equals(name);
    }

    public Factory(String name, int supply) {
        super(name, supply);
    }
}
