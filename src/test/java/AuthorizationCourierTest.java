import actions.Courier;
import actions.GeneralActions;
import client.ClientForTest;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import model.CreateCourier;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AuthorizationCourierTest {

    private ClientForTest clientForTest; // создаем клиент

    CreateCourier createCourier = new CreateCourier("FastCourier", "qwe123", "Ivan"); // создаем объект - курьера для создания учетной записи перед каждым тестом

    @Before
    public void setUp() {
        clientForTest = new ClientForTest(); // инициализируем клиент
        clientForTest.createNewCourier(createCourier); // отправка запроса на создание курьера
    }

    @Test
    @DisplayName("Успешная авторизация пользователя")
    public void successfulAuthorization() {
        CreateCourier courierForAuthorization = new CreateCourier("FastCourier", "qwe123", "Ivan"); // создаем объект для авторизации - курьера с валидными данными авторизации
        Response response = clientForTest.authorizationCourier(courierForAuthorization); // отправляем запрос на авторизацию, ответ сохраняем
        GeneralActions.printResponseBody(response); // выводим ответ в консоль
        GeneralActions.checkResponseCode(response, 200); // проверяем код ответа
        Courier.checkReturnId(response); // проверяем, что успешный запрос авторизации возвращает id
    }

    @Test
    @DisplayName("Ошибка авторизации, если неправильно введен логин")
    public void errorAuthorizationWithIncorrectUsername(){
        CreateCourier courierForAuthorization = new CreateCourier("Fastcourier", "qwe123", "Ivan"); // создаем объект для авторизации - курьера с неправильным логином
        Response response = clientForTest.authorizationCourier(courierForAuthorization); // отправляем запрос на авторизацию, ответ сохраняем
        GeneralActions.printResponseBody(response); // выводим ответ в консоль
        GeneralActions.checkResponseCode(response, 404); // проверяем код ответа
        GeneralActions.checkMessage(response, "Учетная запись не найдена"); // проверяем сообщение в теле ответа
    }

    @Test
    @DisplayName("Ошибка авторизации, если неправильно введен пароль")
    public void errorAuthorizationWithIncorrectPassword(){
        CreateCourier courierForAuthorization = new CreateCourier("FastCourier", "qwe1233", "Ivan"); // создаем объект для авторизации - курьера с неправильным логином
        Response response = clientForTest.authorizationCourier(courierForAuthorization); // отправляем запрос на авторизацию, ответ сохраняем
        GeneralActions.printResponseBody(response); // выводим ответ в консоль
        GeneralActions.checkResponseCode(response, 404); // проверяем код ответа
        GeneralActions.checkMessage(response, "Учетная запись не найдена"); // проверяем сообщение в теле ответа
    }

    @Test
    @DisplayName("Ошибка авторизации, если не введен логин")
    public void errorAuthorizationWithEmptyUsernameField(){
        CreateCourier courierForAuthorization = new CreateCourier("", "qwe123", "Ivan"); // создаем объект для авторизации - курьера с неправильным логином
        Response response = clientForTest.authorizationCourier(courierForAuthorization); // отправляем запрос на авторизацию, ответ сохраняем
        GeneralActions.printResponseBody(response); // выводим ответ в консоль
        GeneralActions.checkResponseCode(response, 400); // проверяем код ответа
        GeneralActions.checkMessage(response, "Недостаточно данных для входа"); // проверяем сообщение в теле ответа
    }

    @Test
    @DisplayName("Ошибка авторизации, если не введен пароль")
    public void errorAuthorizationWithEmptyPasswordField(){
        CreateCourier courierForAuthorization = new CreateCourier("FastCourier", "", "Ivan"); // создаем объект для авторизации - курьера с неправильным логином
        Response response = clientForTest.authorizationCourier(courierForAuthorization); // отправляем запрос на авторизацию, ответ сохраняем
        GeneralActions.printResponseBody(response); // выводим ответ в консоль
        GeneralActions.checkResponseCode(response, 400); // проверяем код ответа
        GeneralActions.checkMessage(response, "Недостаточно данных для входа");  // проверяем сообщение в теле ответа
    }

    @Test
    @DisplayName("Ошибка авторизации, при отсутствующем поле login")
    public void errorAuthorizationWithMissingUsernameField(){
        CreateCourier courierForAuthorization = new CreateCourier(null, "qwe123", "Ivan"); // создаем объект для авторизации - курьера с неправильным логином
        Response response = clientForTest.authorizationCourier(courierForAuthorization); // отправляем запрос на авторизацию, ответ сохраняем
        GeneralActions.printResponseBody(response); // выводим ответ в консоль
        GeneralActions.checkResponseCode(response, 400); // проверяем код ответа
        GeneralActions.checkMessage(response, "Недостаточно данных для входа"); // проверяем сообщение в теле ответа
    }

    @Test
    @DisplayName("Ошибка авторизации, при отсутствующем поле password")
    public void errorAuthorizationWithMissingPasswordField(){
        CreateCourier courierForAuthorization = new CreateCourier("FastCourier", null, "Ivan"); // создаем объект для авторизации - курьера с неправильным логином
        Response response = clientForTest.authorizationCourier(courierForAuthorization); // отправляем запрос на авторизацию, ответ сохраняем
        GeneralActions.printResponseBody(response); // выводим ответ в консоль
        GeneralActions.checkResponseCode(response, 400); // проверяем код ответа
        GeneralActions.checkMessage(response, "Недостаточно данных для входа"); // проверяем сообщение в теле ответа
    }

    @Test
    @DisplayName("Ошибка авторизации если авторизоваться под несуществующим пользователем")
    public void errorAuthorizationNonExistentUser() {
        clientForTest.deleteCourierAccount(createCourier); // запрос на удаление курьера из предусловия
        Response response = clientForTest.authorizationCourier(createCourier); // отправляем запрос на авторизацию только что удаленного курьера (из предусловия), ответ сохраняем
        GeneralActions.printResponseBody(response); // выводим в консоль
        GeneralActions.checkResponseCode(response, 404); // проверяем код ответа
        GeneralActions.checkMessage(response, "Учетная запись не найдена"); // проверяем сообщение в теле ответа
    }

    @After
    public void deleteAccount() {
        clientForTest.deleteCourierAccount(createCourier); // отправка запроса на удаление курьера
    }
}
