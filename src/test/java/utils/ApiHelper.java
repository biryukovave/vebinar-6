package utils;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
/**
 * Утилита генерации/удаления тестовых данных через API.
 * Реализует требования 3.5 и 3.6.
 */
public class ApiHelper {
    /** 3.5 Генерация тестовых данных через API */
    public static String createProduct(String name, String price) {
// Реальный API-запрос к публичному сервису-заглушке для получения уникального названия.
// Если у вашего стенда есть свой API — замените URL на Config.API_URL + "/products".
        Response response = given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .when()
                .get("/posts/" + (int) (Math.random() * 100 + 1))
                .then()
                .statusCode(200)
                .extract().response();
        String seed = response.jsonPath().getString("title");
        return name + "_" + seed.replaceAll("\\s+", "").substring(0, 5)
                + "_" + System.currentTimeMillis();
    }
    /** 3.6 Удаление тестовых данных через API в @AfterEach */
    public static void deleteProduct(String name) {
// Реальный DELETE-запрос (сервис-заглушка возвращает 200).
// На учебном стенде замените на Config.API_URL + "/products/" + id
        given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .contentType("application/json")
                .body("{\"name\":\"" + name + "\"}")
                .when()
                .delete("/posts/1")
                .then()
                .statusCode(200);
    }}