package api.endoints;

import api.payload.Auth;
import api.payload.Booking;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.awt.geom.AffineTransform;

import static io.restassured.RestAssured.given;

public class AuthEndpoints {

    public static Response createToken(Auth payload)
    {
        Response response =

                given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post(Routes.createTokenUrl);

        return response;
    }
}
