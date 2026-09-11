package br.com.minhasmusicas.modelos;

import br.com.minhasmusicas.modelos.Audio;
import br.com.minhasmusicas.modelos.Genero;

public class Musicas extends Audio {
    private String album;
    private String cantor;
    private Genero genero;

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getCantor() {
        return cantor;
    }

    public void setCantor(String cantor) {
        this.cantor = cantor;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    @Override
    public int getClassificacao() {
        if (this.getTotalReproducoes() > 200){
            return 10;
        } else {
            return 7;
        }
    }

    @Override
    public String toString() {
        return "Música: " + getTitulo() +
                " | Cantor: " + cantor +
                " | Álbum: " + album +
                " | Gênero: " + genero +
                " | Reproduções: " + getTotalReproducoes() +
                " | Curtidas: " + getTotalCurtidas() +
                " | Classificação: " + getClassificacao();
    }


}


