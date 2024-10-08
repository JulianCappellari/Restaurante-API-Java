package pp.restaurante.services.booking;

import org.springframework.stereotype.Service;
import pp.restaurante.entities.Booking;
import pp.restaurante.respositories.BookingRespository;
import pp.restaurante.transformers.BookingMapper;

import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService{

    private BookingRespository bookingRespository;
    private BookingMapper bookingMapper;
    public BookingServiceImpl(BookingRespository bookingRespository, BookingMapper bookingMapper) {
        this.bookingRespository = bookingRespository;
        this.bookingMapper = bookingMapper;
    }

    @Override
    public Booking add(Booking entity) {
        return bookingRespository.save(entity);
    }

    @Override
    public Optional<Booking> getById(Long id) {
        Booking booking = bookingRespository.findById(id).orElse(null);
        return bookingRespository.findById(id);
    }

    @Override
    public List<Booking> getAll() {
        return bookingRespository.findAll();
    }

    @Override
    public boolean delete(Long id) {
        if(bookingRespository.findById(id).isPresent()) {
            bookingRespository.deleteById(id);
            return true;
        };
        return false;
    }

    @Override
    public Booking update(Booking entity, Long id) {
        return bookingRespository.findById(id).map(booking -> {
            booking.setId(booking.getId());
            booking.setCustomerId(booking.getCustomerId());
            booking.setBookingDate(booking.getBookingDate());
            booking.setNumberPeople(booking.getNumberPeople());
            return bookingRespository.save(booking);

        }).orElse(null);
    }
}
