package AllActions;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;

public class Courier {

    @Step("Проверка тела ответа при успешном создании курьера")
    public static void checkResponseBodyByCorrectCreatingCourier(Response response, boolean expectedBody) {
        response.then().assertThat().body("ok", equalTo(expectedBody));
    }
}
