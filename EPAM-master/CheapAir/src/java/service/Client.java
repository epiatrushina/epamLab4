package service;

//класс для работы с клиентом(его именем и багажом)
public class Client {
    //переменная для хранения имени клиента
    private String name;
    //переменная для хранения состоянии багажа(есть или нет)(true, false)
    private boolean haveLuggage;

    //метод, возвращающий имя клиента
    public String getName() {
        return name;
    }

    //метод, задающий имя клиента
    public void setName(String name) {
        this.name = name;
    }

    //метод, возвращающий(true, false) есть ли багаж или нет
    public boolean isHaveLuggage() {
        return haveLuggage;
    }

    //метод, задающий состояние багажа(есть или нет)
    public void setHaveLuggage(boolean haveLuggage) {
        this.haveLuggage = haveLuggage;
    }

    //конструктор по умолчанию
    public Client(){
        //задаем значения переменных "нулями"
        name = "";
        haveLuggage = false;
    }

    //конструктор с параметрами
    public Client(String name, boolean haveLuggage){
        //задаем значения переменных переданными параметрами
        //к имени вызываем метод для проверки его на корректность
        this.name = isNameCorrect(name) ? name : "";
        this.haveLuggage = haveLuggage;
    }

    //метод, проверяющий на корректность имени
    //если начинается с большой буквы(возвращает true), в противном случае(false)
    private boolean isNameCorrect(String name){
        return name.startsWith(String.valueOf(name.toCharArray()[0]).toUpperCase());
    }
}
