package exercise;

import com.fasterxml.jackson.databind.ObjectMapper;
import exercise.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.http.MediaType;

import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.junit.jupiter.Container;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.List;
import java.util.Map;

@SpringBootTest
@AutoConfigureMockMvc

// BEGIN
@Testcontainers
@Transactional
// END
public class AppTest {

    @Autowired
    private MockMvc mockMvc;

    // BEGIN
    @Container
    private static PostgreSQLContainer<?> database = new PostgreSQLContainer<>("postgres")
            .withDatabaseName("people")
            .withUsername("sa")
            .withPassword("sa")
            .withInitScript("init.sql");
    @DynamicPropertySource
    public static void properties(DynamicPropertyRegistry registry) {
        // Устанавливаем URL базы данных
        registry.add("spring.datasource.url", database::getJdbcUrl);
        // Имя пользователя и пароль для подключения
        registry.add("spring.datasource.username", database::getUsername);
        registry.add("spring.datasource.password", database::getPassword);
        // Эти значения приложение будет использовать при подключении к базе данных
    }
    // END

    @Test
    void testCreatePerson() throws Exception {
        MockHttpServletResponse responsePost = mockMvc
            .perform(post("/people")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"firstName\": \"Jackson\", \"lastName\": \"Bind\"}")
            )
            .andReturn()
            .getResponse();

        assertThat(responsePost.getStatus()).isEqualTo(200);

        MockHttpServletResponse response = mockMvc
            .perform(get("/people"))
            .andReturn()
            .getResponse();

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getContentType()).isEqualTo(MediaType.APPLICATION_JSON.toString());
        assertThat(response.getContentAsString()).contains("Jackson", "Bind");
    }

    @Test
    void testShowPerson() throws Exception {
        MockHttpServletResponse response = mockMvc
                .perform(get("/people")
                        .contentType(MediaType.APPLICATION_JSON.toString())
                )
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getContentType()).isEqualTo(MediaType.APPLICATION_JSON.toString());
        assertThat(response.getContentAsString()).contains("John", "Smith",
                "Jack", "Doe", "Jassica", "Simpson", "Robert", "Lock");
    }

    @Test
    void testUpdatePerson() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        MockHttpServletResponse responsePatch = mockMvc
                .perform(get("/people"))
                .andReturn()
                .getResponse();
        var body = responsePatch.getContentAsString();

        List<Map> users = mapper.readValue(body, List.class);
        var existingUser = users.stream()
                .filter(user -> user.get("lastName").equals("Smith"))
                .findAny()
                .get();
        var existingUserId = existingUser.get("id");

        mockMvc.perform(
                patch("/people/{id}", existingUserId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\": \"Jackson\", \"lastName\": \"Bind\"}")
                )
                .andReturn()
                .getResponse();

        assertThat(responsePatch.getStatus()).isEqualTo(200);

        MockHttpServletResponse response = mockMvc
                .perform(get("/people")
                        .contentType(MediaType.APPLICATION_JSON.toString())
                )
                .andReturn()
                .getResponse();

        assertThat(response.getContentType()).isEqualTo(MediaType.APPLICATION_JSON.toString());
        assertThat(response.getContentAsString()).contains("Jackson", "Bind");
        assertThat(response.getContentAsString()).doesNotContain("John", "Smith");
    }

    @Test
    void testDeletePerson() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        MockHttpServletResponse responseDelete = mockMvc
                .perform(get("/people"))
                .andReturn()
                .getResponse();
        var body = responseDelete.getContentAsString();

        List<Map> users = mapper.readValue(body, List.class);
        var existingUser = users.stream()
                .filter(user -> user.get("lastName").equals("Smith"))
                .findAny()
                .get();
        var existingUserId = existingUser.get("id");

        mockMvc.perform(delete("/people/{id}", existingUserId)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andReturn()
                .getResponse();

        assertThat(responseDelete.getStatus()).isEqualTo(200);

        MockHttpServletResponse response = mockMvc
                .perform(get("/people")
                        .contentType(MediaType.APPLICATION_JSON.toString())
                )
                .andReturn()
                .getResponse();

        assertThat(response.getContentType()).isEqualTo(MediaType.APPLICATION_JSON.toString());
        assertThat(response.getContentAsString()).doesNotContain("John", "Smith");
    }
}
