/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author daniel
 */
public class Util_ComunicadorTCP {
    
    private Socket conexao;
    private ServerSocket ouvir;
    private DataInputStream receberMsg;
    private DataOutputStream enviarMsg;
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //Construtor Cliente
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public Util_ComunicadorTCP(String ipServer, int porta) throws IOException{
        this.conexao = new Socket(ipServer,porta);
        this.receberMsg = new DataInputStream(this.conexao.getInputStream());
        this.enviarMsg = new DataOutputStream(this.conexao.getOutputStream());
    }
    
    //Construtor Servidor
    public Util_ComunicadorTCP(int porta) throws IOException{
        this.ouvir = new ServerSocket(porta);
        this.conexao = this.ouvir.accept();
        this.receberMsg = new DataInputStream(this.conexao.getInputStream());
        this.enviarMsg = new DataOutputStream(this.conexao.getOutputStream());
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //Get and Set
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
    public Socket getSocket() throws IOException{
        this.conexao = ouvir.accept();
        return this.conexao;
    }
    public ServerSocket getServerSocket() throws IOException{
        return this.ouvir;
    }
    
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //Metodos
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
    //envia uma mensagem texto utilizando o objeto DataOutpuStream
    public void enviarMensagem(String mensagem) throws IOException{
        this.enviarMsg.writeUTF(mensagem);
        this.enviarMsg.flush();
    }
    //recebe uma mensagem texto utilizando o objeto DataInputStream
    public String receberMensagem() throws IOException{
        String msg = this.receberMsg.readUTF();
         return msg;
    }   
    public void fecharConexao() throws IOException{
        this.conexao.close();
    }
}
