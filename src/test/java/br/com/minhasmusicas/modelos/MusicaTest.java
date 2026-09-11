package br.com.minhasmusicas.modelos;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MusicaTest {
    @Test
    void classificacaoDeveSerDezQuandoReproducoesMaiorQue200() {
        Musicas musica = new Musicas();
        for (int i = 0; i < 201; i++) {
            musica.reproduz();
        }
        assertEquals(10, musica.getClassificacao());
    }

    @Test
    void classificacaoDeveSerSeteQuandoReproducoesMenorOuIgualA200() {
        Musicas musica = new Musicas();
        for (int i = 0; i < 100; i++) {
            musica.reproduz();
        }
        assertEquals(7, musica.getClassificacao());
    }
}