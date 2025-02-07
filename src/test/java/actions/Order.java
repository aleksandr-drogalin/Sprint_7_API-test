package actions;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import model.InsideListOrdersOrders;
import model.ListOrders;
import org.junit.Assert;

import java.util.List;

import static org.hamcrest.CoreMatchers.notNullValue;

public class Order {

    @Step("В теле ответа содержится трек-номер заказа")
    public static void checkResponseBodyContainsOrderTrackNumber(Response response) {
        response.then().assertThat().body("track", notNullValue());
    }

    @Step("При запросе списка заказов, в тело ответа возвращается список заказов")
    public static void listOrdersIsReturned(Response response) {
        // преобразуем json в объект ListOrders
        ListOrders objectListOrders = response.body().as(ListOrders.class);
        // получаем список заказов
        List<InsideListOrdersOrders> listOrders = objectListOrders.getOrders();
        // проверяем что список не пустой
        Assert.assertFalse(listOrders.isEmpty());
    }
}