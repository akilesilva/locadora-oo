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
 * @author User
 */
public class Movimento {

    //clientes mensalistas cadastrados
     ArrayList<Cliente> cadastrados;
     
     //registro de entrada e saida de veiculos do estacionamento, tanto mensalistas quando horistas
     ArrayList<Cliente> TotalClientes;

    public void Entrada(String placa) throws FileNotFoundException, IOException, ClassNotFoundException
    {

        int codigo = 0;

        cadastrados = ClienteMensalista.Desserializa("dados/clientes.dat");

        //pega a hora que entrou e armazena no cadastro do cliente
        Calendar hora = Calendar.getInstance();

        //primeiro procura se é mensalista
        for(Cliente pessoa : cadastrados)
        {

            codigo = pessoa.getTipo();
            //se eh mensalista
            if(pessoa.getPlaca().equals(placa))
            {
                pessoa.setHoraEntrada(hora);

                ClienteMensalista.Serializa(cadastrados,"dados/clientes.dat");//atualiza o cadastro com a nova hora do mensalista

                //armazena a entrada no registro total
                TotalClientes.add(pessoa);
                Cliente.Serializa(TotalClientes, "dados/registro.dat");

                break;
            }
        }
        if(codigo==0)//eh horista
        {
            //cria um novo cliente do tipo horista
            ClienteHorista usuario = new ClienteHorista(placa);
            //armazena a hora que entrou
            usuario.setHoraEntrada(hora);
            TotalClientes.add(usuario);
            Cliente.Serializa(TotalClientes, "dados/registro.dat");
            
        }
    }


    public void Saida(String placa) throws FileNotFoundException, IOException, ClassNotFoundException{

        Calendar horaSaida = Calendar.getInstance();

        int codigo=0;

        for(Cliente pessoa: TotalClientes)
        {
            codigo = pessoa.getTipo();

            //procura o cliente com a placa dada
            if(pessoa.getPlaca().equals(placa))
            {
                if(pessoa.getTipo()==1)//eh mensalista
                {
                    //pega a hora de saída do mensalista e atualiza seu cadastro
                    pessoa.setHoraSaida(horaSaida);
                    Cliente.Serializa(TotalClientes, "dados/registro.dat");
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
                    Cliente.Serializa(TotalClientes,"dados/registro.dat" );
                 }
                break;
            }
        }

}
}
