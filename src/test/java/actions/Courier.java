package actions;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import model.GetIdCourier;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class Courier {

    @Step("Проверка тела ответа при успешном создании курьера")
    public static void checkResponseBodyByCorrectCreatingCourier(Response response, boolean expectedBody) {
        response.then().assertThat().body("ok", equalTo(expectedBody));
    }

    @Step("При успешном запросе на авторизацию возвращается id курьера")
    public static void checkReturnId(Response response){
        response.then().assertThat().body("id", notNullValue());
    }

    // получить id после авторизации учетной записи курьера (применяется для отправки запроса на удаление учетной записи см. ClientForTest)
    public static String getCourierId(Response response) {
        GetIdCourier getIdCourier = response.body().as(GetIdCourier.class);
        return getIdCourier.getId();
    }

    // получить код ответа на создание учетной записи курьера (применяется для проверки, была содзана учетная запись по запросу или нет см. CreateCourierTest )
    public static int checkCreatedCourierStatusCode(Response response) {
        int statusCode = response.getStatusCode();
        return statusCode;
    }
}