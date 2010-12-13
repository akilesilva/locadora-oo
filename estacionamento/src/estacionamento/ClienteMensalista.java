/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package estacionamento;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author User
 */
public class ClienteMensalista extends Cliente implements Serializable{

    boolean emDia = true; // indica se o cliente esta com o pagamento em dia ou atrasado
    private ClienteHorista usuario;

    public ClienteMensalista(String nPlaca, boolean pagamento)
    {
        this.placa=nPlaca;
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
    public void Cadastro(String placa) throws FileNotFoundException, IOException
    {
          ArrayList<Cliente> clientes=null;
          ClienteMensalista pessoa = new ClienteMensalista (placa,true);
          clientes.add(pessoa);
          Serializa(clientes,"dados/cliente.dat");
    }


}

