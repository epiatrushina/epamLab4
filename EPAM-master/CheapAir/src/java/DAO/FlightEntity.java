package DAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

//Класс для хранения сущности объекта рейса
public class FlightEntity {
	//Переменная для хранения названия пункта отправления
    private String fromPlace;
    //Переменная для хранения названия пункта прибытия
    private String toPlace;
    //Переменная для хранения даты отправления
    private Date departureDate;
    //Переменная для хранения даты прибытия
    private Date arrivalDate;
    //Переменная для хранения количества пассажиров
    private int passengersAmount;

    //Конструктор по умолчанию
    public FlightEntity(){
    	//Заполняем переменные "нулями"
        this.fromPlace = null;
        this.toPlace = null;
        this.departureDate = null;
        this.arrivalDate = null;
        this.passengersAmount = 0;
    }

    //Конструктор с параметрами
    public FlightEntity(String fromPlace, String toPlace, Date departureDate, Date arrivalDate, int passengersAmount){
        //Заполняем переменные переданными значениями
        this.fromPlace = fromPlace;
        this.toPlace = toPlace;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.passengersAmount = passengersAmount;
    }

    //Функция, возвращающая название пункта отправления
    public String getFromPlace() {
        return fromPlace;
    }

    //Функция, задающая новое название пункта отправления
    public void setFromPlace(String fromPlace) {
        this.fromPlace = fromPlace;
    }

    //Функция, возвращающая название пункта прибытия
    public String getToPlace() {
        return toPlace;
    }

    //Функция, задающая новое название пункта прибытия
    public void setToPlace(String toPlace) {
        this.toPlace = toPlace;
    }

    //Функция, возвращающая дату отпраления
    public Date getDepartureDate() {
        return departureDate;
    }

    //Функция, задающая новую дату отпраления
    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    //Функция, возвращающая дату прибытия
    public Date getArrivalDate() { return arrivalDate; }

    //Функция, задающая новую дату прибытия
    public void setArrivalDate(Date arrivalDate) { this.arrivalDate = arrivalDate; }

    //Функция, возвращающая количество пассажиров
    public int getPassengersAmount() {
        return passengersAmount;
    }

    //Функция, задающая новое количество пассажиров
    public void setPassengersAmount(int passengersAmount) {
        this.passengersAmount = passengersAmount;
    }

    //Перегрузка функции возвращающей значения всех переменных в виде строки
    @Override
    public String toString() {
    	//Создаем объект в который будем записывать значения наших переменных
        final StringBuilder sb = new StringBuilder("");
        //Добавляем в объект по очереди все названия переменных и их значения
        sb.append("\"fromPlace\" : ").append("\"").append(fromPlace).append("\",\n");
        sb.append("\"toPlace\" : ").append("\"").append(toPlace).append("\",\n");
        //Создаем объект для форматирования вывода даты
        SimpleDateFormat ft = new SimpleDateFormat("dd.MM.yyyy', 'hh:mm a");
        sb.append("\"departureDate\" : ").append("\"").append(ft.format(departureDate)).append("\",\n");
        sb.append("\"arrivalDate\" : ").append("\"").append(ft.format(arrivalDate)).append("\",\n");
        sb.append("\"passengersAmount\" : ").append(passengersAmount).append("\n");
        return sb.toString();
    }
}
