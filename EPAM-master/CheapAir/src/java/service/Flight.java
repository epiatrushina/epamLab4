package service;

import java.util.Date;

//Класс для работы с рейсом
public class Flight {
	//Переменная для хранения названия пункта отправления
    private String from;
    //Переменная для хранения названия пункта прибытия
    private String to;
    //Переменная для хранения даты отправления
    private Date departureDate;
    //Переменная для хранения даты прибытия
    private Date arrivalDate;
    //Переменная для хранения количества пассажиров
    private int passengersAmount;

    //Функция, возвращающая название пункта прибытия
    public String getDestination() {
        return to;
    }

    //Функция, возвращающая название пункта прибытия
    public Date getDepartureDate() {
        return departureDate;
    }

    //Функция, задающая новую дату отпраления
    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    //Функция, возвращающая количество пассажиров
    public int getPassengersAmount() {
        return passengersAmount;
    }

    //Конструктор с параметрами
    public Flight(String from, String to, Date departureDate, Date arrivalDate, int passengersAmount) {
        //Заполняем переменные переданными значениями
        this.from = from;
        this.to = to;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.passengersAmount = passengersAmount;
        //Проверяем количество мест на рейсе
        checkSeats();
    }

    //Функция, проверяющая
    public void checkSeats() {
        try{
        		//Если количество пассажиров слишком большое
            if (!enoughtSeats()){
            	 //Генерируем исключение
                throw new Exception("Flight " + from + "-" + to + " has too much seats");
            }
        }
        catch (Exception e){
        	   //Выводим сообщение об ошибке
            System.out.println(e.toString());
            passengersAmount = 0;
        }
    }

    //Функция, проверяющая не привосходит ли количество пассажиров их максимального числа
    private boolean enoughtSeats(){
        return passengersAmount <= Constants.MAX_PASSENGERS;
    }

    //Перегрузка функции возвращающей значения всех переменных в виде строки
    @Override
    public String toString() {
        return "From: ".concat(from).concat("\n")
                .concat("To: ").concat(to).concat("\n")
                .concat("Date: ").concat(String.valueOf(departureDate)).concat(" - ")
                .concat(String.valueOf(arrivalDate)).concat("\n")
                .concat("Amount of valuable sits: ").concat(
                        String.valueOf(Constants.MAX_PASSENGERS - passengersAmount)
                ).concat("\n");
    }
}
