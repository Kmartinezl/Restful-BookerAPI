package api.endoints;


import api.payload.Booking;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.awt.geom.AffineTransform;
import java.util.Date;

import static io.restassured.RestAssured.given;

//Create to perform Create, Read, Update and Delete requests to the booking API
public class BookingEndpoints {

    //CRUD
    public static Response createBooking (Booking payload)
    {
        Response response =
                given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post(Routes.createBookingUrl);

        return response;
    }

    public static Response readBooking (int id)
    {
        Response response =

                given()
                .accept(ContentType.JSON)
                .pathParam("id", id)
                .when()
                .get(Routes.getBookingUrl);

        return response;
    }


    public static Response updateBooking (int id, Booking payload, String token)
    {
        Response response =

                given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .cookie(token)
                .pathParam("id", id)
                .body(payload)
                .when()
                .put(Routes.updateBookingUrl);

        return response;
    }

    public static Response deleteBooking (int id, String token)
    {
       Response response =

               given()
               .contentType(ContentType.JSON)
               .cookie(token)
               .pathParam("id", id)
               .when()
               .delete(Routes.deleteBookingUrl);

       return response;
    }

    // Additionals Booking endpoints

    public static Response getBookingIds(Booking payload)
    {
        Response response = given()
                .body(payload)

                .when()
                .get(Routes.getBookingUrl);

        return response;
    }
    public  static Response partialUpdateBooking(int id, Booking payload, String token)
    {
        Response response =

                given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .cookie(token)
                .pathParam("id", id)
                .body(payload)
                .when()
                .patch(Routes.partialUpdateBookingUrl);

        return response;
    }
}
