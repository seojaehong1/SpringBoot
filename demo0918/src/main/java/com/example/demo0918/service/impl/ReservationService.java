package com.example.demo0918.service.impl;


import com.example.demo0918.dao.ReservationMapper;
import com.example.demo0918.domain.Reservation;
import com.example.demo0918.domain.ReservationItem;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class ReservationService {
    @Autowired
    private ReservationMapper reservationMapper;

    public List<Reservation> getAllReservations() {
        return reservationMapper.findAll();
    }

    public void addReservation(Reservation reservation) {
        reservationMapper.insert(reservation);
    }

    public void deleteReservation(Long id) {
        reservationMapper.delete(id);
    }

    public List<ReservationItem> getItemsByReservationId(Long reservationId) {
        return reservationMapper.findItemsByReservationId(reservationId);
    }

    public void addReservationItem(ReservationItem item) {
        reservationMapper.insertItem(item);
    }

    public void deleteReservationItem(Long id) {
        reservationMapper.deleteItem(id);
    }

    public Reservation getReservationById(Long id) {
        return reservationMapper.findById(id);
    }

}

