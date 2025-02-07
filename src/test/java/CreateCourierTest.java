import actions.GeneralActions;
import actions.Courier;
import client.ClientForTest;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import model.CreateCourier;
import org.junit.Before;
import org.junit.Test;

public class CreateCourierTest {

    private ClientForTest clientForTest; // создаем клиент

    @Before
    public void setUp() {
        clientForTest = new ClientForTest(); // инициализируем клиент
    }

    @Test
    @DisplayName("Успешное создание курьера")
    public void successfulCreationCourier() {
        CreateCourier createCourier = new CreateCourier("FastCourier", "qwe123", "Ivan"); // создаем объект с тестовыми данными
        Response response = clientForTest.createNewCourier(createCourier); // отправляем запрос на создание нового курьера, результат сохраняем
        GeneralActions.printResponseBody(response); // выводим в консоль ответ
        GeneralActions.checkResponseCode(response, 201); // проверяем код ответа
        Courier.checkResponseBodyByCorrectCreatingCourier(response, true); // проверяем тело ответа
        // удаление созданного курьера
        if(Courier.checkCreatedCourierStatusCode(response)==201) {
            clientForTest.deleteCourierAccount(createCourier);
        }
    }

    @Test
    @DisplayName("Нельзя создать двух одинаковых курьеров")
    public void errorCreateTwoIdenticalCouriers() {
        CreateCourier createCourier = new CreateCourier("FastCourier", "qwe123", "Ivan"); // создаем объект с тестовыми данными
        Response responseCourierNumberOne = clientForTest.createNewCourier(createCourier); // запрос на создание курьера №1, результат сохраняем
        Response responseCourierNumberTwo = clientForTest.createNewCourier(createCourier); // запрос на создание курьера №2, результат сохраняем
        // удаление созданного курьера №1
        if(Courier.checkCreatedCourierStatusCode(responseCourierNumberOne)==201) {
            clientForTest.deleteCourierAccount(createCourier);
        }
        // удаление созданного курьера №2 (если по каким-либо причинам он все-же был создан)
        if(Courier.checkCreatedCourierStatusCode(responseCourierNumberTwo)==201) {
            clientForTest.deleteCourierAccount(createCourier);
        }
        GeneralActions.printResponseBody(responseCourierNumberTwo); // выводим в консоль ответ на запрос №2
        GeneralActions.checkResponseCode(responseCourierNumberTwo, 409); // проверяем код ответа
        GeneralActions.checkMessage(responseCourierNumberTwo, "Этот логин уже используется"); // проверяем сообщение об ошибке
    }

    @Test
    @DisplayName("Нельзя создать курьера при незаполненом поле login")
    public void errorCreateCourierWithEmptyLoginField() {
        CreateCourier createCourier = new CreateCourier("", "qwe123", "Ivan"); // создаем объект с тестовыми данными
        Response response = clientForTest.createNewCourier(createCourier); // отправляем запрос на создание нового курьера, результат сохраняем
        // удаление курьера, если по каким-либо причинам он все-же был создан
        if(Courier.checkCreatedCourierStatusCode(response)==201) {
            clientForTest.deleteCourierAccount(createCourier);
        }
        GeneralActions.printResponseBody(response); // выводим в консоль ответ
        GeneralActions.checkResponseCode(response, 400); // проверяем код ответа
        GeneralActions.checkMessage(response, "Недостаточно данных для создания учетной записи"); // проверяем тело ответа
    }

    @Test
    @DisplayName("Нельзя создать курьера при незаполненом поле password")
    public void errorCreateCourierWithEmptyPasswordField() {
        CreateCourier createCourier = new CreateCourier("FastCourier", "", "Ivan"); // создаем объект с тестовыми данными
        Response response = clientForTest.createNewCourier(createCourier); // отправляем запрос на создание нового курьера, результат сохраняем
        // удаление курьера, если по каким-либо причинам он все-же был создан
        if(Courier.checkCreatedCourierStatusCode(response)==201) {
            clientForTest.deleteCourierAccount(createCourier);
        }
        GeneralActions.printResponseBody(response); // выводим в консоль ответ
        GeneralActions.checkResponseCode(response, 400); // проверяем код ответа
        GeneralActions.checkMessage(response, "Недостаточно данных для создания учетной записи"); // проверяем тело ответа
    }

    @Test
    @DisplayName("Нельзя создать курьера при отсутствующем поле login")
    public void errorCreateCourierWithMissingLoginField() {
        CreateCourier createCourier = new CreateCourier(null, "qwe123", "Ivan"); // создаем объект с тестовыми данными
        Response response = clientForTest.createNewCourier(createCourier); // отправляем запрос на создание нового курьера, результат сохраняем
        // удаление курьера, если по каким-либо причинам он все-же был создан
        if(Courier.checkCreatedCourierStatusCode(response)==201) {
            clientForTest.deleteCourierAccount(createCourier);
        }
        GeneralActions.printResponseBody(response); // выводим в консоль ответ
        GeneralActions.checkResponseCode(response, 400); // проверяем код ответа
        GeneralActions.checkMessage(response, "Недостаточно данных для создания учетной записи"); // проверяем тело ответа
    }

    @Test
    @DisplayName("Нельзя создать курьера при отсутствующем поле password")
    public void errorCreateCourierWithMissingPasswordField() {
        CreateCourier createCourier = new CreateCourier("FastCourier", null, "Ivan"); // создаем объект с тестовыми данными
        Response response = clientForTest.createNewCourier(createCourier); // отправляем запрос на создание нового курьера, результат сохраняем
        if(Courier.checkCreatedCourierStatusCode(response)==201) {
            clientForTest.deleteCourierAccount(createCourier);
        }
        GeneralActions.printResponseBody(response); // выводим в консоль ответ
        GeneralActions.checkResponseCode(response, 400); // проверяем код ответа
        GeneralActions.checkMessage(response, "Недостаточно данных для создания учетной записи"); // проверяем тело ответа
    }

    @Test
    @DisplayName("Нельзя создать курьера при незаполненом поле firstName")
    public void errorCreateCourierWithEmptyFirstNameField() {
        CreateCourier createCourier = new CreateCourier("FastCourier", "qwe123", ""); // создаем объект с тестовыми данными
        Response response = clientForTest.createNewCourier(createCourier); // отправляем запрос на создание нового курьера, результат сохраняем
        // удаление курьера, если по каким-либо причинам он все-же был создан
        if(Courier.checkCreatedCourierStatusCode(response)==201) {
            clientForTest.deleteCourierAccount(createCourier);
        }
        GeneralActions.printResponseBody(response); // выводим в консоль ответ
        GeneralActions.checkResponseCode(response, 400); // проверяем код ответа
        GeneralActions.checkMessage(response, "Недостаточно данных для создания учетной записи"); // проверяем тело ответа
    }

    @Test
    @DisplayName("Нельзя создать курьера при отсутствующем поле firstName")
    public void errorCreateCourierWithMissingFirstNameField() {
        CreateCourier createCourier = new CreateCourier("FastCourier", "qwe123", null); // создаем объект с тестовыми данными
        Response response = clientForTest.createNewCourier(createCourier); // отправляем запрос на создание нового курьера, результат сохраняем
        // удаление курьера, если по каким-либо причинам он все-же был создан
        if(Courier.checkCreatedCourierStatusCode(response)==201) {
            clientForTest.deleteCourierAccount(createCourier);
        }
        GeneralActions.printResponseBody(response); // выводим в консоль ответ
        GeneralActions.checkResponseCode(response, 400); // проверяем код ответа
        GeneralActions.checkMessage(response, "Недостаточно данных для создания учетной записи"); // проверяем тело ответа
    }
}