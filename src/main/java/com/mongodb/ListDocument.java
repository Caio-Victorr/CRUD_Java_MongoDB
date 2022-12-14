package com.mongodb;

import com.mongodb.client.FindIterable;
import org.bson.Document;

import java.util.ArrayList;

public class ListDocument extends MongoMain{

    public boolean listProcuraCadastrado(String cpfProcurado){
        FindIterable<Document> iterDoc = getCollection().find();
        ArrayList<String> lista = new ArrayList<String>();
        for (Document document : iterDoc) {
            lista.add((String) document.get("cpf"));
        }
        return lista.contains(cpfProcurado);
    }
    public ArrayList<String> listListarCadastrados(){
        FindIterable<Document> iterDoc = getCollection().find();
        ArrayList<String> lista = new ArrayList<String>();
        for (Document document : iterDoc) {
            lista.add((String) document.get("cpf"));
        }
        return lista;
    }
}
