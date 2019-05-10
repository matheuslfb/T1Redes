import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {

    public static void main(String[] args) {
        ServerSocket server = null;

        try {//tenta criar uma conexao
            server = new ServerSocket(6000); // cria um SocketServer com a porta 6000

            while(true){

                Socket cliente = server.accept();
                //cria uma thread que envia a conexao
                Thread t = new Server(cliente);
                //inicia a thread T
                t.start();
            }


        } catch (IOException e) {
            System.out.println("IOException " + e);
        }
    }


    public void run(){
        try {
            //cria um buffer que irá armazenar as informaçoes enviadas pelo cliente
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(conexao.getInputStream()));

            //Cria uma stream de saída para retorno das informacoes ao cliente
            DataOutputStream outToClient = new DataOutputStream(conexao.getOutputStream());

            //armazena as informacoes enviadas opelo client
            String strClient = inFromClient.readLine();
            //imprime no console do server
            System.out.println("Voce digitou: "+ strClient.length() + "digitos");
            //imprime a string modificada no console do client
            outToClient.writeBytes("Voce digotu: "+ strClient.length()+" digitos");
        }catch (IOException e){
            System.out.println("IOException "+ e);
        }
    }



    private Socket conexao;

    public Server(Socket s){
        conexao = s;
    }
}
