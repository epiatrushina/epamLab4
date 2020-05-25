package presentation;

import java.util.Date;

//Класс для обработки данных рейса
public class FlightDTO {
    //Переменная для хранения названия пункта отправления
    public String from;
    //Переменная для хранения названия пункта прибытия
    public String to;
    //Переменная для хранения даты отправления
    public Date departureDate;
    //Переменная для хранения даты прибытия
    public Date arrivalDate;
    //Переменная для хранения количества пассажиров
    public int passengersAmount;
}
