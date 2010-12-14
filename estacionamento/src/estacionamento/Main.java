/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package estacionamento;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


/**
 *
 * @author User
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ArrayList<Cliente> cadastro;
        //Cadastro c = new Cadastro();


        try{
            cadastro = Cadastro.desserializa();
        }
        catch(java.io.FileNotFoundException e){

        }
        catch(java.io.IOException e){

        }
        catch(ClassNotFoundException e){

        }

        FormPrincipal form = new FormPrincipal();
        form.show();
    }

}
