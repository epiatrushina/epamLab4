package service;

import DAO.FlightEntity;
import presentation.FlightDTO;

import java.util.Date;

//Класс для преобразования объектов FlightEntity в объекты Flight и наоборот
public class FlightMapper {

	 //Функция, преобразующая объект FlightEntity в объект Flight
    public Flight mapToFlight(FlightEntity flightEntity) {
        String fromPlace = flightEntity.getFromPlace();
        String toPlace = flightEntity.getToPlace();
        Date departureDate = flightEntity.getDepartureDate();
        Date arrivalDate = flightEntity.getArrivalDate();
        int passengersAmount = flightEntity.getPassengersAmount();

        return new Flight(fromPlace, toPlace, departureDate, arrivalDate, passengersAmount);
    }

    //Функция, преобразующая объект Flight в объект FlightEntity
    public FlightEntity mapToFlightEntity(FlightDTO flightDTO){
        String fromPlace = flightDTO.from;
        String toPlace = flightDTO.to;
        Date departureDate = flightDTO.departureDate;
        Date arrivalDate = flightDTO.arrivalDate;
        int passengersAmount = flightDTO.passengersAmount;

        return new FlightEntity(fromPlace, toPlace, departureDate, arrivalDate, passengersAmount);
    }
}
