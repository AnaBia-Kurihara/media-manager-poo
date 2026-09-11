package br.com.minhasmusicas.main;

import br.com.minhasmusicas.modelos.Audio;
import br.com.minhasmusicas.modelos.Genero;
import br.com.minhasmusicas.modelos.Musicas;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Playlist {
    private List<Audio> audios = new ArrayList<>();
    public void adiciona(Audio audio) {
        audios.add(audio);
    }

    public List<Audio> ordenarPorClassificacao() {
        return audios.stream()
                .sorted(Comparator.comparingInt(Audio::getClassificacao).reversed())
                .toList();
    }

    public List<Musicas> filtrarPorGenero(Genero genero) {
        return audios.stream()
                .filter(a -> a instanceof Musicas)
                .map(a -> (Musicas) a)
                .filter(m -> m.getGenero() == genero)
                .toList();
    }

    public double calcularMediaCurtidas() {
        return audios.stream()
                .mapToInt(Audio::getTotalCurtidas)
                .average()
                .orElse(0);
    }
}
