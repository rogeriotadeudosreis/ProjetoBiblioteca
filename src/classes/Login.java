
package classes;

import ENUMERADORES.EnumTipoDeUsuario;


public class Login {
    
    private String email = null;
    private String senha = "";
    
   
   public Login ()throws Exception{
       
   } 
   
   public Login (String email, String senha)throws Exception{
       this.email = email;
       this.senha = senha;
   }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
  
}
