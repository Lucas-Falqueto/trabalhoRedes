import java.io.*;
import java.net.*;
import java.util.Scanner;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        String localhost = "127.0.0.1";
        int portNumber = 30002;

        Scanner inputScanner = new Scanner(System.in);
        PrintStream out = System.out;

        Socket clientSocket = null;
        ServerSocket serverSocket = null;

        try {
            InetAddress addressConect = InetAddress.getByName(localhost);
            serverSocket = new ServerSocket(portNumber, 50, addressConect);
            System.out.println("Servidor ECHO pronto... Pressione CTRL+C para sair. \nAguardando o Cliente...");
            clientSocket = serverSocket.accept();
            System.out.println("Chat iniciado");

            InputStream inputStr = clientSocket.getInputStream();
            InputStreamReader reader = new InputStreamReader(inputStr);
            BufferedReader outUser = new BufferedReader(reader);

            OutputStream outPutStream = clientSocket.getOutputStream();
            OutputStreamWriter outPutWriter = new OutputStreamWriter(outPutStream);
            BufferedWriter bufferedWriter = new BufferedWriter(outPutWriter);
            PrintWriter print = new PrintWriter(bufferedWriter);

            out.println("Aguardando receber mensagem...");
            String msgUser = outUser.readLine();
            while (true) {
                out.println(msgUser);
                if (msgUser.equals("sair")) {
                    break;
                }

                System.out.println("Digite nova mensagem...");
                String myMsg = inputScanner.nextLine();
                print.println(myMsg);
                print.flush();

                if (myMsg.equals("sair")) {
                    break;
                }

                out.println("Mensagem recebida:");
                outUser.readLine();
            }
            out.close();
            inputScanner.close();
        } catch (IOException e) {
            System.out.println("Erro detectado ao tentar ouvir a porta " + portNumber + " ou escutar a conexão. ");
            System.out.println(e.getMessage());
        }
    }
}