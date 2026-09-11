package br.com.minhasmusicas.modelos;

public abstract class Audio implements Reproduzivel {
    private String titulo;
    private int totalReproducoes;
    private int totalCurtidas;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getTotalReproducoes() {
        return totalReproducoes;
    }

    public int getTotalCurtidas() {
        return totalCurtidas;
    }

    //metodos
    public void curte() {
        this.totalCurtidas++;
    }

    public void reproduz() {
        this.totalReproducoes++;
    }

}


