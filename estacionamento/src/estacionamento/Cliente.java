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
import java.util.Calendar;

/**
 *
 * @author Marina
 */

public class Cliente implements Serializable{


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

    public Cliente(String placaV,int tipoC,double valor){

        this.placa=placaV;
        this.tipo=tipoC;
        this.valor=valor;
    }

    public Cliente(){

        this.placa=null;
        this.tipo=1;
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
 public static void serializa(ArrayList<Cliente> Clientes) throws FileNotFoundException, IOException{

        //Gera o arquivo para armazenar o objeto
        FileOutputStream arquivoGrav;

        if (!new File("dados/").exists()) {
            new File("dados/").mkdirs();
        }
        arquivoGrav = new FileOutputStream("dados/registro.dat");

        //Classe responsavel por inserir os objetos
        ObjectOutputStream objGravar = new ObjectOutputStream(arquivoGrav);

        //Grava o objeto no arquivo
        objGravar.writeObject(Clientes);

        objGravar.flush();

        objGravar.close();

        arquivoGrav.flush();

        arquivoGrav.close();
    }

    static ArrayList<Cliente> deserializa() {

        FileInputStream arqLeitura = null;
	ObjectInputStream in = null;
	ArrayList<Cliente> lista = null;
	try {

		//arquivo onde estao os dados serializados
		arqLeitura = new FileInputStream("dados/registro.dat");

		//objeto que vai ler os dados do arquivo
		in = new ObjectInputStream(arqLeitura);

		//recupera os dados
		lista = (ArrayList<Cliente>) in.readObject();
	} catch (ClassNotFoundException ex) {
		ex.printStackTrace();
	} catch (IOException ex) {
		ex.printStackTrace();
	} finally {
		try {
			arqLeitura.close();
			in.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	return lista;
}


    public static ArrayList<Cliente> PagamentosMes()
    {
        ArrayList<Cliente> lista = new ArrayList<Cliente>();
        ArrayList<Cliente> todos = deserializa();

        // pega o mes em que estamos
        Calendar mes = Calendar.getInstance();


        for(Cliente usuario: todos)
        {
            //se o mes da saida eh igual ao mes que procura
            if(usuario.HoraSaida.get(Calendar.MONTH)==mes.get(Calendar.MONTH))
                    lista.add(usuario);
        }

        return lista;
    }
}
