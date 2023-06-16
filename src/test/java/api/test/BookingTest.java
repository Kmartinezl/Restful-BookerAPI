package api.test;

import api.endoints.BookingEndpoints;
import api.payload.Booking;
import api.payload.BookingDates;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BookingTest {

    Faker faker;
    Booking bookingPayload;
    BookingDates bookingDatesPayload;
    @BeforeClass
    public void setupData()
    {
        faker = new Faker();
        bookingPayload = new Booking();
        bookingDatesPayload = new BookingDates();

        bookingPayload.setFirstname(faker.name().firstName());
        bookingPayload.setLastname(faker.name().lastName());
        //bookingPayload.setTotalprice(faker.number().hashCode());
        bookingPayload.setTotalprice(Integer.parseInt(faker.number().digits(3)));
        bookingPayload.setDepositpaid(faker.bool().bool());
        bookingDatesPayload.setCheckin(faker.date().birthday());
        bookingDatesPayload.setCheckout(faker.date().birthday());
        bookingPayload.setBookingdates(bookingDatesPayload);
        bookingPayload.setAdditionalneeds(faker.space().planet());
    }

    @Test(priority = 1)
    public void testCreateBooking()
    {
        Response response = BookingEndpoints.createBooking(bookingPayload);
        response.then().log().all();

        //Validations
        Assert.assertEquals(response.getStatusCode(),200);
    }

   @Test(priority = 2)
    public void testReadBooking()
    {
        Response response = BookingEndpoints.readBooking(4569);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test
    public void testUpdateBooking()
    {
        Response response = BookingEndpoints.updateBooking(4569, bookingPayload);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void testDeleteBooking()
    {
        Response response = BookingEndpoints.deleteBooking(4569);
        response.then().log().all();
    }
}
