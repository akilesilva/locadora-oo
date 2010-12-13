/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package estacionamento;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Marina
 */
public class Cadastro implements Serializable {
//classe que cadastra os clientes mensalistas
    
    public static void Cadastro() throws FileNotFoundException, IOException{

        String placa;
        int CodCliente;// informa se é mensalista
        ArrayList<Cliente> clientes=null;

        Scanner input = new Scanner(System.in);

        System.out.print("Digite a placa do veículo");
        placa = input.nextLine();

        Cliente mensalista = new Cliente(placa,1,true);

        clientes = new ArrayList<Cliente>();
        clientes.add(mensalista);

        Serializa(clientes);
    }

    public static void Serializa(ArrayList<Cliente> Clientes) throws FileNotFoundException, IOException{

        //Gera o arquivo para armazenar o objeto
        FileOutputStream arquivoGrav;

        if (!new File("dados/").exists()) {
            new File("dados/").mkdirs();
        }
        arquivoGrav = new FileOutputStream("dados/clientes.dat");

        //Classe responsavel por inserir os objetos
        ObjectOutputStream objGravar = new ObjectOutputStream(arquivoGrav);

        //Grava o objeto no arquivo
        objGravar.writeObject(Clientes);

        objGravar.flush();

        objGravar.close();

        arquivoGrav.flush();

        arquivoGrav.close();
    }

    public static ArrayList<Cliente> desserializa() throws FileNotFoundException, IOException, ClassNotFoundException {

        //arquivo onde estao os dados serializados
        FileInputStream arqLeitura;

        arqLeitura = new FileInputStream("dados/clientes.dat");

        //objeto que vai ler os dados do arquivo
        ObjectInputStream in = new ObjectInputStream(arqLeitura);

        //recupera o arrayList
        ArrayList<Cliente> lista = (ArrayList<Cliente>) in.readObject();

        arqLeitura.close();
        in.close();

        return lista;
    }

   
}




