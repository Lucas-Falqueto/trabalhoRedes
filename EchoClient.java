import java.io.*;
import java.net.*;
import java.util.Scanner;

public class EchoClient {
    public static void main(String[] args) throws IOException {
        String hostName = "127.0.0.1";
        int portNumber = 30002;
        System.out.println("cliente ECHO iniciado...");

        Socket serverSocket = null;
        Scanner inputScanner = new Scanner(System.in);
        PrintStream out = System.out;

        try {
            serverSocket = new Socket(hostName, portNumber);

            System.out.println("Conectado ao Servidor.");

            InputStream inputStr = serverSocket.getInputStream();
            InputStreamReader reader = new InputStreamReader(inputStr);
            BufferedReader outServer = new BufferedReader(reader);

            OutputStream outPutStream = serverSocket.getOutputStream();
            OutputStreamWriter outPutWriter = new OutputStreamWriter(outPutStream);
            BufferedWriter bufferedWriter = new BufferedWriter(outPutWriter);
            PrintWriter print = new PrintWriter(bufferedWriter);

            out.print("Digite uma mensagem ");
            String msgUser = inputScanner.nextLine();
            print.println(msgUser);
            print.flush();

            while (!msgUser.equals("sair")) {
                out.print("Mensagem recebida:");
                String msg = outServer.readLine();
                out.println(msg);
                if (msg.equals("sair")) {
                    break;
                }

                out.print("Digite uma mensagem");
                msgUser = inputScanner.nextLine();
                print.println(msgUser);
                print.flush();

            }

            inputScanner.close();
            print.close();

        } catch (IOException e) {
            System.err.println("Não foi possível conectar ao Servidor " + hostName);
            System.exit(1);
        }

    }
}