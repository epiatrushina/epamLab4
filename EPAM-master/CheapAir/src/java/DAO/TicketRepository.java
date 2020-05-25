package DAO;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Hashtable;
import java.util.Optional;
import java.util.Random;

//Класс для работы с объктами билетов, наследвуемый от интерфейса DAO
//Ключ - id билета(Integer), Объект - билет(TicketEntity)
public class TicketRepository implements DAO<Integer, TicketEntity>{

    //Переменная для хранения объекта таблицы(ключ, значение), где ключ это id билета(Integer), а значение это объект билета
    //то есть это объект, в котором мы храним все наши билеты
    //мы можем работать c информацией о любом билете, зная только его id
    Hashtable<Integer, TicketEntity> ticketTable = new Hashtable<Integer, TicketEntity>();

    //Конструктор по умолчанию, может выбросить иссключения(ошибка ввода-вывода, ошибки парсирования данных)
    public TicketRepository() throws IOException, ParseException {
        //Создаем объект для работы с ресурсами класса TicketRepository
        ClassLoader classLoader = TicketRepository.class.getClassLoader();
        //Создаем объект для работы с файлом, который хранит информацию о билетах
        File file = new File(classLoader.getResource("Tickets.json").getFile());
        //Записываем путь к файлу
        String path = file.getAbsolutePath();
        //Создаем объект для парсирования данных из json
        JSONParser jsonParser = new JSONParser();
        //Открываем json файл с информацией о билетах и записываем ее в json-объект
        JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader(path));
        //Записываем в json-массив информацию о билетах
        JSONArray jsonArray = (JSONArray) jsonObject.get("tickets");

        //Идем по json-массиву билетов
        for (Object o : jsonArray) {
            //Записываем информацию о билете в json-оюъект
            JSONObject object = (JSONObject) jsonParser.parse(o.toString());
            //Записываем значения из json-объекта в переменные
            Integer id = Integer.valueOf(object.get("id").toString());
            int flightId = Integer.valueOf(object.get("flightId").toString());
            double price = Double.valueOf(object.get("price").toString());
            boolean rightFirstSitting = Boolean.valueOf(object.get("rightFirstSitting").toString());
            boolean rightFirstRegistration = Boolean.valueOf(object.get("rightFirstRegistration").toString());
            //Добавляем в таблицу новый объект билета
            //ключ - id, значение - объект класса TicketEntity
            ticketTable.put(id, new TicketEntity(flightId, price, rightFirstSitting, rightFirstRegistration));
        }
    }

    //Реализация метода, возвращающего переменную, хранящуюю объект билета по его id
    @Override
    public Optional<TicketEntity> get(Integer id) {
        //Возвращаем значение объекта из таблицы с ключом id
        return Optional.of(ticketTable.get(id));
    }

    //Реализация метода, возвращающего таблицу всех билетов
    @Override
    public Hashtable<Integer, TicketEntity> getAll() {
        return ticketTable;
    }

    //Реализация метода, сохраняющего таблицу билетов в файл, может выбросить иссключение(ошибка ввода-вывода)
    @Override
    public void save() throws IOException {
        //Создаем объект для работы с ресурсами класса TicketRepository
        ClassLoader classLoader = TicketRepository.class.getClassLoader();
        //Создаем объект для работы с файлом, который хранит информацию о билетах
        File file = new File(classLoader.getResource("Tickets.json").getFile());
        //Записываем путь к файлу
        String fileName = file.getAbsolutePath();
        //Записываем в строку все данные объекта FlightRepository, т.е. таблицу билетов
        String str = this.toString();
        //Создаем объект для вывода данных в файл
        FileOutputStream outputStream = new FileOutputStream(fileName);
        //Записываем нашу строку в массив байт
        byte[] strToBytes = str.getBytes();
        //Сохраняем данные в файл
        outputStream.write(strToBytes);
        //Закрываем файл
        outputStream.close();
    }

    //Реализация метода, добавляющего новый объект билета, может выбросить иссключение(ошибка ввода-вывода)
    @Override
    public void add(TicketEntity ticketEntity) throws IOException {
        //Генерируем id билета
        Integer id = new Random().nextInt(9000) + 1000;
         //Пока сгенерированный id уже существует, генерируем новый
        while (ticketTable.keySet().contains(id)){
            id = new Random().nextInt(9000) + 1000;
        }
        //Добавляем в таблицу новый объект билета
        //ключ - id, значение - переданный объект класса TicketEntity
        ticketTable.put(id, ticketEntity);
        //Сохраняем изменения в файл
        this.save();
    }

    //Реализация метода, обновляющего объект билета по его id, передаем новый объект билета и id объекта, который надо обновить
    //может выбросить иссключение(ошибка ввода-вывода)
    @Override
    public void update(TicketEntity ticketEntity, Integer id) throws IOException {
        //Удаляем из таблицы объект с ключом id
        ticketTable.remove(id);
        //Добавляем в таблицу новый объект билета
        //ключ - id, значение - переданный объект класса TicketEntity
        ticketTable.put(id, ticketEntity);
        //Сохраняем изменения в файл
        this.save();
    }

    //Реализация метода, удаляющего объект билета по его id, может выбросить иссключение(ошибка ввода-вывода)
    @Override
    public void delete(Integer id) throws IOException {
        //Удаляем из таблицы объект с ключом id
        ticketTable.remove(id);
        //Сохраняем изменения в файл
        this.save();
    }

    //Перегрузка функции возвращающей значения всех переменных в виде строки
    //Данные будут записаны в формате json
    @Override
    public String toString() {
        //Создаем объект в который будем записывать значения наших переменных
        StringBuilder sb = new StringBuilder("{\n");
        //Добавляем в объект по очереди все названия переменных и их значения
        sb.append("\"tickets\" : [\n");
        //Идем по таблице билетов
        for (Integer key: ticketTable.keySet()){
            sb.append("{\n");
            //Добавляем id билета
            sb.append("\"id\" : ").append(key).append(",\n");
            //Добавляем объект TicketEntity
            sb.append(ticketTable.get(key).toString());
            sb.append("},\n");
        }
        //Удаляем лишнюю запятую
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append("]\n}");
        return sb.toString();
    }
}
