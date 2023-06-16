package api.test;

import api.endoints.BookingEndpoints;
import api.payload.Booking;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BookingTest {

    Faker faker;
    Booking bookingPayload;
    @BeforeClass
    public void setupData()
    {
        faker = new Faker();
        bookingPayload = new Booking();

        bookingPayload.setFirstname(faker.name().firstName());
        bookingPayload.setLastname(faker.name().lastName());
        bookingPayload.setTotalprice(faker.number().hashCode());
        //bookingPayload.setTotalprice(Integer.parseInt(faker.number().digits(3)));
        bookingPayload.setDepositpaid(faker.bool().bool());
    }

    @Test(priority = 1)
    public void testCreateBooking()
    {
        Response response = BookingEndpoints.createBooking(bookingPayload);
        response.then().log().all();

        //Validations
        Assert.assertEquals(response.getStatusCode(),200);

    }
}
