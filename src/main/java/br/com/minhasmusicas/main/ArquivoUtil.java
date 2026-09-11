package br.com.minhasmusicas.main;
import br.com.minhasmusicas.modelos.Musicas;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ArquivoUtil {
    public static void salvar(List<Musicas> musicas, String caminho) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminho))) {
            for (Musicas m : musicas) {
                writer.write(m.getTitulo() + ";" + m.getCantor() + ";" + m.getTotalReproducoes());
                writer.newLine();
            }
        }
    }

    public static List<Musicas> carregar(String caminho) throws IOException {
        List<Musicas> musicas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(caminho))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                Musicas  m = new Musicas();
                m.setTitulo(partes[0]);
                m.setCantor(partes[1]);
                musicas.add(m);
            }
        }
        return musicas;
    }
}

