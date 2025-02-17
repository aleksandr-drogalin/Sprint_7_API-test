import actions.GeneralActions;
import actions.Order;
import client.ClientForTest;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import model.CreateOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.List;

@RunWith(Parameterized.class)
public class CreateOrderParameterizedTest {

    private ClientForTest clientForTest; // создаем клиент

    //константы тестовых данных для остальных полей
    private static final String FIRST_NAME = "Сергей";
    private static final String LAST_NAME = "Иванов";
    private static final String ADDRESS = "Москва";
    private static final String METRO_STATION = "5";
    private static final String PHONE = "+7 960 333 45 45";
    private static final int RENT_TIME = 3;
    private static final String DELIVERY_DATE = "2025-02-27";
    private static final String COMMENT = "Без комментариев=)";

    private final CreateOrder createOrder; // объект с тестовыми данными

    //конструктор тестового класса
    public CreateOrderParameterizedTest(CreateOrder createOrder) {
        this.createOrder = createOrder;
    }

    //метод для получения тестовых данных цвета самоката
    @Parameterized.Parameters
    public static List<CreateOrder> getTestData() {
        List<CreateOrder> orderList = new ArrayList<>();
        orderList.add(new CreateOrder(FIRST_NAME, LAST_NAME, ADDRESS, METRO_STATION, PHONE, RENT_TIME, DELIVERY_DATE, COMMENT, new String[] {"BLACK"}));
        orderList.add(new CreateOrder(FIRST_NAME, LAST_NAME, ADDRESS, METRO_STATION, PHONE, RENT_TIME, DELIVERY_DATE, COMMENT, new String[] {"GRAY"}));
        orderList.add(new CreateOrder(FIRST_NAME, LAST_NAME, ADDRESS, METRO_STATION, PHONE, RENT_TIME, DELIVERY_DATE, COMMENT, new String[] {"BLACK", "GRAY"}));
        orderList.add(new CreateOrder(FIRST_NAME, LAST_NAME, ADDRESS, METRO_STATION, PHONE, RENT_TIME, DELIVERY_DATE, COMMENT, new String[] {}));
        return orderList;
    }

    @Before
    public void setUp() {
        clientForTest = new ClientForTest(); // инициализируем клиент
    }

    @Test
    @DisplayName("Выбор цвета самоката")
    public void checkScooterColorTest() {
        Response response = clientForTest.createNewOrder(createOrder); // запрос на создание заказа, ответ сохраняем
        GeneralActions.printResponseBody(response); // выводим ответ в консоль
        GeneralActions.checkResponseCode(response, 201); // проверяем код ответа
        Order.checkResponseBodyContainsOrderTrackNumber(response); // проверяем что в теле ответа содержится трек-номер
        clientForTest.cancelOrder(response); // запрос на отмену заказа
    }
}