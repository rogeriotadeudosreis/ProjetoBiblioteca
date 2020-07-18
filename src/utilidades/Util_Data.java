/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import java.util.Calendar;

/**
 *
 * @author roger
 */
public class Util_Data {

    int dia = 0;
    int mes = 0;
    int ano = 0;

    public Util_Data() {

    }

    public Util_Data(int dia, int mes, int ano) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    public void setData(int dia, int mes, int ano) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }
    
    public String getData(){
       String data =  String.format("%02d/%02d/%d", dia, mes, ano);     
         System.out.println(data);
        return data;
    }
    
    public String getDataAtual(){
        
        return imprimirDataAtual();
    }

    public String imprimirDataAtual() {
        Calendar data = Calendar.getInstance();        
        
        dia = data.get(Calendar.DAY_OF_MONTH);
        mes = data.get(Calendar.MONTH);
        ano = data.get(Calendar.YEAR);
        int hora = data.get(Calendar.HOUR_OF_DAY);
        int minutos = data.get(Calendar.MINUTE);
        int segundos = data.get(Calendar.SECOND);

        String dataAtual =(String.format("%02d/%02d/%d %02d:%02d:%02d", dia, (mes + 1), ano, hora, minutos, segundos));
        System.out.println(dataAtual);
        return dataAtual;

    }

}
