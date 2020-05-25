package service;

import DAO.TicketEntity;
import DAO.TicketRepository;
import presentation.TicketDTO;
import org.json.simple.parser.ParseException;

import java.io.IOException;

//класс для обработки данных из сущности билета
public class TicketService {
    //переменная для хранения объекта для преобразования типов объектов
    private TicketMapper ticketMapper = new TicketMapper();
    //переменная для хранения объекта, работающего с объектами билетов
    private TicketRepository ticketRepository = new TicketRepository();

    //конструктор по умолчанию, может выбросить исключения преобразования типов данных и ввода, вывода
    public TicketService() throws ParseException, java.text.ParseException, IOException {
    }

    //метод, добавляющий новый билет в таблицу, может выбросить исключение
    public void receiveUserTicket(TicketDTO ticketDTO) throws Exception {
        //переменная для хранения сущности билета
        //записываем данные из переданного объекта
        TicketEntity ticketEntity = ticketMapper.mapToTicketEntity(ticketDTO);

        //добавляем новую сущность билета
        ticketRepository.add(ticketEntity);
    }

    //метод, возвращающий цену билета, может выбросить исключение
    public double getTicketPrice(TicketDTO ticketDTO) throws Exception {
        //данные берем из переданного объекта
        return ticketMapper.mapToTicket(ticketDTO).getPrice();
    }
}
