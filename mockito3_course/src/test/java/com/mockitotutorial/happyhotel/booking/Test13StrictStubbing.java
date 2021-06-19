package com.mockitotutorial.happyhotel.booking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.*;

import java.time.LocalDate;
import java.util.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class Test13StrictStubbing {

	@InjectMocks
	private BookingService bookingService;
	
	@Mock
	private PaymentService paymentServiceMock;
	
	@Mock
	private RoomService roomServiceMock;
	
	@Spy
	private BookingDAO bookingDAOMock;
	
	@Mock
	private MailSender mailSenderMock;
	
	@Captor
	private ArgumentCaptor<Double> doubleCaptor;
	
	@Test
	void should_InvokePayment_When_Prepaid() {
		//given
		BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2020, 01, 01), LocalDate.of(2020, 01, 05), 2, false);
		// when(paymentServiceMock.pay(any(), anyDouble())).thenReturn("1"); if uncommented an exception will be thrown with the message "Unnecesary stubbings detected"
		// if we need the above line and ignore the exception then use lenient() to bypass the strict stubbing like this:
		lenient().when(paymentServiceMock.pay(any(), anyDouble())).thenReturn("1");
		
		//when
		bookingService.makeBooking(bookingRequest);
		
		//then
		// no exception is thrown
	}

}
