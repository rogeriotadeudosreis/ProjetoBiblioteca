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
public enum EnumNomesDeArquivos {
    
    AREAS, AUTORES, EDITORAS, LIVROS, EXEMPLARES, USUARIOS, SUGESTOES, CDDORIGEM;

    public static EnumNomesDeArquivos getCDDORIGEM() {
        return CDDORIGEM;
    }

    public static EnumNomesDeArquivos getAREAS() {
        return AREAS;
    }

    public static EnumNomesDeArquivos getAUTORES() {
        return AUTORES;
    }

    public static EnumNomesDeArquivos getEDITORAS() {
        return EDITORAS;
    }

    public static EnumNomesDeArquivos getLIVROS() {
        return LIVROS;
    }

    public static EnumNomesDeArquivos getEXEMPLARES() {
        return EXEMPLARES;
    }

    public static EnumNomesDeArquivos getUSUARIOS() {
        return USUARIOS;
    }

    public static EnumNomesDeArquivos getSUGESTOES() {
        return SUGESTOES;
    }
    
    
    
    
}
