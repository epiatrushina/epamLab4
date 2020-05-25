package DAO;

import DBConnector;
import java.io.*;
import java.util.Hashtable;
import java.util.Optional;

public class ClientRepository implements DAO<String, ClientEntity> {


    Hashtable<String, ClientEntity> clientTable = new Hashtable<String, ClientEntity>();

    public ClientRepository() throws IOException, ParseException {

        ArrayList<Client> allClients = new ArrayList<Client>();
        String query = "SELECT * FROM CLIENT";
        try {
            ResultSet resultSet = this.dbConnector.getQueryResultAsResultSet(query);
            while (resultSet.next()) {
                String username = resultSet.getString("USER_NAME");
                boolean isAdmin = resultSet.getBoolean("IS_ADMIN");

                allClients.add(new Client(name, isAdmin));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

    }


    @Override
    public Optional<ClientEntity> get(String username) {
        return Optional.of(clientTable.get(username));
    }

    @Override
    public Hashtable<String, ClientEntity> getAll() {
        return clientTable;
    }

    @Override
    public void save() throws IOException {
        ClassLoader classLoader = ClientRepository.class.getClassLoader();
        File file = new File(classLoader.getResource("lowcost.db").getFile());
        String fileName = file.getAbsolutePath();
        String str = this.toString();
        FileOutputStream outputStream = new FileOutputStream(fileName);
        byte[] strToBytes = str.getBytes();
        outputStream.write(strToBytes);
        outputStream.close();
    }

    @Override
    public void add(ClientEntity clientEntity) throws IOException {
        clientTable.put(clientEntity.getUsername(), clientEntity);
        this.save();
    }

    @Override
    public void delete(String username) throws IOException {
        clientTable.remove(username);
        this.save();
    }

    @Override
    public void update(ClientEntity clientEntity, String username) throws IOException {
        clientTable.remove(username);
        clientTable.put(username, clientEntity);
        this.save();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{\n");
        sb.append("\"clients\" : [\n");
        for (String key: clientTable.keySet()){
            sb.append("{\n");
            sb.append(clientTable.get(key).toString());
            sb.append("},\n");
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append("]\n}");
        return sb.toString();
    }


}
