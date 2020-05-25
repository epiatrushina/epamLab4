package service;

import DAO.FlightEntity;
import DAO.FlightRepository;
import org.json.simple.parser.ParseException;
import presentation.FlightDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

//Класс для работы с данными рейсов
public class FlightService {
	 //Создаем объект для работы с информацией в файле
    FlightRepository flightRepository = new FlightRepository();
    //Создаем объект для преобразования типов объектов
    FlightMapper mapper = new FlightMapper();
    //Создаем объект для связи индексов и id
    FlightIndexIdMapper flightIndexIdMapper = new FlightIndexIdMapper();

    //Конструктор по умолчанию, может выбросить исключения(ошибки ввода-вывода, ошибки парсирования данных)
    public FlightService() throws ParseException, java.text.ParseException, IOException {
    }

    //Функция, возвращающая массив всех рейсов
    public ArrayList<String> getFlightsStrings() throws Exception {
        //Создаем массив со всеми рейсами
        ArrayList<Flight> flights = getFlights();
        //Создаем пустой массив
        ArrayList<String> flightStrings = new ArrayList<>();
        //Идем по всем рейсам и добавляем их в массив
        for (Flight flight: flights) {
            flightStrings.add(flight.toString());
        }
        return flightStrings;
    }

    //Функция, возвращающая массив с рейсами
    public ArrayList<Flight> getFlights() throws Exception{
    	  //Получаем таблицу с сущностями рейсов
        Hashtable<Integer, FlightEntity> flightsInFile = flightRepository.getAll();
        //Создаем пустой массив
        ArrayList<Flight> flights = new ArrayList<>();
        //Идем по таблице сущностей рейсов
        for (Integer key : flightsInFile.keySet()) {
        		//Записываем объект сущности рейса
            FlightEntity flightEntity = flightsInFile.get(key);
            //Записываем объект сущности рейса в объект рейса
            Flight flight = mapper.mapToFlight(flightEntity);
            //Доюавляем объект рейса в массив
            flights.add(flight);
        }
        return flights;
    }

    //Функция, для получения объекта рейса по его индексу
    public Flight getFlightByOrder(int order) throws Exception {
        return getFlights().get(order);
    }

    //Функция, для добавления нового объекта рейса
    public void addFlight(FlightDTO flightDTO) throws IOException {
    	  //Создаем объект сущности рейса и в него записываем объект рейса
        FlightEntity flightEntity = mapper.mapToFlightEntity(flightDTO);
        //Добавляем новый обхект сущности рейса в файл
        flightRepository.add(flightEntity);
    }

    //Функция, для удаления объекта по его индексу
    public void deleteFlight(int order) throws IOException {
    	  //Получаем id элемента по его индексу
        Integer flightId = flightIndexIdMapper.getIdByIndex(order);
        //Удаляем объект из файла
        flightRepository.delete(flightId);
    }
}
