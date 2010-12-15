/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package estacionamento;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Marina
 */
public class Movimento {

    //clientes mensalistas cadastrados
     ArrayList<ClienteMensalista> cadastrados;

     //registro de entrada e saida de veiculos do estacionamento, tanto mensalistas quando horistas
     ArrayList<Cliente> TotalClientes;

     //objeto da classe cliente para acessar seu métodos
     Cliente obj = new Cliente();

    public void Entrada(String placa) throws FileNotFoundException, IOException, ClassNotFoundException, Exception
    {
        int codigo = 0;


        //cria um objeto Cliente Mensalista e 'pega' todos os clientes mensalistas
        ClienteMensalista mensal = new ClienteMensalista();
        cadastrados = mensal.deserializaMensal();

        //pega a hora que entrou para armazenar no registro do cliente
        Calendar hora = Calendar.getInstance();

        //primeiro procura se é mensalista
        for(ClienteMensalista pessoa : cadastrados)
        {
            codigo = pessoa.getTipo();
            //se eh mensalista
            if(pessoa.getPlaca().equals(placa))
            {
                //adiciona a hora de entrada no cadastro
                pessoa.setHoraEntrada(hora);

                //atualiza o .dat com a nova hora do mensalista
                pessoa.SerializaMensal(cadastrados);

                //armazena a entrada no registro total
                TotalClientes.add(pessoa);
                obj.serializa(TotalClientes);

                break;
            }
        }
        if(codigo==0)//eh horista
        {
            //cria um novo cliente do tipo horista
            ClienteHorista usuario = new ClienteHorista(placa);
            //armazena a hora que entrou
            usuario.setHoraEntrada(hora);
            //adiciona no ArrayList de todos os clientes
            TotalClientes.add(usuario);
            //salva no registro o novo cliente
            obj.serializa(TotalClientes);

        }
    }


    public void Saida(String placa) throws FileNotFoundException, IOException, ClassNotFoundException, Exception{

        Calendar horaSaida = Calendar.getInstance();

        int codigo=0;

        for(Cliente pessoa: TotalClientes)
        {
            codigo = pessoa.getTipo();

            //procura o cliente com a placa dada, no registro total de clientes
            if(pessoa.getPlaca().equals(placa))
            {
                if(pessoa.getTipo()==1)//eh mensalista
                {
                    //pega a hora de saída do mensalista e atualiza seu cadastro
                    pessoa.setHoraSaida(horaSaida);
                    obj.serializa(TotalClientes);
                }
                else // eh horista
                {
                    //cria um objeto do tipo ClienteHorista para usar o metodo calcula valor
                    ClienteHorista usuario = (ClienteHorista) pessoa;

                    //pega a hora de entrada, a hora de saída e calcula
                    Calendar entrada = usuario.getHoraEntrada();

                    //seta o valor a ser pago
                    usuario.setValor(usuario.CalculaValor(entrada,horaSaida));

                    //atualiza o registro de clientes
                    obj.serializa(TotalClientes);
                 }
                break;
            }
        }

}
}
