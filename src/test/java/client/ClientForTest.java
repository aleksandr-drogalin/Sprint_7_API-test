package client;

import actions.Courier;
import actions.GeneralActions;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import model.CreateCourier;
import model.CreateOrder;
import model.GetTrackOrder;

import static io.restassured.RestAssured.given;

public class ClientForTest {

    private final String BASE_URI = "http://qa-scooter.praktikum-services.ru";
    // ручка создания учетной записи курьера
    private final String END_URI_CREATE_COURIER = "/api/v1/courier";
    // ручка авторизации учетной записи курьера
    private final String END_URI_AUTHORIZATION_COURIER = "/api/v1/courier/login";
    // ручка удаления учетной записи курьера
    private final String END_URI_DELETE_COURIER = "/api/v1/courier/";
    // ручка создания заказа
    private final String END_URI_CREATE_ORDER = "/api/v1/orders";
    // ручка получения списка заказов
    private final String END_URI_GET_ORDER_LIST = "/api/v1/orders";
    // ручка отмены заказа
    private final String END_URI_CANCEL_ORDER = "/api/v1/orders/cancel";


    @Step("Отправка запроса на создание учетной записи курьера")
    public Response createNewCourier(CreateCourier createCourier) {
        return given().baseUri(BASE_URI).header("Content-Type", "application/json").body(createCourier).post(END_URI_CREATE_COURIER);
    }

    @Step("Отправка запроса на авторизацию учетной записи курьера")
    public Response authorizationCourier(CreateCourier createCourier) {
        return given().baseUri(BASE_URI).header("Content-Type", "application/json").body(createCourier).post(END_URI_AUTHORIZATION_COURIER);
    }

    @Step("Отправка запроса на удаление учетной записи курьера")
    public void deleteCourierAccount(CreateCourier createCourier) {
        // отправляем запрос авторизации
        Response response = authorizationCourier(createCourier);
        // получаем id
        String courierId = Courier.getCourierId(response);
        // отправляем запрос на удаление
        given().baseUri(BASE_URI).delete(END_URI_DELETE_COURIER + courierId);
    }

    @Step("Отправка запроса на создание нового заказа")
    public Response createNewOrder(CreateOrder createOrder) {
        return given().baseUri(BASE_URI).header("Content-Type", "application/json").body(createOrder).post(END_URI_CREATE_ORDER);
    }

    @Step("Отправка запроса на отмену созданного заказа")
    public void cancelOrder(Response response) {
        GetTrackOrder getTrackOrder = response.as(GetTrackOrder.class);
        given().baseUri(BASE_URI).header("Content-Type", "application/json").body(getTrackOrder).post(END_URI_CANCEL_ORDER);
    }

    @Step("Отправка запроса на получение списка заказов")
    public Response getOrderList() {
        return given().baseUri(BASE_URI).get(END_URI_GET_ORDER_LIST);
    }
}