import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.given;

public class StatusCodeTest extends ApiTestBase {

    @Before
    public void setUp() {}

    /**
     * This test case checks status code for example swagger api link
     */
    @Test
    public void TesCheckAddNewBooks() {
         given()
                 .contentType("application/json")
                 .when()
                 .get(API_ROOT)
                        .then()
                 .statusCode(200)
                 .log().body()
                 .extract().response();

    }
}
