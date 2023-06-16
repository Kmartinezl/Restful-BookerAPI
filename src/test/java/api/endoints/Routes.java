package api.endoints;

public class Routes {
    public static String baseUrl = "https://restful-booker.herokuapp.com";

    //Auth Module
    public static String createTokenUrl = baseUrl+"/auth";

    //Booking Module
    public static String getBookingIdsUrl = baseUrl+"/booking";
    public static String getBookingUrl = baseUrl+"/booking/{id}";
    public static String createBookingUrl = baseUrl+"/booking";
    public static String updateBookingUrl = baseUrl+"/booking/{id}";
    public static String partialUpdateBookingUrl = baseUrl+"/booking/{id}";
    public static String deleteBookingUrl = baseUrl+"/booking/{id}";

    //Ping Module
    public static String healthCheckUrl = baseUrl+"/ping";

}
