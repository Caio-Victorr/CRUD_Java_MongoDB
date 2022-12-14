package com.mongodb;

import com.mongodb.client.FindIterable;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;

public class CRUD extends MongoMain {

    public void create() {
        Document documento = new Document();
        InputTreatment createTreatment = new InputTreatment();
        String cpf = "", nome = "", sobrenome = "", idade = "", sexo = "", emprego = "";

        boolean confirmCadastro = false;
        while (!confirmCadastro){
            cpf = createTreatment.cpfEntrada(0);
            if(cpf.equals("SAIR")) break;
            nome = createTreatment.palavraEntrada(0);
            if(nome.equals("SAIR")) break;
            sobrenome = createTreatment.palavraEntrada(1);
            if(sobrenome.equals("SAIR")) break;
            idade = createTreatment.idadeEntrada();
            if(idade.equals("SAIR")) break;
            sexo = createTreatment.sexoEntrada();
            if(sexo.equals("SAIR")) break;
            emprego = createTreatment.palavraEntrada(2);
            if(emprego.equals("SAIR")) break;
            confirmCadastro = true;
        }

        if(confirmCadastro){
            documento.append("cpf", cpf);
            documento.append("nome", nome);
            documento.append("sobrenome", sobrenome);
            documento.append("idade", idade);
            documento.append("sexo", sexo);
            documento.append("emprego", emprego);
            getCollection().insertOne(documento);
            System.out.println("\nDADOS CADASTRADOS COM SUCESSO!");
        }else{
            System.out.println("\nOPERAÇÃO CANCELADA!");
        }
    }

    public void read() {
        String[] listaOpcoes = {"_id", "cpf", "nome", "sobrenome", "idade", "sexo", "emprego"};
        FindIterable<Document> iterDoc = getCollection().find();
        ListDocument listDocumentRead = new ListDocument();
        int tamListCadastros = (listDocumentRead.listListarCadastrados()).size();
        if(tamListCadastros == 0){
            System.out.println("\nNÃO HÁ USUARIOS PARA EXIBIR!");
        }
        boolean inicioLista = true;
        for (Document document : iterDoc) {
            if(inicioLista){
                System.out.println("\nEXIBINDO OS DADOS CADASTRADOS!\n");
                inicioLista = false;
            }
            System.out.println("------------------------------------------");
            int tamDocumento = document.size();
            for (int j = 0; j < tamDocumento; j++) {
                System.out.println(listaOpcoes[j] + ": " + document.get(listaOpcoes[j]));
            }
            System.out.println("------------------------------------------");
        }
    }

    public void update() {
        String[] listaOpcoes = {"nome", "sobrenome", "idade", "sexo", "emprego"};
        InputTreatment updateTreatment = new InputTreatment();
        String usuarioCPF = updateTreatment.cpfEntrada(1);
        ListDocument listDocumentUpdate = new ListDocument();
        boolean cpfEncontrado = listDocumentUpdate.listProcuraCadastrado(usuarioCPF);

        if (cpfEncontrado) {
            System.out.println("\n\nENCONTRADO!");
            int opcaoSubstituir = updateTreatment.tipoUpdateEntrada();
            if (opcaoSubstituir == 0) {
                System.out.println("\nOPERAÇÃO CANCELADA!");
            } else {
                String insertNovoValor;
                System.out.println("\nInfo: para cancelar digite 'SAIR'");
                switch (opcaoSubstituir) {
                    case 1:
                        insertNovoValor = updateTreatment.palavraEntrada(0);
                        break;
                    case 2:
                        insertNovoValor = updateTreatment.palavraEntrada(1);
                        break;
                    case 3:
                        insertNovoValor = updateTreatment.idadeEntrada();
                        break;
                    case 4:
                        insertNovoValor = updateTreatment.sexoEntrada();
                        break;
                    case 5:
                        insertNovoValor = updateTreatment.palavraEntrada(2);
                        break;
                    default:
                        insertNovoValor = "";
                        break;
                }
                if (!insertNovoValor.equals("SAIR")) {
                    String tipoSubstitui = listaOpcoes[opcaoSubstituir - 1];
                    getCollection().updateOne(eq("cpf", usuarioCPF),
                            combine(set(tipoSubstitui, insertNovoValor)));
                    System.out.println("\nATUALIZADO COM SUCESSO!");
                } else {
                    System.out.println("\nOPERAÇÃO CANCELADA!");
                }
            }
        } else if (usuarioCPF.equals("SAIR")) {
            System.out.println("\n\nOPERAÇÃO CANCELADA!");
        } else {
            System.out.println("\n\nNÃO ENCONTRADO!");
        }
    }

    public void delete() {
        InputTreatment deleteTreatment = new InputTreatment();
        String usuarioCPF = deleteTreatment.cpfEntrada(2);
        ListDocument listDocumentDelete = new ListDocument();
        boolean cpfEncontrado = listDocumentDelete.listProcuraCadastrado(usuarioCPF);

        if (cpfEncontrado) {
            System.out.println("\n\nENCONTRADO!");
            int opcaoDeletar = deleteTreatment.confirmaDeleteEntrada();
            if (opcaoDeletar == 0) {
                System.out.println("\nOPERAÇÃO CANCELADA!");
            } else if (opcaoDeletar == 1) {
                getCollection().deleteMany(eq("cpf", usuarioCPF));
                System.out.println("\nDELETADO COM SUCESSO!");
            }
        } else if (usuarioCPF.equals("SAIR")) {
            System.out.println("\n\nOPERAÇÃO CANCELADA!");
        } else{
            System.out.println("\n\nNÃO ENCONTRADO!");
        }
    }
}
