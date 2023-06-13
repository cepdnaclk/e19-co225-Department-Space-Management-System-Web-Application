package com.example.SharedSpaces.db;

import com.example.SharedSpaces.models.Reservation;
import com.example.SharedSpaces.models.User;
import com.example.SharedSpaces.repos.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationDB {

    private ReservationRepository reservationRepository;

    @Autowired
    public ReservationDB(ReservationRepository reservationRepository){
        this.reservationRepository = reservationRepository;
    }

    public List<Reservation> getAllResevation() {
        return (List<Reservation>) reservationRepository.findAll();
    }

    public Optional<Reservation> getReservationById(Long id) {
        return reservationRepository.findById(id);
    }

//    public Optional<Reservation> getReservationByEmail(String email) {
//        if (email == null) {
//            return Optional.empty();
//        }
//
//        Optional<User> optionalUser = reservationRepository.findByEmail(email);
//
//        if (!optionalUser.isPresent()) {
//
//            // In here, we are returning an empty optional
//            return Optional.empty();
//        }
//
//        return optionalUser;
//    }


    public Reservation createUser(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public Reservation updateUser(Long id, Reservation reservation) {
        reservation.setId(id);
        return reservationRepository.save(reservation);
    }

    public void deleteUser(Long id) {
        reservationRepository.deleteById(id);
    }


}
