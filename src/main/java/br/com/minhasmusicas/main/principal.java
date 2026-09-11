package br.com.minhasmusicas.main;

import br.com.minhasmusicas.modelos.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Playlist minhaPlaylist = new Playlist();
        MinhasPreferidas preferidas = new MinhasPreferidas();
        List<Musicas> minhasMusicas = new ArrayList<>();

        boolean continuar = true;

        while (continuar) {
            System.out.println("\n===== MINHAS MÚSICAS =====");
            System.out.println("1 - Cadastrar música");
            System.out.println("2 - Cadastrar podcast");
            System.out.println("3 - Ver relatório da playlist");
            System.out.println("4 - Salvar músicas em arquivo");
            System.out.println("5 - Carregar músicas de arquivo");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = lerInteiro(scanner);

            switch (opcao) {
                case 1 -> cadastrarMusica(scanner, minhaPlaylist, preferidas, minhasMusicas);
                case 2 -> cadastrarPodcast(scanner, minhaPlaylist, preferidas);
                case 3 -> mostrarRelatorio(minhaPlaylist);
                case 4 -> salvarArquivo(scanner, minhasMusicas);
                case 5 -> carregarArquivo(scanner);
                case 0 -> {
                    System.out.println("Até logo!");
                    continuar = false;
                }
                default -> System.out.println("Opção inválida, tente novamente.");
            }
        }

        scanner.close();
    }

    private static void cadastrarMusica(Scanner scanner, Playlist playlist, MinhasPreferidas preferidas, List<Musicas> lista) {
        Musicas musica = new Musicas();

        System.out.print("Título: ");
        musica.setTitulo(scanner.nextLine());

        System.out.print("Cantor: ");
        musica.setCantor(scanner.nextLine());

        System.out.print("Álbum: ");
        musica.setAlbum(scanner.nextLine());

        System.out.println("Gênero:");
        for (Genero g : Genero.values()) {
            System.out.println("  " + (g.ordinal() + 1) + " - " + g);
        }
        System.out.print("Escolha o gênero: ");
        int generoEscolhido = lerInteiro(scanner);
        Genero[] generos = Genero.values();
        if (generoEscolhido >= 1 && generoEscolhido <= generos.length) {
            musica.setGenero(generos[generoEscolhido - 1]);
        }

        System.out.print("Quantas reproduções? ");
        int reproducoes = lerInteiro(scanner);
        for (int i = 0; i < reproducoes; i++) {
            musica.reproduz();
        }

        System.out.print("Quantas curtidas? ");
        int curtidas = lerInteiro(scanner);
        for (int i = 0; i < curtidas; i++) {
            musica.curte();
        }

        playlist.adiciona(musica);
        lista.add(musica);
        preferidas.inclui(musica);

        System.out.println("\nMúsica cadastrada:");
        System.out.println(musica);
    }

    private static void cadastrarPodcast(Scanner scanner, Playlist playlist, MinhasPreferidas preferidas) {
        Podcast podcast = new Podcast();

        System.out.print("Título: ");
        podcast.setTitulo(scanner.nextLine());

        System.out.print("Apresentador: ");
        podcast.setApresentador(scanner.nextLine());

        System.out.print("Descrição: ");
        podcast.setDescricao(scanner.nextLine());

        System.out.print("Quantas reproduções? ");
        int reproducoes = lerInteiro(scanner);
        for (int i = 0; i < reproducoes; i++) {
            podcast.reproduz();
        }

        System.out.print("Quantas curtidas? ");
        int curtidas = lerInteiro(scanner);
        for (int i = 0; i < curtidas; i++) {
            podcast.curte();
        }

        playlist.adiciona(podcast);
        preferidas.inclui(podcast);

        System.out.println("\nPodcast cadastrado:");
        System.out.println(podcast);
    }

    private static void mostrarRelatorio(Playlist playlist) {
        System.out.println("\n===== RELATÓRIO =====");

        System.out.println("\n-- Ordenado por classificação --");
        for (Audio audio : playlist.ordenarPorClassificacao()) {
            System.out.println(audio);
        }

        System.out.println("\n-- Média de curtidas: " + playlist.calcularMediaCurtidas());

        System.out.println("\n-- Filtrar por gênero --");
        System.out.println("Gênero:");
        Genero[] generos = Genero.values();
        for (Genero g : generos) {
            System.out.println("  " + (g.ordinal() + 1) + " - " + g);
        }
        // (aqui daria pra pedir input também, mas deixei simples mostrando um gênero fixo de exemplo)
        List<Musicas> filtradas = playlist.filtrarPorGenero(Genero.SERTANEJO);
        System.out.println("Músicas SERTANEJO:");
        if (filtradas.isEmpty()) {
            System.out.println("  (nenhuma encontrada)");
        } else {
            filtradas.forEach(System.out::println);
        }
    }

    private static void salvarArquivo(Scanner scanner, List<Musicas> musicas) {
        System.out.print("Nome do arquivo (ex: musicas.txt): ");
        String caminho = scanner.nextLine();
        try {
            ArquivoUtil.salvar(musicas, caminho);
            System.out.println("Salvo com sucesso em " + caminho);
        } catch (Exception e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        }
    }

    private static void carregarArquivo(Scanner scanner) {
        System.out.print("Nome do arquivo (ex: musicas.txt): ");
        String caminho = scanner.nextLine();
        try {
            List<Musicas> carregadas = ArquivoUtil.carregar(caminho);
            System.out.println("Músicas carregadas:");
            carregadas.forEach(m -> System.out.println(m.getTitulo() + " - " + m.getCantor()));
        } catch (Exception e) {
            System.out.println("Erro ao carregar: " + e.getMessage());
        }
    }

    private static int lerInteiro(Scanner scanner) {
        while (true) {
            String linha = scanner.nextLine();
            try {
                return Integer.parseInt(linha.trim());
            } catch (NumberFormatException e) {
                System.out.print("Digite um número válido: ");
            }
        }
    }
}