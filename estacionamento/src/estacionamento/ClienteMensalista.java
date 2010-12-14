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
 * @author User
 */
public class ClienteMensalista extends Cliente implements Serializable{

    boolean emDia = true; // indica se o cliente esta com o pagamento em dia ou atrasado
    String nomeCliente;
    String modelo; // indica o modelo do carro do cliente
    private ClienteHorista usuario;
    ArrayList<ClienteMensalista> clientes=new ArrayList<ClienteMensalista>();

    public ClienteMensalista(){
        this.placa=null;
        this.nomeCliente=null;
        this.modelo=null;
        this.tipo=1;
        this.emDia=true;

    }

    public ClienteMensalista(String nPlaca, String nome, String modelo, boolean pagamento)
    {
        this.placa=nPlaca;
        this.nomeCliente=nome;
        this.modelo=modelo;
        this.tipo=1;
        this.emDia=pagamento;
    }


    public double CalculaValor(Calendar entrada, Calendar saida, ClienteMensalista Cliente)
    {

        if(Cliente.isEmDia())
        {
           //hora, mes e dia da entrada do veiculo no estacionamento
           int mesEntrou = entrada.get(Calendar.MONTH);
           int diaEntrou = entrada.get(Calendar.DAY_OF_MONTH);
           int horaEntrou = entrada.get(Calendar.HOUR_OF_DAY);
           int minEntrou = entrada.get(Calendar.MINUTE);

           int mesSaiu = saida.get(Calendar.MONTH);
           int diaSaiu = saida.get(Calendar.DAY_OF_MONTH);
           int horaSaiu = saida.get(Calendar.HOUR_OF_DAY);
           int minSaiu = saida.get(Calendar.MINUTE);

           //diminui o dia em que saiu, do dia em que entrou para achar a quantidade de tempo
           //em que o veículo ficou no estacionamento
           return (float)((float)((mesSaiu + diaSaiu + horaSaiu + minSaiu) - ( mesEntrou + diaEntrou + horaEntrou + minEntrou)) * this.preco);

        }
        //se o pagamento nao tah em dia calcula como se ele fosse Horista
        else
            usuario = new ClienteHorista(Cliente.getPlaca());
            return usuario.CalculaValor(entrada, saida);
    }


    public boolean isEmDia() {
        return emDia;
    }

    public void setEmDia(boolean emDia) {
        this.emDia = emDia;
    }


    //cadastra o cliente e salva suas informacoes em um arquivo .dat

    public void Cadastro(ClienteMensalista pessoa) throws FileNotFoundException, IOException
    {
        //adiciona no arrayList de clientes mensalistas
        clientes.add(pessoa);

        //adiciona no cadastro de mensalistas
        Serializa(clientes);
        ArrayList<ClienteMensalista> tudo = Deserializa();

    }

    public ArrayList<ClienteMensalista> ListaAtrasados() throws FileNotFoundException, IOException, ClassNotFoundException
    {
        ArrayList<ClienteMensalista> Atrasados = null;
        ArrayList<ClienteMensalista> todos = Deserializa();
        for(Cliente pessoa: clientes)
        {
            ClienteMensalista donoCarro = (ClienteMensalista) pessoa;
            if(donoCarro.isEmDia()==false)
            {
                Atrasados.add(donoCarro);
            }
        }
        return Atrasados;
    }

    public void Serializa (ArrayList<ClienteMensalista> Dados) throws FileNotFoundException, IOException
    {
        //Gera o arquivo para armazenar o objeto
        FileOutputStream arquivoGrav;

        if (!new File("dados/").exists()) {
            new File("dados/").mkdirs();
        }
        arquivoGrav = new FileOutputStream("dados/clientesmensais.dat");

        //Classe responsavel por inserir os objetos
        ObjectOutputStream objGravar = new ObjectOutputStream(arquivoGrav);

        //Grava o objeto no arquivo
        objGravar.writeObject(Dados);

        objGravar.flush();

        objGravar.close();

        arquivoGrav.flush();

        arquivoGrav.close();
    }

    public ArrayList<ClienteMensalista> Deserializa(){

        FileInputStream arqLeitura = null;
	ObjectInputStream in = null;
	ArrayList<ClienteMensalista> todos = null;
	try {

		//arquivo onde estao os dados serializados
		arqLeitura = new FileInputStream("dados/clientesmensais.dat");

		//objeto que vai ler os dados do arquivo
		in = new ObjectInputStream(arqLeitura);

		//recupera os dados
		todos = (ArrayList<ClienteMensalista>) in.readObject();
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

	return todos;
    }
}



