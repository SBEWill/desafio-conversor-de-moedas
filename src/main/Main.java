package main;

import model.ConversionRecord;
import model.Currency;
import service.HistoryManager;
import service.LogService;
import utils.Convercao;
import utils.TaxService;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

/**
 * Main integrado:
 * - AULA 3: usa BigDecimal (precisão financeira)
 * - AULA 4: mantém histórico (HistoryManager) e logs (LogService)
 *
 * Requisitos:
 * - utils.TaxService.obterTaxa(String fromCode, String toCode) (Aula 1)
 * - utils.Convercao.convertendoMoedas(BigDecimal, BigDecimal) (Aula 3)
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Convercao convercao = new Convercao();
        TaxService importaApi = new TaxService();

        // AULA 4: criar history manager e logger (arquivos na raiz do projeto)
        HistoryManager history = new HistoryManager(50, Path.of("history.json"));
        LogService logger = new LogService(Path.of("conversions.log"));

        while (true) {
            System.out.println("******* Seja vindo ao conversor de moedas *******");
            System.out.println("1 - Fazer conversão");
            System.out.println("2 - Ver histórico (mais recentes primeiro)");
            System.out.println("3 - Limpar histórico");
            System.out.println("4 - Sair");
            System.out.print("Escolha uma opção: ");
            String opc = scanner.nextLine().trim();

            if ("4".equals(opc)) {
                System.out.println("Saindo...");
                break;
            } else if ("2".equals(opc)) {
                // Mostrar histórico
                List<ConversionRecord> list = history.listAll();
                if (list.isEmpty()) {
                    System.out.println("Histórico vazio.");
                } else {
                    list.forEach(System.out::println);
                }
                continue;
            } else if ("3".equals(opc)) {
                history.clear();
                System.out.println("Histórico limpo.");
                continue;
            } else if (!"1".equals(opc)) {
                System.out.println("Opção inválida.");
                continue;
            }

            // Opção 1: conversão
            Currency from = lerCurrency(scanner, "Escolha a moeda de origem:");
            Currency to = lerCurrency(scanner, "Escolha a moeda de destino:");

            if (from == to) {
                System.out.println("Moeda de origem e destino são iguais. Escolha moedas diferentes.");
                continue;
            }

            // AULA 3: ler valor como BigDecimal
            BigDecimal quantidade;
            try {
                System.out.print("Digite o valor a ser convertido: ");
                String linha = scanner.nextLine().trim();
                quantidade = new BigDecimal(linha);
                if (quantidade.compareTo(BigDecimal.ZERO) < 0) {
                    System.out.println("Digite um valor positivo.");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Use apenas números (ex: 100.50).");
                continue;
            }

            try {


                BigDecimal taxa = importaApi.obterTaxa(from.code(), to.code());
                BigDecimal resultado = convercao.convertendoMoedas(taxa, quantidade);

                String simbolo = obterSimbolo(to); // para exibição simples
                System.out.printf("Resultado: %s%s%n", simbolo, resultado);

                // AULA 4: criar registro, salvar no histórico e gravar log
                String nowIso = ZonedDateTime.now().format(DateTimeFormatter.ISO_ZONED_DATE_TIME);
                ConversionRecord rec = new ConversionRecord(nowIso, from, to, quantidade, taxa, resultado);
                history.add(rec);
                logger.log(rec);

            } catch (IOException | InterruptedException e) {
                System.out.println("Erro na chamada à API: " + e.getMessage());
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println("Ocorreu um erro: " + e.getMessage());
                e.printStackTrace();
            }
        }

        scanner.close();
    }

    private static Currency lerCurrency(Scanner scanner, String prompt) {
        Currency[] moedas = Currency.values();
        while (true) {
            System.out.println(prompt);
            for (int i = 0; i < moedas.length; i++) {
                System.out.printf("%d - %s%n", i + 1, moedas[i].code());
            }
            System.out.print("Escolha o número da moeda: ");
            String linha = scanner.nextLine().trim();
            try {
                int idx = Integer.parseInt(linha) - 1;
                if (idx >= 0 && idx < moedas.length) {
                    return moedas[idx];
                } else {
                    System.out.println("Número fora do intervalo. Tente novamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite o número da opção (ex: 1).");
            }
        }
    }

    private static String obterSimbolo(Currency currency) {
        switch (currency) {
            case BRL: return "R$ ";
            case ARS: return "ARS ";
            case BOB: return "Bs ";
            default: return ""; // USD e demais
        }
    }
}
