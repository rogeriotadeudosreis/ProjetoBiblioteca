
package utilidades;
import javax.swing.JOptionPane;

public class Util_ProjetoID {
   
    public static void main(String[] args) {
        try {
            Util_GeradorIdentificador objetoId = new Util_GeradorIdentificador();
            System.out.println("ID: " + objetoId.getID());
            //System.out.println("ID: " + objetoId.getID());
            //System.out.println("ID: " + objetoId.getID());
            objetoId.finalize();
            
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());
        }
    }
    
}
