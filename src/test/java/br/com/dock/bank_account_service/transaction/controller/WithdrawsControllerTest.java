package br.com.dock.bank_account_service.transaction.controller;

import br.com.dock.bank_account_service.transaction.controller.cases.ApplyWithDraw;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class WithdrawsControllerTest {
    public static final String HEAD_ACCEPT_VERSION = "Accept-version";
    public static final String HEAD_CONTENT_TYPE = "Content-type";

    public static final String PATH_WITHDRAWS_RESOURCE = "/withdraws";
    public static final String FIRST_QUERY_STRING_ACCOUNT_ID = "?accountId=1";
    public static final String VERSION_1 = "v1";

    private WebTestClient client;

    @BeforeEach
    public void setup(@Autowired WebApplicationContext wac) {
        this.client = MockMvcWebTestClient.bindToApplicationContext(wac).build();
    }

    @DisplayName("Should apply an withdraw to an account")
    @ParameterizedTest(name = "{index} {0}")
    @MethodSource("br.com.dock.bank_account_service.transaction.controller.cases.ApplyWithDraw#parametersApplyWithDraw")
    void testShouldCreateAnNewAccount(String title, ApplyWithDraw.UserCase userCase) {
        client.post().uri(PATH_WITHDRAWS_RESOURCE + FIRST_QUERY_STRING_ACCOUNT_ID)
                .header(HEAD_ACCEPT_VERSION, VERSION_1)
                .header(HEAD_CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .bodyValue(userCase.getRequest())
                .exchange()
                .expectStatus().isEqualTo(userCase.getHttpStatus())
                .expectBody()
                .json(userCase.getResponse());
    }

}