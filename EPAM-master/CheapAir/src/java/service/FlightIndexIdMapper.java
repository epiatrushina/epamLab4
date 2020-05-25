package service;

import DAO.FlightEntity;
import DAO.FlightRepository;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

//Класс для связи id рейсов с их индексами в массиве
public class FlightIndexIdMapper {
	 //Создаем пустой массив
    private ArrayList<Integer> indexToIdMap = new ArrayList<>();

    //Конструтор по умолчанию
    public FlightIndexIdMapper() throws ParseException, java.text.ParseException, IOException {
        //Получаем таблицу с сущностями рейсов
        Hashtable<Integer, FlightEntity>  hashtable = new FlightRepository().getAll();
        //Доюавляем все id в массив
        indexToIdMap.addAll(hashtable.keySet());
    }

    //Функция, для получения id по индексу в массиве
    public Integer getIdByIndex(int index){
        return indexToIdMap.get(index);
    }
}
