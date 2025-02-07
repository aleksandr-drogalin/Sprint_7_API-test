import actions.GeneralActions;
import actions.Order;
import client.ClientForTest;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import model.CreateOrder;
import model.ListOrders;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ListOrderTest {


    private ClientForTest clientForTest; // создаем клиент
    private Response responseForTest; // создаем поле для сохранения ответа на запрос создания заказа перед тестом

    @Before
    public void setUp() {
        clientForTest = new ClientForTest(); // инициализируем клиент
        CreateOrder orderForTest = new CreateOrder("Степан", "Семенов", "Москва", "6", "+7 999 111 22 33", 3, "2025-02-28", "Без комментариев", new String[] {"BLACK"}); // создаем заказ
        responseForTest = clientForTest.createNewOrder(orderForTest); // отправляем запрос на создание заказа
    }

    @Test
    @DisplayName("При запросе списка заказов, в теле ответа возвращается список заказов")
    public void orderListInBodyResponse() {
        Response response = clientForTest.getOrderList(); // отправляем запрос на получение списка заказов
        GeneralActions.printResponseBody(response); // выводим в консоль
        GeneralActions.checkResponseCode(response,200); // проверяем код ответа
        Order.listOrdersIsReturned(response); // проверяем, что вернулся список заказов
    }

    @After
    public void cancelOrder() {
        clientForTest.cancelOrder(responseForTest); // запрос на отмену заказа
    }
}