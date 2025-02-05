package Actions;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static org.hamcrest.CoreMatchers.equalTo;

public class Actions {


    // вывести ответ на запрос в консоль
    public static void printResponseBody(Response response) {
        System.out.println(response.body().asString());
    }

    @Step ("Проверка кода ответа на запрос")
    public static void checkResponseCode(Response response, int expectedCode) {
        response.then().statusCode(expectedCode);
    }

    @Step ("Проверка сообщения в теле ответа")
    public static void checkMessage(Response response, String expectedMessage) {
        response.then().assertThat().body("message",equalTo(expectedMessage));
    }

}
