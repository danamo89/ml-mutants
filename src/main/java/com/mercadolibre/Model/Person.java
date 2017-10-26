package com.mercadolibre.Model;

/**
 *
 * @author david
 */
public class Person {

    private String[] dna;

    public Person() {
    }

    public Person(String[] dna) {
        this.dna = dna;
    }

    public String[] getDna() {
        return dna;
    }

    public void setDna(String[] dna) {
        this.dna = dna;
    }
    
}
