/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package estacionamento;

import java.util.Calendar;

/**
 *
 * @author Marina
 */
public class ClienteHorista extends Cliente {

    public ClienteHorista(String numPlaca)
    {
        this.placa=numPlaca;
        this.tipo=0;
    }

    public double CalculaValor(Calendar Entrada, Calendar Saida)
    {
        int horaEntrou = Entrada.get(Calendar.HOUR_OF_DAY);
        int minEntrou = Entrada.get(Calendar.MINUTE);
        int horaSaiu = Saida.get(Calendar.HOUR_OF_DAY);
        int minSaiu = Saida.get(Calendar.MINUTE);

        return (float)((float)((horaSaiu + minSaiu) - (horaEntrou + minEntrou)) * this.preco);
    }

}
