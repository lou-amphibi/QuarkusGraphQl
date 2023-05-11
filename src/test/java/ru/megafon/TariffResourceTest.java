package ru.megafon;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.Test;
import ru.megafon.dto.TariffDto;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;

@QuarkusTest
public class TariffResourceTest {

    @Test
    void findAllTest() {
        Response response = given().when().contentType(MediaType.APPLICATION_JSON)
                .body("{\"query\":\"{ findAll{ id,name}}\"}")
                .post("/graphql")
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .extract()
                .response();

        List<TariffDto> result = response.jsonPath().getList("data.findAll", TariffDto.class);
    }


}