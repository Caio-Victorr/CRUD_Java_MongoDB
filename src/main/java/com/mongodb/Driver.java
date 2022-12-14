package com.mongodb;

import java.util.Scanner;

public class Driver {

    public static void main( String[] args) {
        CRUD operation = new CRUD();

        String[] digitosErrados = {",",".",";",":","-","'","!","@","#","$","%","¨","&","*",
                "(", ")","{","}","[","]","<",">", "|","=","+","-","^","~","/","\\","_"};
        int opcaoExecuta = -1;
        while(true){
            try{
                Scanner entradaOpcao = new Scanner(System.in);
                System.out.print("\nOpções de CRUD: " +
                        "\n1- Create\n2- Read\n3- Update\n4- Delete\n0- Sair da operação" +
                        "\n\nDigite um valor: ");
                String StringOpcaoExecuta = entradaOpcao.nextLine();
                boolean contErrado = false;
                if (StringOpcaoExecuta.length() == 1 ) {
                    for (String digitosErrado : digitosErrados) {
                        if (StringOpcaoExecuta.contains(digitosErrado)) {
                            System.out.println("\nAtenção: Opção digitado fora dos padrões, tente novamente!");
                            System.out.println("Dica: Utilizar apenas numeros, sem letras, pontos, traçoes ou simbolos");
                            contErrado = true;
                            break;
                        }
                    }
                    if(!contErrado){
                        opcaoExecuta = Integer.parseInt(StringOpcaoExecuta);
                        if(opcaoExecuta == 0){
                            System.out.println("\nPROGRAMA ENCERRADO!");
                            break;
                        }
                        else if (!(opcaoExecuta > 4 || opcaoExecuta < 1)){
                            if(opcaoExecuta == 1){
                                operation.create();
                            }else if(opcaoExecuta == 2){
                                operation.read();
                            } else if (opcaoExecuta == 3) {
                                operation.update();
                            }else{
                                operation.delete();
                            }
                        } else{
                            System.out.println("\nAtenção: Opção digitada fora dos padrões, tente novamente!");
                            System.out.println("Dica: digite um valor da lista opções");
                        }
                    }
                } else {
                    System.out.println("\nAtenção: Opção digitada fora dos padrões, tente novamente!");
                    System.out.println("Dica: digite 1 opção");
                }
            } catch (NumberFormatException e){
                System.out.println("\nAtenção: Opção digitada fora dos padrões, tente novamente!");
                System.out.println("Dica: Utilizar apenas numeros, sem letras, pontos ou simbolos");
            }
        }
    }
}
