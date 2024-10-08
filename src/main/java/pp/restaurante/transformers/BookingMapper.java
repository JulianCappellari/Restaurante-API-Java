package pp.restaurante.transformers;


import org.springframework.stereotype.Component;
import pp.restaurante.dto.BookingDto;
import pp.restaurante.entities.Booking;


@Component
public class BookingMapper {

    public BookingDto toDto(Booking booking) {
        return new BookingDto(
                booking.getId(),
                booking.getCustomerId(),
                booking.getBookingDate(),
                booking.getNumberPeople()
        );
    }

    public Booking toEntity(BookingDto bookingDto) {
        return new Booking(
                bookingDto.getId(),
                bookingDto.getCustomerId(),
                bookingDto.getBookingDate(),
                bookingDto.getNumberPeople()
        );
    }
}
