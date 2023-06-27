package exercise;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import io.javalin.Javalin;
import io.ebean.DB;

import exercise.domain.User;
import exercise.domain.query.QUser;
import io.ebean.Database;

class AppTest {

    private static Javalin app;
    private static String baseUrl;

    // BEGIN
    @BeforeAll
    public static void beforeAll() {
        app = App.getApp();
        app.start(0);
        int port = app.port();
        baseUrl = "http://localhost:" + port;
    }

    @AfterAll
    public static void afterAll() {
        app.stop();
    }
    // END

    // Между тестами база данных очищается
    // Благодаря этому тесты не влияют друг на друга
    @BeforeEach
    void beforeEach() {
        Database db = DB.getDefault();
        db.truncate("users");
        User existingUser = new User("Wendell", "Legros", "a@a.com", "123456");
        existingUser.save();
    }

    @Test
    void testUsers() {

        // Выполняем GET запрос на адрес http://localhost:port/users
        HttpResponse<String> response = Unirest
            .get(baseUrl + "/users")
            .asString();
        // Получаем тело ответа
        String content = response.getBody();

        // Проверяем код ответа
        assertThat(response.getStatus()).isEqualTo(200);
        // Проверяем, что страница содержит определенный текст
        assertThat(response.getBody()).contains("Wendell Legros");
    }

    @Test
    void testNewUser() {

        HttpResponse<String> response = Unirest
            .get(baseUrl + "/users/new")
            .asString();

        assertThat(response.getStatus()).isEqualTo(200);
    }

    // BEGIN
    @Test
    void testAppWorkValid() {
        HttpResponse<String> response = Unirest
                .post(baseUrl + "/users/new")
                .field("first_name", "Alex")
                .field("last_name", "Koganov")
                .field("email", "Koganov@gmail.com")
                .field("password", "123456")
                .asString();

        assertThat(response.getStatus()).isEqualTo(302);

        User user = new QUser()
                .firstName.equalTo("Alex")
                .findOne();
        assertThat(user).isNotNull();

        assertThat(user.getLastName()).isEqualTo("Koganov");
        assertThat(user.getEmail()).isEqualTo("Koganov@gmail.com");
        assertThat(user.getPassword()).isEqualTo("123456");
    }

    @Test
    void testAppWorkNotValid() {
        HttpResponse<String> response = Unirest
                .post(baseUrl + "/users/new")
                .field("first_name", "Alex")
                .field("last_name", "")
                .field("email", "Koganov@gmail.com")
                .field("password", "123")
                .asString();

        assertThat(response.getStatus()).isEqualTo(422);

        User user = new QUser()
                .firstName.equalTo("Alex")
                .findOne();
        assertThat(user).isNull();

        String content = response.getBody();
        assertThat(content).contains("Фамилия не должна быть пустой");
        assertThat(content).contains("Пароль должен содержать не менее 4 символов");
    }
    // END
}
