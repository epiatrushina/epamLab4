package service;

import DAO.ClientEntity;
import DAO.ClientRepository;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Optional;

//класс для обработки данных из сущности клиента
public class ClientService {
    //объект класса для работы с объектами сущности клиентов
    private ClientRepository clientRepository = new ClientRepository();

    //конструктор по умолчанию, может выбросить исключение ввода, вывода и исключение перевода из одного типа данных в другой
    public ClientService() throws IOException, ParseException {
    }

    //метод, возвращающий состояние клиента, является ли он админом(true, false), может выбросить исключение
    public boolean isAdmin(String username) throws Exception {
        //создаем переменную для хранения объекта сущности клиента
        Optional<ClientEntity> clientEntityOptional = clientRepository.get(username);
        //записываем в новый объект сущности клиента или клиента, или "ноль"
        ClientEntity clientEntity = clientEntityOptional.orElse(null);
        //проверяем записался ли клиент
        if (clientEntity != null){
            //если да, возвращаем является ли он админом(true, false)
            return clientEntity.isAdmin();
        }
        else{
            //если нет, выбрасываем ошибку, что нет такого клиента
            throw new Exception("No such user");
        }
    }
}
