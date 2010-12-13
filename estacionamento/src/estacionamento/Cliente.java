/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package estacionamento;

/**
 *
 * @author User
 */
public class Cliente {

    private String placa;
    private int tipo;// 1 se é mensalista e 0 se é horista
    private double valor=0.0;// quanto deve ser pago pelo cliente. é inicializado com zero
    private boolean emDia = true; // indica se o cliente esta com o pagamento em dia ou atrasado

    //tipo cliente mensalista
    public Cliente (String nPlaca, int codigo, boolean pagamento)
    {
        this.placa=nPlaca;
        this.tipo=codigo;
        this.emDia=pagamento;
    }

    //cliente horista
    public Cliente (String nPlaca, int codigo)
    {
        this.placa=nPlaca;
        this.tipo=codigo;
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



}
