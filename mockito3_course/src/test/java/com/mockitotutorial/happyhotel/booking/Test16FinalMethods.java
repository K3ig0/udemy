package com.mockitotutorial.happyhotel.booking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.*;

class Test16FinalMethods {

	private BookingService bookingService;
	private PaymentService paymentServiceMock;
	private RoomService roomServiceMock;
	private BookingDAO bookingDAOMock;
	private MailSender mailSenderMock;

	@BeforeEach
	void setup() {
		this.paymentServiceMock = mock(PaymentService.class);
		this.roomServiceMock = mock(RoomService.class);
		this.bookingDAOMock = mock(BookingDAO.class);
		this.mailSenderMock = mock(MailSender.class);
		this.bookingService = new BookingService(paymentServiceMock, roomServiceMock, bookingDAOMock, mailSenderMock);
	}

	@Test
	void should_CountAvailablePlaces_When_OneRoomAvailable() {
		// given
		/**
		 * here we changed the method "getAvailableRooms" adding the "final" modifier
		 * it works because we changed the pom to "mockito-inline" instead of "mockito-core" when used annotations (Test11Annotations)
		 * 
		 * by the way, private methods cannot be mocked using Mockito, but can be mocked using PowerMock
		 * in the initial versions of Mockito, constructors, final and static methods could not be mocked, so PowerMock was used instead
		 * however, PowerMock does not support all features of JUnit5, and should not be necessary nowadays
		 */
		when(this.roomServiceMock.getAvailableRooms()).thenReturn(Collections.singletonList(new Room("Room 1", 5)));
		int expected = 5;

		// when
		int actual = bookingService.getAvailablePlaceCount();

		// then
		assertEquals(expected, actual);
	}
	
}
