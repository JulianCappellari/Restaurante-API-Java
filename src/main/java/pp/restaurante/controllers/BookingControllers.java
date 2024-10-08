package pp.restaurante.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pp.restaurante.dto.BookingDto;
import pp.restaurante.entities.Booking;
import pp.restaurante.services.booking.BookingService;
import pp.restaurante.transformers.BookingMapper;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservas")
public class BookingControllers {


    private BookingService bookingService;
    private BookingMapper bookingMapper;

    public BookingControllers( BookingService bookingService, BookingMapper bookingMapper) {
        this.bookingService = bookingService;
        this.bookingMapper = bookingMapper;
    }

    @PostMapping
    public ResponseEntity<BookingDto>  createBooking(@RequestBody BookingDto bookingDto) {
        Booking booking = bookingMapper.toEntity(bookingDto);
        Booking bookingToSave = bookingService.add(booking);
        return ResponseEntity.ok(bookingMapper.toDto(bookingToSave));
    }

    @GetMapping
    public List<BookingDto> getAllBookings() {
        List<Booking> bookings = bookingService.getAll();
        return bookings.stream().map(bookingMapper::toDto).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingDto> getBookingById(@PathVariable Long id) {
        Optional<Booking> booking = bookingService.getById(id);
        return booking.map( b -> ResponseEntity.ok(bookingMapper.toDto(b))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookingDto> updateBooking(@PathVariable Long id, @RequestBody BookingDto bookingDto) {
        Booking bookingToUpdate = bookingMapper.toEntity(bookingDto);
        Booking bookingUpdated = bookingService.update(bookingToUpdate, id);
        return bookingUpdated != null ? ResponseEntity.ok(bookingMapper.toDto(bookingUpdated)) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public boolean deleteBooking(@PathVariable Long id) {
        if (bookingService.getById(id).isPresent()) {
            bookingService.delete(id);
            return true;
        }
        return false;
    }

}
