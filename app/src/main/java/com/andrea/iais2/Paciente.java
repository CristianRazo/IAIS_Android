package com.andrea.iais2;

public class Paciente {
    String peso,talla,estatura;

    public Paciente(String peso, String talla, String estatura) {
        this.peso = peso;
        this.talla = talla;
        this.estatura = estatura;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public String getEstatura() {
        return estatura;
    }

    public void setEstatura(String estatura) {
        this.estatura = estatura;
    }
}
