package presentation;

//Класс для обработки данных билета
public class TicketDTO {
	 //Имя клиента
    public String clientName;
    //Есть ли у клиента багаж
    public boolean clientHaveLuggage;
    //Вес багажа
    public double luggageWeight;
    //Номер рейса
    public int flightOrder;
    //Хочет ли клиент иметь право посадки в числе первых
    public boolean wantRightFirstSitting;
    //Хочет ли клиент иметь право регистрации в числе первых
    public boolean wantRightFirstRegistration;

    //Конструктор по умолчанию
    TicketDTO(){
        clientName = "";
        clientHaveLuggage = false;
        luggageWeight = 0;
        flightOrder = 0;
        wantRightFirstSitting = false;
        wantRightFirstRegistration = false;
    }

    //Перегрузка функции возвращающей значения всех переменных в виде строки
    @Override
    public String toString() {
        return "Ticket {\n" +
                "clientName = " + clientName + ";\n" +
                "clientHaveLuggage = " + ((clientHaveLuggage) ? "yes" : "no") + ";\n" +
                "flightID = " + (flightOrder + 1) + ";\n" +
                "wantRightFirstSitting = " + ((wantRightFirstSitting) ? "yes" : "no") + ";\n" +
                "wantRightFirstRegistration = " + ((wantRightFirstRegistration) ? "yes" : "no") + ";\n" +
                "}";
    }
}
