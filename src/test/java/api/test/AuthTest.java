package api.test;

import api.endoints.AuthEndpoints;
import api.payload.Auth;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.codehaus.groovy.control.io.ReaderSource;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AuthTest {

    Faker faker;
    Auth authPayload;

    @BeforeClass
    public void setupData()
    {
        faker = new Faker();
        authPayload = new Auth();

        authPayload.setUsername(faker.name().firstName());
        authPayload.setPassword(String.valueOf(faker.avatar()));
    }

    @Test
    public void testCreateToken()
    {
        Response response = AuthEndpoints.createToken(authPayload);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(),200);
    }
}
