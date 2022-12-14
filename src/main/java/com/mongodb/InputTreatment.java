package com.mongodb;
import java.util.ArrayList;
import java.util.Scanner;

public class InputTreatment{

    Scanner entrada = new Scanner(System.in);
    public String cpfEntrada(int tipoEntCpf){
        ListDocument listDocumentCPF = new ListDocument();
        ArrayList<String> lista = listDocumentCPF.listListarCadastrados();

        String[] digitosErrados = {",", ".", ";", ":", "-", "'", "!", "@", "#", "$", "%", "¨", "&", "*",
                "(", ")", "{", "}", "[", "]", "<", ">", "|", "=", "+", "-", "^", "~", "/", "\\", "_"};
        String tipoCpfEntrada;
        switch (tipoEntCpf){
            case 1:
                tipoCpfEntrada = " do usuario a ser atualizado (para cancelar digite 'SAIR')";
                break;
            case 2:
                tipoCpfEntrada = " do usuario a ser deletado (para cancelar digite 'SAIR')";
                break;
            default:
                System.out.println("\nINICIANDO CADASTRO!");
                tipoCpfEntrada = " (para cancelar digite 'SAIR')";
                break;
        }
        System.out.println("\nDica: O CPF contém 11 digitos, apenas numeros!");
        while(true){
            try {
                System.out.print("Digite o CPF" + tipoCpfEntrada + ": ");
                String valorEntradaString = entrada.nextLine();
                if (valorEntradaString.length() == 11) {
                    boolean contErrado = false;
                    for (String digitosErrado : digitosErrados) {
                        if (valorEntradaString.contains(digitosErrado)) {
                            System.out.println("\nAtenção: CPF digitado fora dos padrões, tente novamente!");
                            System.out.println("Dica: Utilizar apenas numeros, sem letras, pontos," +
                                    " traçoes ou simbolos");
                            contErrado = true;
                            break;
                        }
                    }
                    if (!contErrado) {
                        double entradaConvertida = Double.parseDouble(valorEntradaString);//erro do try/catch
                        if(!lista.contains(valorEntradaString) || tipoEntCpf == 1){
                            return valorEntradaString;
                        }else{
                            System.out.println("\nAtenção: O CPF digitado já foi cadastrado!");
                            System.out.println("Dica: O CPF é unico, digite um novo " +
                                    "ou digite 'SAIR' para cancelar!");
                        }
                    }
                } else if (valorEntradaString.toUpperCase().equals("SAIR")) {
                    return valorEntradaString.toUpperCase();
                } else {
                    System.out.println("\nAtenção: CPF digitado fora dos padrões, tente novamente!");
                    System.out.println("Dica: O CPF contém 11 digitos, apenas numeros");
                }
            } catch (NumberFormatException e) {
                System.out.println("\nAtenção: CPF digitado fora dos padrões, tente novamente!");
                System.out.println("Dica: Utilizar apenas numeros, sem letras, pontos ou simbolos");
            }
        }
    }

    //Esta parte consegue ficar responsanvel por receber nome, sobrenome e emprego
    public String palavraEntrada(int opcaoEntrada){
        String[] digitosErrados = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"
                , ",", ".", ";", ":", "-", "'", "!", "@", "#", "$", "%", "¨", "&", "*",
                "(", ")", "{", "}", "[", "]", "<", ">", "|", "=", "+", "-", "^", "~", "/", "\\", "_"};
        String[] listaOpcoes = {"Nome", "Sobrenome", "Emprego"};
        String tipoEntrada = listaOpcoes[opcaoEntrada];
        String valorEntradaString = null;
        System.out.println("\nDica: O " + tipoEntrada + " deve conter pelo menos 3 letras!");
        for(int i = 0; i != 1; i++){
            System.out.print("Digite o " + tipoEntrada + ": ");
            valorEntradaString = entrada.nextLine();
            if (valorEntradaString.toUpperCase().equals("SAIR")) {
                return valorEntradaString.toUpperCase();
            }else if (valorEntradaString.length() >= 3) {
                for (String digitosErrado : digitosErrados) {
                    if (valorEntradaString.contains(digitosErrado)) {
                        System.out.println("\nAtenção: " + tipoEntrada
                                + " digitado fora dos padrões, tente novamente!");
                        System.out.println("Dica: Utilizar apenas letras, sem numeros," +
                                " pontos, traçoes ou simbolos");
                        i--;
                        break;
                    }
                }
            }else {
                System.out.println("\nAtenção: " + tipoEntrada + " digitado fora dos padrões, tente novamente!");
                System.out.println("Dica: " + tipoEntrada + " deve conter pelo menos 3 letras!");
                i--;
            }
        }
        return valorEntradaString;
    }

    public String sexoEntrada(){
        String tipoSexo = "";
        String[] digitosErrados = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"
                , ",", ".", ";", ":", "-", "'", "!", "@", "#", "$", "%", "¨", "&", "*",
                "(", ")", "{", "}", "[", "]", "<", ">", "|", "=", "+", "-", "^", "~", "/", "\\", "_"};
        System.out.println("\nDica: O sexo é M para Masculino e F para feminino!");
        for(int i = 0; i != 1; i++){
            System.out.print("Digite o Sexo: ");
            String valorEntradaString = entrada.nextLine();
            valorEntradaString = valorEntradaString.toUpperCase();
            if (valorEntradaString.equals("M") || valorEntradaString.toUpperCase().equals("MASCULINO")) {
                tipoSexo = "Masculino";
            } else if (valorEntradaString.equals("F") || valorEntradaString.toUpperCase().equals("FEMININO")) {
                tipoSexo = "Feminino";
            } else if (valorEntradaString.toUpperCase().equals("SAIR")) {
                return valorEntradaString.toUpperCase();
            }else {
                System.out.println("\nAtenção: Sexo digitado fora dos padrões, tente novamente!");
                System.out.println("Dica: M para Masculino e F para feminino!");
                for (String digitosErrado : digitosErrados) {
                    if (valorEntradaString.contains(digitosErrado)) {
                        System.out.println("Dica: Utilizar apenas letras, sem numeros, pontos," +
                                " traçoes ou simbolos");
                        break;
                    }
                }
                i--;
            }
        }
        return tipoSexo;
    }

    public String idadeEntrada(){
        String[] digitosErrados = {",", ".", ";", ":", "-", "'", "!", "@", "#", "$", "%", "¨", "&", "*",
                "(", ")", "{", "}", "[", "]", "<", ">", "|", "=", "+", "-", "^", "~", "/", "\\", "_"};
        System.out.println("\nDica: A Idade deve ser entre 1 e 100 anos, apenas numeros!");
        while(true){
            try {
                System.out.print("Digite a Idade: ");
                String valorEntradaString = entrada.nextLine();
                if (valorEntradaString.length() >= 1 && valorEntradaString.length() <= 3) {
                    boolean contErrado = false;
                    for (String digitosErrado : digitosErrados) {
                        if (valorEntradaString.contains(digitosErrado)) {
                            System.out.println("\nAtenção: Idade digitada fora dos padrões, tente novamente!");
                            System.out.println("Dica: Utilizar apenas numeros, sem letras, pontos," +
                                    " traçoes ou simbolos");
                            contErrado = true;
                            break;
                        }
                    }
                    if (!contErrado) {
                        int entradaConvertida = Integer.parseInt(valorEntradaString);//erro do try/catch
                        if(entradaConvertida >= 1 && entradaConvertida <= 100){
                            return valorEntradaString;
                        }else{
                            System.out.println("\nAtenção: Idade digitada fora dos padrões, tente novamente!");
                            System.out.println("Dica: a Idade deve ser entre 1 e 100 anos, apenas numeros!");
                        }
                    }
                } else if (valorEntradaString.toUpperCase().equals("SAIR")) {
                    return valorEntradaString.toUpperCase();
                }else {
                    System.out.println("\nAtenção: Idade digitada fora dos padrões, tente novamente!");
                    System.out.println("Dica: a Idade deve ser entre 1 e 100 anos, apenas numeros!");
                }
            } catch (NumberFormatException e) {
                System.out.println("\nAtenção: Idade digitada fora dos padrões, tente novamente!");
                System.out.println("Dica: Utilizar apenas numeros, sem letras, pontos ou simbolos");
            }
        }
    }

    public int tipoUpdateEntrada(){
        String[] digitosErrados = {",", ".", ";", ":", "-", "'", "!", "@", "#", "$", "%", "¨", "&", "*",
                "(", ")", "{", "}", "[", "]", "<", ">", "|", "=", "+", "-", "^", "~", "/", "\\", "_"};
        String[] listaOpcoes = {"nome", "sobrenome", "idade", "sexo", "emprego"};
        while (true) {
            try {
                Scanner valorSubstituir = new Scanner(System.in);
                System.out.print("\nOpções de substituição: " +
                        "\n1- Nome\n2- Sobrenome\n3- Idade\n4- Sexo\n5- emprego\n0- Sair da operação" +
                        "\n\nDigite um valor: ");
                String StringOpcaoSubstituir = valorSubstituir.nextLine();
                boolean contErrado = false;
                if (StringOpcaoSubstituir.length() == 1) {
                    for (String digitosErrado : digitosErrados) {
                        if (StringOpcaoSubstituir.contains(digitosErrado)) {
                            System.out.println("\nAtenção: Opção digitado fora dos padrões, tente novamente!");
                            System.out.println("Dica: Utilizar apenas numeros, sem letras, pontos," +
                                    " traçoes ou simbolos");
                            contErrado = true;
                            break;
                        }
                    }
                    if (!contErrado) {
                        int opcaoSubstituir = Integer.parseInt(StringOpcaoSubstituir);//try e catch
                        if (!(opcaoSubstituir > 5 || opcaoSubstituir < 0)) {
                            return opcaoSubstituir;
                        } else {
                            System.out.println("\nAtenção: Opção digitada fora dos padrões, tente novamente!");
                            System.out.println("Dica: digite um valor da lista opções");
                        }
                    }
                } else {
                    System.out.println("\nAtenção: Opção digitada fora dos padrões, tente novamente!");
                    System.out.println("Dica: digite 1 opção");
                }
            } catch (NumberFormatException e) {
                System.out.println("\nAtenção: CPF digitado fora dos padrões, tente novamente!");
                System.out.println("Dica: Utilizar apenas numeros, sem letras, pontos ou simbolos");
            }
        }
    }

    public int confirmaDeleteEntrada(){
        while (true){
            try{
                System.out.print("\nOpções de substituição: " +
                        "\n1- Deletar\n0- Sair da operação" +
                        "\n\nDigite um valor: ");
                int opcaoDeletar = entrada.nextInt();
                if (opcaoDeletar == 0 || opcaoDeletar == 1) {
                    return opcaoDeletar;
                }else {
                    System.out.println("\nAtenção: Opção digitada fora dos padrões, tente novamente!");
                    System.out.println("Dica: digite um valor da lista opções");
                }
            }catch (NumberFormatException e){
                System.out.println("\nAtenção: CPF digitado fora dos padrões, tente novamente!");
                System.out.println("Dica: Utilizar apenas numeros, sem letras, pontos ou simbolos");
            }
        }
    }
}

