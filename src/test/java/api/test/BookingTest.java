package api.test;

import api.endoints.AuthEndpoints;
import api.endoints.BookingEndpoints;
import api.payload.Auth;
import api.payload.Booking;
import api.payload.BookingDates;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BookingTest {

    int  bookingId;
    String token;
    Faker faker;
    Booking bookingPayload;
    Auth authPayload;
    BookingDates bookingDatesPayload;
    @BeforeClass
    public void setupData()
    {
        faker = new Faker();
        bookingPayload = new Booking();
        bookingDatesPayload = new BookingDates();
        authPayload = new Auth();

        authPayload.setUsername("admin");
        authPayload.setPassword("password123");

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

    //---------------------------------------- AUTH -----------------------------------------------------------------
    @Test(priority = 1)
    public void testCreateToken()
    {
        Response response = AuthEndpoints.createToken(authPayload);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(),200);

        token = response.path("token");
        System.out.println(token);

    }
    //---------------------------------------- CRUD -----------------------------------------------------------------
    @Test(priority = 2)
    public void testCreateBooking()
    {
        Response response = BookingEndpoints.createBooking(bookingPayload);
        response.then().log().all();

        //Validations
        Assert.assertEquals(response.getStatusCode(),200);

        bookingId = response.path("bookingid");
        System.out.println(bookingId);
    }

   @Test(priority = 3, dependsOnMethods = {"testCreateBooking"})
    public void testReadBooking()
    {
        Response response = BookingEndpoints.readBooking(bookingId);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test(priority = 4/*, dependsOnMethods = {"testReadBooking"}*/)
    public void testUpdateBooking()
    {
        bookingPayload.setFirstname(faker.name().firstName());
        bookingPayload.setLastname(faker.name().lastName());
        Response response = BookingEndpoints.updateBooking(bookingId, bookingPayload, token);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 5, dependsOnMethods = {"testUpdateBooking"})
    public void testDeleteBooking()
    {
        Response response = BookingEndpoints.deleteBooking(bookingId, token);
        response.then().log().all();
    }

    //---------------------------------------------------------------------------------------------------------------
    @Test
    public void testGetBookingIds()
    {
        Response response = BookingEndpoints.getBookingIds(bookingPayload);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
