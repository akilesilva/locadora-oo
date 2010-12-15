/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package estacionamento;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import estacionamento.ClienteMensalista;
import java.util.ArrayList;

/**
 *
 * @author Marina
 */
public class FormularioCadastro extends JFrame {

    //declaracao dos componentes da janela
    protected JLabel nome, placa, tipoDoCarro;
    protected JButton ok;
    protected JTextField campo1,campo2,campo3;
    protected JPanel panel1 = new JPanel();
    protected JPanel panel2= new JPanel();
    protected JPanel panel3 = new JPanel();
    protected JPanel panel4 = new JPanel();

    public FormularioCadastro(){

        this.setTitle("Cadastro de Mensalista");
        this.setSize(360,250);

        // Cria os componentes
        nome = new JLabel("Nome:"); campo1 = new JTextField(30);
        placa = new JLabel("Placa do veículo:"); campo2 = new JTextField(10);
        ok= new JButton("OK");  
        tipoDoCarro = new JLabel("Modelo do Veículo:"); campo3 = new JTextField(30);
     
        // Define o layout do container básico
        setLayout(new GridLayout(4,1));

        // Define o layout dos Panels
        panel1.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel2.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel3.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel4.setLayout(new FlowLayout(FlowLayout.LEFT));

        // Adiciona os componentes aos panels
        panel1.add(nome); panel1.add(campo1);
        panel2.add(tipoDoCarro); panel2.add(campo3);
        panel3.add(placa); panel3.add(campo2);
        panel4.add(ok);     
        
        // Adiciona os panels ao container básico
        add(panel1); add(panel2);
        add(panel3); add(panel4);


        //adicionando um ouvinte de evento ao botao 'ok'
        //pega o nome,placa e tipo digitado, e cria um novo objeto cliente
        ok.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){

            String nomeCliente = campo1.getText();
            String placa = campo2.getText();
            String tipoC = campo3.getText();

            ClienteMensalista atual = new ClienteMensalista(placa,nomeCliente,tipoC,true);
                try {
                    atual.Cadastro(atual);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(FormularioCadastro.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(FormularioCadastro.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(FormularioCadastro.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(FormularioCadastro.class.getName()).log(Level.SEVERE, null, ex);
                }


            System.exit(0);
       }
        });
   
}
}


