package DAO;

//Класс для хранения сущности объекта билета
public class TicketEntity {
    //Переменная для хранения id рейса
    private int flightId;
    //Переменная для хранения стоимости билета
    private double price;
    //Переменная для хранения состояния есть ли у билета право на посадку на рейс в числе первых
    private boolean rightFirstSitting;
    //Переменная для хранения состояния есть ли у билета право на регистрацию на рейс в числе первых
    private boolean rightFirstRegistration;

    //Конструктор по умолчанию
    public TicketEntity(){
        //Заполняем переменные "нулями"
        this.flightId = 0;
        this.price = 0.0;
        this.rightFirstSitting = false;
        this.rightFirstRegistration = false;
    }

    //Конструктор с параметрами
    public TicketEntity(int flightId, double price, boolean rightFirstSitting, boolean rightFirstRegistration) {
        //Заполняем переменные переданными значениями
        this.flightId = flightId;
        this.price = price;
        this.rightFirstSitting = rightFirstSitting;
        this.rightFirstRegistration = rightFirstRegistration;
    }

    //Функция, возвращающая id рейса
    public int getFlightId() {
        return flightId;
    }

    //Функция, задающая новый id рейса
    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    //Функция, возвращающая стоимость билета
    public double getPrice() {
        return price;
    }

    //Функция, задающая новую стоимость билета
    public void setPrice(double price) {
        this.price = price;
    }

    //Функция, возвращающая состояние(true, false) есть ли у билета право на посадку на рейс в числе первых
    public boolean isRightFirstSitting() {
        return rightFirstSitting;
    }

    //Функция, задающая новое состояние(true, false) есть ли у билета право на посадку на рейс в числе первых
    public void setRightFirstSitting(boolean rightFirstSitting) {
        this.rightFirstSitting = rightFirstSitting;
    }

    //Функция, возвращающая состояние(true, false) есть ли у билета право на регистрацию на рейс в числе первых
    public boolean isRightFirstRegistration() {
        return rightFirstRegistration;
    }

    //Функция, задающая новое состояние(true, false) есть ли у билета право на регистрацию на рейс в числе первых
    public void setRightFirstRegistration(boolean rightFirstRegistration) {
        this.rightFirstRegistration = rightFirstRegistration;
    }

    //Перегрузка функции возвращающей значения всех переменных в виде строки
    @Override
    public String toString() {
        //Создаем объект в который будем записывать значения наших переменных
        final StringBuilder sb = new StringBuilder("");
        //Добавляем в объект по очереди все названия переменных и их значения
        sb.append("\"flightId\" : ").append(flightId).append(",\n");
        sb.append("\"price\" : ").append(price).append(",\n");
        sb.append("\"rightFirstSitting\" : ").append(rightFirstSitting).append(",\n");
        sb.append("\"rightFirstRegistration\" : ").append(rightFirstRegistration).append("\n");
        return sb.toString();
    }
}
