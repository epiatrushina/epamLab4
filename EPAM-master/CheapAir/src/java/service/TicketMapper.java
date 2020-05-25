package service;

import DAO.TicketEntity;
import presentation.TicketDTO;
import org.json.simple.parser.ParseException;

import java.io.IOException;

//класс для преобразования ticketDTO в объект билета и его сущность
//класс для преобразования ticketDTO в объект билета и его сущность
public class TicketMapper {
    //переменная для хранения объекта для работы с данными рейсов
    private FlightService flightService = new FlightService();
    //переменная для хранения объекта, связывающего id рейсов с их индексами
    private FlightIndexIdMapper flightIndexIdMapper = new FlightIndexIdMapper();

    //конструктор по умолчанию, может выбросить исключения перевода из одного типа данных в другой и ввода, вывода
    public TicketMapper() throws ParseException, java.text.ParseException, IOException {
    }

    //метод, преобразующий переданный объект класса ticketDTO в объект класса TicketEntity, может выбросить исключение
    public TicketEntity mapToTicketEntity(TicketDTO ticketDTO) throws Exception {
        //переменная для хранения объекта класса билет
        //записыввем данные из переданного объекта
        Ticket ticket = mapToTicket(ticketDTO);

        //переменная для хранения id рейса
        //записываем данные из переданного объекта
        Integer flightId = flightIndexIdMapper.getIdByIndex(ticketDTO.flightOrder);
        //переменная для хранения цены билета
        double price = ticket.getPrice();
        //переменная для хранения состояния, есть ли у билета право на посадку на рейс первым
        boolean rightFirstSitting = ticket.isRightFirstSitting();
        //переменная для хранения состояния, есть ли у билета право на регистрацию на рейс первым
        boolean rightFirstRegistration = ticket.isRightFirstRegistration();

        //возвращаем новые объект класса сущности билета
        return new TicketEntity(flightId, price, rightFirstSitting, rightFirstRegistration);

    }

    //метод, преобразующий переданный объект класса ticketDTO в объект класса Ticket, может выбросить исключение
    public Ticket mapToTicket(TicketDTO ticketDTO) throws Exception {
        //переменная для хранения имени клиента
        //записываем данные из переданного объекта
        String name = ticketDTO.clientName;
        //переменная для хранения состояния о багаже(есть ли перевес или нет)(true, false)
        //записываем данные из переданного объекта
        boolean haveLuggage = ticketDTO.clientHaveLuggage;

        //переменная для хранения объекта клиента
        Client client = new Client(name, haveLuggage);

        //переменная для хранения объекта рейса
        //записываем данные из переданного объекта
        Flight flight = flightService.getFlightByOrder(ticketDTO.flightOrder);
        //переменная для хранения состояния, есть ли у билета право на посадку на рейс первым
        //записываем данные из переданного объекта
        boolean rightFirstSitting = ticketDTO.wantRightFirstSitting;
        //переменная для хранения состояния, есть ли у билета право на регистрацию на рейс первым
        //записываем данные из переданного объекта
        boolean rightFirstRegistration = ticketDTO.wantRightFirstRegistration;
        //возвращаем новый объект класса билет
        return new Ticket(client, flight, rightFirstRegistration, rightFirstSitting);
    }
}
