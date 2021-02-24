package com.github.antmh.laboratorul2;

/**
 * @author Antonio Mihăeș
 */
public class Source {

    private String name;
    int supply;
    private SourceType sourceType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SourceType getSourceType() {
        return sourceType;
    }

    public void setSourceType(SourceType sourceType) {
        this.sourceType = sourceType;
    }

    public int getSupply() {
        return supply;
    }

    public void setSupply(int supply) {
        this.supply = supply;
    }

    @Override
    public String toString() {
        return "Source [name=" + name + ", sourceType=" + sourceType + ", supply=" + supply + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        
        return ((Source)obj).name.equals(name);
    }

    public Source(String name, SourceType sourceType, int supply) {
        this.name = name;
        this.sourceType = sourceType;
        this.supply = supply;
    }

}
