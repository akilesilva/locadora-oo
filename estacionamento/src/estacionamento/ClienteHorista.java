

package estacionamento;

import java.io.Serializable;
import java.util.Calendar;

/**
 *
 * @author Marina
 */
public class ClienteHorista extends Cliente implements Serializable{

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
