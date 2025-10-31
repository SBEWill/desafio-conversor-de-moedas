package main;

import utils.Convercao;
import utils.TaxService;


import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        Scanner scanner = new Scanner(System.in);

        Convercao convercao = new Convercao();
        TaxService importaApi = new TaxService();

        int controle= 0;



        while (controle!= 7) {

            System.out.println("******* Seja vindo ao conversor de moedas *******");
            System.out.println("Para converter USD em BRL digite 1");
            System.out.println("Para converter BRL em USD digite 2");
            System.out.println("Para converter USD em ARS digite 3");
            System.out.println("Para converter ARS em USD digite 4");
            System.out.println("Para converter USD em BOB digite 5");
            System.out.println("Para converter BOB em USD digite 6");
            System.out.println("Para sair digite 7");
            controle = scanner.nextInt();

           if (controle == 1){
               double taxa = importaApi.obterTaxa(controle);
               System.out.println("Digite o valor a ser convertido");
               double quantidade = scanner.nextDouble();
               scanner.nextLine();
               System.out.println("Valor total em R$ " + convercao.convertendoMoedas(taxa,quantidade));
        }
         else if (controle == 2){
               double taxa = importaApi.obterTaxa(controle);
               System.out.println("Digite o valor a ser convertido");
               double quantidade = scanner.nextDouble();
               scanner.nextLine();
               System.out.println("Valor total em $ " + convercao.convertendoMoedas(taxa,quantidade));

        } else if (controle == 3){
               double taxa = importaApi.obterTaxa(controle);
               System.out.println("Digite o valor a ser convertido");
               double quantidade = scanner.nextDouble();
               scanner.nextLine();
               System.out.println("Valor total em $ " + convercao.convertendoMoedas(taxa,quantidade));

        }  else if (controle == 4){
               double taxa = importaApi.obterTaxa(controle);
               System.out.println("Digite o valor a ser convertido");
               double quantidade = scanner.nextDouble();
               scanner.nextLine();
               System.out.println("Valor total em $ " + convercao.convertendoMoedas(taxa,quantidade));

        }else if (controle == 5){
               double taxa = importaApi.obterTaxa(controle);
               System.out.println("Digite o valor a ser convertido");
               double quantidade = scanner.nextDouble();
               scanner.nextLine();
               System.out.println("Valor total em Bs " + convercao.convertendoMoedas(taxa,quantidade));


        }else if (controle == 6){
               double taxa = importaApi.obterTaxa(controle);
               System.out.println("Digite o valor a ser convertido");
               double quantidade = scanner.nextDouble();
               scanner.nextLine();
               System.out.println("Valor total em $ " + convercao.convertendoMoedas(taxa,quantidade));




    }}
}}
