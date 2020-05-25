package DAO;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Optional;
import java.util.Random;

//Класс для работы с объктами рейсов, наследвуемый от интерфейса DAO
//Ключ - id рейса(Integer), Объект - рейс(FlightEntity)
public class FlightRepository implements DAO<Integer, FlightEntity> {

    //Переменная для хранения объекта таблицы(ключ, значение), где ключ это id рейса(Integer), а значение это объект рейса
    //то есть это объект, в котором мы храним все наши рейсы
    //мы можем работать c информацией о любом рейсе, зная только его id
    Hashtable<Integer, FlightEntity> flightTable = new Hashtable<Integer, FlightEntity>();

    //Конструктор по умолчанию, может выбросить иссключения(ошибка ввода-вывода, ошибки парсирования данных)
    public FlightRepository() throws IOException, ParseException, java.text.ParseException {
        //Создаем объект для работы с ресурсами класса FlightRepository
        ClassLoader classLoader = FlightRepository.class.getClassLoader();
        //Создаем объект для работы с файлом, который хранит информацию о рейсах
        File file = new File(classLoader.getResource("Flights.json").getFile());
        //Записываем путь к файлу
        String path = file.getAbsolutePath();
        //Создаем объект для парсирования данных из json
        JSONParser jsonParser = new JSONParser();
        //Открываем json файл с информацией о рейсах и записываем ее в json-объект
        JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader(path));
        //Записываем в json-массив информацию о рейсах
        JSONArray jsonArray = (JSONArray) jsonObject.get("flights");

        //Идем по json-массиву рейсов
        for (Object o : jsonArray) {
            //Записываем информацию о рейсе в json-оюъект
            JSONObject object = (JSONObject) jsonParser.parse(o.toString());
            //Записываем значения из json-объекта в переменные
            String fromPlace = object.get("fromPlace").toString();
            String toPlace = object.get("toPlace").toString();
            //Создаем объект для форматирования даты
            SimpleDateFormat ft = new SimpleDateFormat("dd.MM.yyyy', 'hh:mm a");
            String departureDateString = object.get("departureDate").toString();
            //Записываем дату, применяя форматирование
            Date departureDate = ft.parse(departureDateString);
            String arrivalDateString = object.get("arrivalDate").toString();
            Date arrivalDate = ft.parse(arrivalDateString);
            int passengersAmount = Integer.parseInt(object.get("passengersAmount").toString());
            Integer id = Integer.valueOf(object.get("id").toString());
            //Добавляем в таблицу новый объект рейса
            //ключ - id, значение - объект класса FlightEntity
            flightTable.put(id, new FlightEntity(fromPlace, toPlace, departureDate, arrivalDate, passengersAmount));
        }
    }

    //Реализация метода, возвращающего переменную, хранящуюю объект рейса по его id
    @Override
    public Optional<FlightEntity> get(Integer id) {
        //Возвращаем значение объекта из таблицы с ключом id
        return Optional.of(flightTable.get(id));
    }

    //Реализация метода, возвращающего таблицу всех рейсов
    @Override
    public Hashtable<Integer, FlightEntity> getAll() {
        return flightTable;
    }

    //Реализация метода, сохраняющего таблицу рейсов в файл, может выбросить иссключение(ошибка ввода-вывода)
    @Override
    public void save() throws IOException {
        //Создаем объект для работы с ресурсами класса FlightRepository
        ClassLoader classLoader = FlightRepository.class.getClassLoader();
        //Создаем объект для работы с файлом, который хранит информацию о рейсах
        File file = new File(classLoader.getResource("Flights.json").getFile());
        //Записываем путь к файлу
        String fileName = file.getAbsolutePath();
        //Записываем в строку все данные объекта FlightRepository, т.е. таблицу рейсов
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

    //Реализация метода, удаляющего объект рейса по его id, может выбросить иссключение(ошибка ввода-вывода)
    @Override
    public void delete(Integer id) throws IOException {
        //Удаляем из таблицы объект с ключом id
        flightTable.remove(id);
        //Сохраняем изменения в файл
        this.save();
    }

    //Реализация метода, обновляющего объект рейса по его id, передаем новый объект рейса и id объекта, который надо обновить
    //может выбросить иссключение(ошибка ввода-вывода)
    @Override
    public void update(FlightEntity flightEntity, Integer id) throws IOException {
        //Удаляем из таблицы объект с ключом id
        flightTable.remove(id);
        //Добавляем в таблицу новый объект рейса
        //ключ - id, значение - переданный объект класса FlightEntity
        flightTable.put(id, flightEntity);
        //Сохраняем изменения в файл
        this.save();
    }

    //Реализация метода, добавляющего новый объект рейса, может выбросить иссключение(ошибка ввода-вывода)
    @Override
    public void add(FlightEntity flightEntity) throws IOException {
        //Генерируем id рейса
        Integer id = new Random().nextInt(900) + 100;
        //Пока сгенерированный id уже существует, генерируем новый
        while (flightTable.keySet().contains(id)){
            id = new Random().nextInt(900) + 100;
        }
        //Добавляем в таблицу новый объект рейса
        //ключ - id, значение - переданный объект класса FlightEntity
        flightTable.put(id, flightEntity);
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
        sb.append("\"flights\" : [\n");
        //Идем по таблице рейсов
        for (Integer key: flightTable.keySet()){
            sb.append("{\n");
            //Добавляем id рейса
            sb.append("\"id\" : ").append(key).append(",\n");
            //Добавляем объект FlightEntity
            sb.append(flightTable.get(key).toString());
            sb.append("},\n");
        }
        //Удаляем лишнюю запятую
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append("]\n}");
        return sb.toString();
    }
}