package service;

//класс для работы с багажом
public class Luggage {
    //переменная для хранения веса багажа
    private double weight;
    //переменная для хранения объекта класса клиент
    private Client client;
    //переменная для хранения состояния, есть ли перевес или нет(true, false)
    private boolean overweight;

    //метод, возвращающий вес багажа
    public double getWeight() {
        return weight;
    }

    //метод, возвращающий объект класса клиент
    public Client getClient() {
        return client;
    }

    //метод, возвращающий состояние, есть ли перевес или нет(true, false)
    public boolean overweight() {
        return overweight;
    }

    //конструктор по умолчанию
    public Luggage(){
        //задаем переменные "нулями"
        this.weight = 0;
        this.client = null;
        this.overweight = false;
    }

    //конструктор с параметрами
    public Luggage(double weight, Client client){
        //задаем переменные переданными параметрами
        this.weight = weight;
        this.client = client;
        //проверяем и записываем есть ли перевес или нет
        this.overweight = isOverweight(weight);
    }

    //метод, вычисляющий есть ли перевес или нет(true, false) по переданному параметру
    //проверяем является ли вес багажа допустимым
    private boolean isOverweight(double weight){
        return weight <= Constants.MAX_WEIGHT;
    }

    // TODO: Create service that allows find luggage by its client
}
