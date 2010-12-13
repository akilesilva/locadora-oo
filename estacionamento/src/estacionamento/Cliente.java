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
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Marina
 */
public class Cliente {

    protected String placa;
    protected int tipo;// 1 se é mensalista e 0 se é horista
    protected double valor=0.0;// quanto deve ser pago pelo cliente. é inicializado com zero
    protected Calendar HoraEntrada;
    protected Calendar HoraSaida;
    protected double preco;


    public Calendar getHoraEntrada() {
        return HoraEntrada;
    }

    public void setHoraEntrada(Calendar horaEntrou) {
        this.HoraEntrada = horaEntrou;
    }

    public Calendar getHoraSaida() {
        return HoraSaida;
    }

    public void setHoraSaida(Calendar horaSaiu) {
        this.HoraSaida = horaSaiu;
    }

    public Cliente()
    {
        this.placa=null;
        this.tipo=0;
        this.valor=0.0;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public static void Serializa(ArrayList<Cliente> Clientes, String caminho) throws FileNotFoundException, IOException{

        //Gera o arquivo para armazenar o objeto
        FileOutputStream arquivoGrav;

        if (!new File("dados/").exists()) {
            new File("dados/").mkdirs();
        }
        arquivoGrav = new FileOutputStream(caminho);

        //Classe responsavel por inserir os objetos
        ObjectOutputStream objGravar = new ObjectOutputStream(arquivoGrav);

        //Grava o objeto no arquivo
        objGravar.writeObject(Clientes);

        objGravar.flush();

        objGravar.close();

        arquivoGrav.flush();

        arquivoGrav.close();
    }

    public static ArrayList<Cliente> Desserializa(String caminho) throws FileNotFoundException, IOException, ClassNotFoundException {

        //arquivo onde estao os dados serializados
        FileInputStream arqLeitura;

        arqLeitura = new FileInputStream(caminho);

        //objeto que vai ler os dados do arquivo
        ObjectInputStream in = new ObjectInputStream(arqLeitura);

        //recupera o arrayList
        ArrayList<Cliente> lista = (ArrayList<Cliente>) in.readObject();

        arqLeitura.close();
        in.close();

        return lista;
    }



}
