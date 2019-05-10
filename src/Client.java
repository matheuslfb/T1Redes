import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client extends Thread {


    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.println("Informe o IP do servidor:");
        String ip = s.next();
        System.out.println("Informe a porta para conexao");
        int porta = s.nextInt();


        try{

            Socket conexao = new Socket(ip, porta);

            Thread t = new Client(conexao);
        }
        catch (IOException e){
            System.out.println("IOException " + e);
        }
    }

    private Socket conexao;

    public Client(Socket s){
        conexao = s;
    }


    public void run(){
        try{//cria uma printstream para pegar as informacoes enviadas do servidor
            PrintStream saida = new PrintStream(conexao.getOutputStream());
            //cria um bufferedreader para receber informacoes digitadas
            BufferedReader teclado = new BufferedReader (new InputStreamReader(System.in));
            //escreve uma mensagem ao usuario
            System.out.println("entre com algum digito");
            //digito recebe o valor digitado pelo usuario
            String digito = teclado.readLine();
            //imprime o valor de digito
            saida.println(digito);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            entrada.readLine();
        }catch(IOException e){
            System.out.println("IOException"+e);
        }finally{
            try {
                conexao.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }



      }
    }
}