package presentation;

import service.ClientService;
import service.FlightService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.TicketService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

//Класс для работы с консолью
public class Console {
	 //Сохдаем объект для вывода логов
    public final static Logger log = LogManager.getLogger(Console.class.getName());
    //Сохдаем объект для ввода с консоли
    static final Scanner sc = new Scanner(System.in);

    //Переменная, отвечающая за права доступа к фунциям
    boolean isAdmin = false;
    //Переменная для храения логина пользователя
    String userLogin = "guest";

    public static void main(String[] args) throws Exception {
        Console console = new Console();
        //Создаем переменные для вывода меню
        String menuStr = "\n------\n"
                + "1. All flights;\n"
                + "2. Log in;\n"
                + "3. Exit.\n"
                + "------\n"
                +"Choose menu item\n>> ";
        String menuStrAdmin = "\n------\n"
                + "1. All flights;\n"
                + "2. Logout;\n"
                + "3. Manage flights;\n"
                + "4. Exit.\n"
                + "------\n"
                +"Choose menu item\n>> ";
        String menuStrUser = "\n------\n"
                + "1. All flights;\n"
                + "2. Logout;\n"
                + "3. Exit.\n"
                + "------\n"
                +"Choose menu item\n>> ";
        //Вызываем метод для работы с меню
        console.menu(menuStr, menuStrAdmin, menuStrUser);
    }

    //Метод, отвечающий за работу с меню
    public void menu(String menuStr, String menuStrAdmin, String menuStrUser) throws Exception {
        //Переменная отвечающая за выбор пользоателя
        int choose = -1;
        //Переменная храняющая значения выбора для выхода
        int chooseExit = 4;
        //Пока не хотим выйти
        while(choose != chooseExit){
        		//В зависимости от типа пользователя задаем значение выхода
            chooseExit = (isAdmin) ? 4 : 3;
            //Рисуем меню
            menuOutput(menuStr, menuStrAdmin, menuStrUser);
            //Пользователь делает выбор
            while (!sc.hasNextInt() || (choose = sc.nextInt()) < 1 || choose > chooseExit){
                //Выводим сообщение о некорректном выборе
                wrongInput();
                System.out.print(">> ");
            }
            switch (choose) {
                case 1:
                    //Вызов меню для работы с рейсами
                    chooseFlight();
                    break;
                case 2:
                    //Вход пользователя в систему
                    userLogin = logInOut();
                    break;
                case 3:
                    //Выход, либо редактирование рейсов
                    if (chooseExit == 3){
                        break;
                    }
                    manageFlights();
            }
        }
    }

    //Функция, отвечающая за вход пользователя в систему
    private String logInOut() throws Exception {
    	  //Если пользователь выходит из системы, он становится "гостем"
        if (!userLogin.equals("guest")){
            System.out.println("Now you logged as guest");
            pause();
            isAdmin = false;
            return "guest";
        }
        System.out.print("\nEnter your login\n>> ");
        String login = sc.next();
        //Создаем объект для работы с пользователями
        ClientService clientService = new ClientService();
        boolean admin;
        try {
            admin = clientService.isAdmin(login);
        }
        catch (Exception ignored){
        	   //Случай если пользователя не существует
            System.out.println("There are no such user. Returning to menu.");
            pause();
            return "guest";
        }
        if (admin) {
            System.out.println("\nHello, " + login +". You've logged as admin!");
            log.info("Admin logged in.");
            isAdmin = true;
        }
        else {
            System.out.println("\nHello, " + login +". You've logged as user!");
        }
        pause();
        return login;
    }

    //Функция, для редактирования рейсов
    public void manageFlights() throws Exception {
        int chooseExit = 3;
        int choose = -1;
        //Меню
        while(choose != chooseExit) {
            System.out.print("Manage menu"
                    + "\n------\n"
                    + "1. Add;\n"
                    + "2. Delete;\n"
                    + "3. Exit.\n"
                    + "------\n"
                    + "Choose menu item\n>> ");
            //Выбор пользователя
            while (!sc.hasNextInt() || (choose = sc.nextInt()) < 1 || choose > 3) { //check for proper input
                wrongInput();
                System.out.print(">> ");
            }
            switch (choose) {
                case 1:
                    addFlight();
                    break;
                case 2:
                    deleteFlight();
                    break;
            }
        }
    }

    //Функция, добавляющая новый рейс
    public void addFlight() throws Exception {
    	  //Создаем объект для хранения данных о рейсе
        FlightDTO flight = new FlightDTO();
        //Воодим данные
        System.out.print("\nWhere does the flight come from?\n>> ");
        flight.from = sc.next();

        System.out.print("Where the flight goes to?\n>> ");
        flight.to = sc.next();

        System.out.print("Enter amount of passengers:\n>> ");
        //Проверка на корректный ввод
        while (!sc.hasNextInt() || (flight.passengersAmount = sc.nextInt()) < 0) { //check for proper input
            wrongInput();
            System.out.print(">> ");
        }

        System.out.print("Enter departure date(dd.MM.yyyy)\n>> ");
        //Объект для форматирования даты
        SimpleDateFormat ft = new SimpleDateFormat("dd.MM.yyyy");
        //Вводим дату, пока не введем в нужном формате
        for (int i = 0; i < 1; i++) {
            try {
                flight.departureDate = ft.parse(sc.next());
            } catch (Exception ignored) {
                System.out.print("Input error\n>> ");
                i--;
            }
        }

        System.out.print("Enter arrival date(dd.MM.yyyy)\n>> ");
        //Вводим дату, пока не введем в нужном формате
        for (int i = 0; i < 1; i++) {
            try {
                flight.arrivalDate = ft.parse(sc.next());
            } catch (Exception ignored) {
                System.out.print("Input error\n>> ");
                i--;
            }
        }
        //Создаем объект для работы с данными рейсов
        FlightService flightService = new FlightService();
        //Добавляем новый рейс
        flightService.addFlight(flight);
        System.out.println("Flight successfully added.");
        pause();
    }

    //Функция, удаляющая рейс
    public void deleteFlight() throws Exception {
    	  //Выводим список всех рейсов
        int flightsSize = showFlights();
        System.out.print("Choose flight\n>> ");
        //Выбираем рейс
        while (!sc.hasNextInt()) { //check for proper input
            wrongInput();
            System.out.print(">> ");
        }
        int flightNum = sc.nextInt();
        //Проверка на корректность данных
        if (flightNum < 1 || flightNum > flightsSize){
            System.out.println("There are no such flight. Go to menu");
            pause();
            return;
        }
        //Создаем объект для работы с данными рейсов
        FlightService flights = new FlightService();
        //Удаляем рейс
        flights.deleteFlight(flightNum - 1);
        System.out.println("Flight deleted successfully.");
        pause();
    }

    //Функция, для выбора рейса
    public void chooseFlight() throws Exception {
    	  //Выводим список всех рейсов
        int flightsSize = showFlights();
        //Вводим номер рейса
        System.out.print("Enter number of flight to buy a ticket\n>> ");
        while (!sc.hasNextInt()) { //check for proper input
            wrongInput();
            System.out.print(">> ");
        }
        int flightNum = sc.nextInt();
        if (flightNum < 1 || flightNum > flightsSize){
            System.out.println("There are no such flight. Returning to menu");
            pause();
            return;
        }
        //Вызываем функцию создания билета
        createTicket(flightNum);
    }

    //Функция, выводящая все рейсы и возвращающая их кол-во
    int showFlights() throws Exception {
    	  //Создаем объект для работы с данными рейсов
        FlightService flights = new FlightService();
        //Записываем в массив все рейсы в виде строк и выводим их
        ArrayList<String> flightsList = flights.getFlightsStrings();
        System.out.print("\nFlights:\n------\n\n");
        for (int i = 0; i < flightsList.size(); i++) {
            System.out.println((i+1) + ". " + flightsList.get(i));
        }
        System.out.print("------\n");
        return flightsList.size();
    }

    //Функция для создания билета
    public void createTicket(int flightNum) throws Exception {
    	  //Создаем объект для хранения данных о билете
        TicketDTO ticket = new TicketDTO();
        //Получаем индекс рейса
        ticket.flightOrder = flightNum - 1;
        System.out.print("Enter you name\n>> ");
        //Заполняем данные
        ticket.clientName = sc.next();

        System.out.print("Do you have luggage?(y/n)\n>> ");
        ticket.clientHaveLuggage = sc.next().equals("y");
        if(ticket.clientHaveLuggage){
            System.out.print("Enter luggage weight\n>> ");
            while (!sc.hasNextInt() || (ticket.luggageWeight = sc.nextInt()) <= 0) {
                wrongInput();
                System.out.print(">> ");
            }
        }

        System.out.print("Do you want to be first on registration?(y/n)\n>> ");
        ticket.wantRightFirstRegistration = sc.next().equals("y");

        System.out.print("Do you want to be first on sitting?(y/n)\n>> ");
        ticket.wantRightFirstSitting = sc.next().equals("y");

        //Создаем объект для работы с билетами
        TicketService ticketService = new TicketService();
        double ticketPrice = ticketService.getTicketPrice(ticket);
        System.out.print("\nFinal price is " + ticketPrice + ". Do you want to buy it?(y/n)\n>> ");
        if(sc.next().equals("y")){
        		//Доюавляем билет
            ticketService.receiveUserTicket(ticket);
            System.out.println("\nYour " + ticket.toString() + '\n');
        }
        pause();
    }

    //Функция, рисующая меню
    public void menuOutput(String menuStr, String menuStrAdmin, String menuStrUser){
        System.out.print("Hello, " + userLogin + "! ");
        if(isAdmin) {
            System.out.print(menuStrAdmin);//check for admin menu
        }
        else if(!userLogin.equals("guest")) {
            System.out.print(menuStrUser);
        }
        else {
            System.out.print(menuStr);
        }
    }

    //Функция, выводящая сообщение об ошибке ввода
    public static void wrongInput(){
        sc.nextLine();
        System.out.println("Input error");
    }

    //Функция, для паузы в работе программы
    public static void pause(){
        System.out.print("Enter any symbol to continue...");
        sc.next();
        System.out.println();
    }
}