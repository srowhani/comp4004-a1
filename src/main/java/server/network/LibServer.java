package main.java.server.network;

import main.java.server.io.dao.*;
import main.java.server.io.handler.ClientInputReader;
import main.java.server.io.handler.model.Client;
import main.java.server.io.handler.model.ClientState;
import main.java.server.io.handler.model.Output;
import main.java.util.Config;
import main.java.util.Trace;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* Modified method based on the course COMP 3004 example*/
/*Reference:http://people.scs.carleton.ca/~jeanpier//304W16/T1%20TDD/4b-%20ChatExample%20and%20other%20files/*/
public class LibServer implements Runnable {
    int clientCount = 0;
    private Thread thread = null;
    private ServerSocket server = null;
    private HashMap<Integer, ServerThread> clients;
    private Logger logger = Trace.getInstance().getLogger(this);
    ClientInputReader clientInputReader = new ClientInputReader();
    private List<Client> clientList = new ArrayList<Client>();

    public LibServer(int port) {
        try {
            logger.info("Binding to port " + port);
            clients = new HashMap<Integer, ServerThread>();
            server = new ServerSocket(port);
            server.setReuseAddress(true);
        } catch (IOException ioe) {
            logger.fatal(ioe);
        }
    }

    public void start() {
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
            logger.info(String.format("Server started: %s %d", server, thread.getId()));
            //Initialize the tables

            UserTable.getInstance();
            TitleTable.getInstance();
            ItemTable.getInstance();
            LoanTable.getInstance();
            FeeTable.getInstance();

            System.out.println("Server started successfully!");
        }
    }

    public void run() {
        while (thread != null) {
            try {
                logger.info("Waiting for a client ...");
                addThread(server.accept());
            } catch (IOException e) {
                logger.fatal(e);
            }
        }
    }

    private void addThread(Socket socket) {
        String message = String.format("%s : Client Address : [%15s] Client Socket: [%-6d]\n", "Client accepted", socket.getRemoteSocketAddress(), socket.getPort());
        logger.info(String.format(message));
        if (clientCount < Config.MAX_CLIENTS) {
            try {
                ServerThread serverThread = new ServerThread(this, socket);
                serverThread.open();
                serverThread.start();
                clients.put(serverThread.getID(), serverThread);
                this.clientCount++;
            } catch (IOException e) {
                logger.fatal(e);
            }
        } else {
            logger.info(String.format("Client Tried to connect: %s", socket));
            logger.info(String.format("Client refused: maximum number of clients reached: d", 5));
        }
    }

    public synchronized void handle(int ID, String input) {
        if (input.equals("Exit")) {
            logger.info(String.format("Client: %d Exits", ID));
            if (clients.containsKey(ID)) {
                clients.get(ID).send("Exit" + "\n");
                remove(ID);
                logger.info(String.format("Client : " + ID + "Exits"));
            }
        } else {
            ServerThread from = clients.get(ID);
            logger.info(String.format("Input from %s:%d" + " " + input, from.getSocketAddress(), from.getID()));
            Output serverOutput;
            String output;
            if (exist(from)) {
                ClientState state = getClientStateFromThread(from);
                serverOutput = clientInputReader.processInput(input, state);
                output = serverOutput.getOutput() + "\n";
                from.send(output);
                setClientState(from, serverOutput.getState());
                logger.info(String.format("Output to %s:%d" + " " + output, from.getSocketAddress(), from.getID()));
            } else {
                Client client = new Client(from, ClientState.WAITING);
                clientList.add(client);
                serverOutput = clientInputReader.processInput(input, ClientState.WAITING);
                output = serverOutput.getOutput() + "\n";
                from.send(output);
                setClientState(from, serverOutput.getState());
                logger.info(String.format("Output to %s:%d" + " " + output, from.getSocketAddress(), from.getID()));
            }
            ;

        }
    }


    private void setClientState(ServerThread from, ClientState state) {
        int index = 0;

        for (int i = 0; i < clientList.size(); i++) {
            Client client = clientList.get(i);
            if (client.getServerThread().equals(from)) {
                client.setState(state);
            }
        }
    }

    private ClientState getClientStateFromThread(ServerThread from) {
        ClientState clientState = null;
        for (int i = 0; i < clientList.size(); i++) {
            Client c = clientList.get(i);
            if (c.getServerThread().equals(from)) {
                clientState = clientList.get(i).getState();
                break;
            }
        }
        return clientState;
    }

    private boolean exist(ServerThread from) {
        boolean result = false;
        for (int i = 0; i < clientList.size(); i++) {
            if (clientList.get(i).getServerThread().equals(from)) {
                result = true;
            }
        }
        return result;
    }

    /**
     * Try and shutdown the client cleanly
     */
    public synchronized void remove(int ID) {
        if (clients.containsKey(ID)) {
            ServerThread toTerminate = clients.get(ID);
            clients.remove(ID);
            clientCount--;
            toTerminate.close();
            toTerminate = null;
        }
    }


}
