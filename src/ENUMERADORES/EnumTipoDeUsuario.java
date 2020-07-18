/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ENUMERADORES;

/**
 *
 * @author roger
 */
public enum EnumTipoDeUsuario {
   ADVOGADO, ESTAGIARIO, FUNCIONARIO, ADMINISTRADOR;    
    
    public static EnumTipoDeUsuario getADVOGADO() {    
        return ADVOGADO;
    }

    public static EnumTipoDeUsuario getESTAGIARIO() {
        return ESTAGIARIO;
    }

    public static EnumTipoDeUsuario getFUNCIONARIO() {
        return FUNCIONARIO;
    }
    public static EnumTipoDeUsuario getADMINISTRADOR(){
        return ADMINISTRADOR;
    }
}
