package service;

//класс для работы с билетом
public class Ticket {
    //переменная для хранения объекта класса клиента
    private Client client;
    //переменная для хранения объекта класса рейс
    private Flight flight;
    //переменная для хранения цены билета
    private double price;
    //переменная для хранения состояния, есть ли у билета право на посадку на рейс в числе первых
    private boolean rightFirstSitting;
    //переменная для хранения состояния, есть ли у билета право на регистрацию на рейс в числе первых
    private boolean rightFirstRegistration;

    //конструктор по умолчанию
    public Ticket(){
        //задаем переменные "нулями"
        client = null;
        flight = null;
        price = 0.;
        rightFirstSitting = false;
        rightFirstRegistration = false;
    }

    //конструктор с параметрами
    public Ticket(Client client, Flight flight, boolean rightFirstRegistration, boolean rightFirstSitting){
        //задаем переменные переданными параметрами
        this.client = client;
        this.flight = flight;
        this.rightFirstRegistration = rightFirstRegistration;
        this.rightFirstSitting = rightFirstSitting;
        //вычисляем цену
        calculatePrice();
    }

    //метод, возвращающий объект класса клиент
    public Client getClient() {
        return client;
    }

    //метод, задающий объект класса клиента по переданному параметру
    public void setClient(Client client) {
        this.client = client;
    }

    //метод, возвращающий состояние, есть ли у билета право на посадку на рейс в числе первых(true, false)
    public boolean isRightFirstSitting() {
        return rightFirstSitting;
    }

    //метод, задающий состояние, есть ли у билета право на посадку на рейс в числе первых по переданному параметру(true, false)
    public void setRightFirstSitting(boolean rightFirstSitting) {
        this.rightFirstSitting = rightFirstSitting;
    }

    //метод, возвращающий состояние, есть ли у билета право на регистрацию на рейс в числе первых(true, false)
    public boolean isRightFirstRegistration() {
        return rightFirstRegistration;
    }

    //метод, задающий состояние, есть ли у билета право на регистрацию на рейс в числе первых по переданному параметру(true, false)
    public void setRightFirstRegistration(boolean rightFirstRegistration) {
        this.rightFirstRegistration = rightFirstRegistration;
    }

    //метод, возвращающий объект класса рейс
    public Flight getFlight() {
        return flight;
    }

    //метод, задающий объект класса рейс по переданному параметру
    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    //метод, возвращающий цену билета
    public double getPrice() {
        return price;
    }

    //метод, задающий цену билета по переданному параметру
    public void setPrice(double price) {
        this.price = price;
    }

    //метод, вычисляющий цену билета
    private void calculatePrice(){
        double price = 0.;
        //проверяем есть ли в билете право на посадку первым
        if (rightFirstSitting){
            //увеличиваем цену
            price += 10;
        }
        //проверяем есть ли в билете право на регистрацию первым
        if (rightFirstRegistration){
            //увеличиваем цену
            price += 10;
        }
        //добавляем к цене длину перелета умноженную на 10
        price += flight.getDestination().length() * 10;

        //задаем цену билета вычисленной ценой
        this.price = price;
    }
}
