package br.com.dock.bank_account_service.transaction;

import br.com.dock.bank_account_service.transaction.cases.ApplyDeposit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DepositsControllerTest {
    public static final String HEAD_ACCEPT_VERSION = "Accept-version";
    public static final String HEAD_CONTENT_TYPE = "Content-type";

    public static final String PATH_DEPOSITS_RESOURCE = "/deposits";
    public static final String VERSION_1 = "v1";

    @Autowired
    WebTestClient client;

    @DisplayName("Should apply an deposit to an account")
    @ParameterizedTest(name = "{index} {0}")
    @MethodSource("br.com.dock.bank_account_service.transaction.cases.ApplyDeposit#parametersApplyDeposit")
    void testShouldCreateAnNewAccount(String title, ApplyDeposit.UserCase userCase) {
        client.post().uri(PATH_DEPOSITS_RESOURCE)
                .header(HEAD_ACCEPT_VERSION, VERSION_1)
                .header(HEAD_CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .bodyValue(userCase.getRequest())
                .exchange()
                .expectStatus().isEqualTo(userCase.getHttpStatus())
                .expectBody()
                .json(userCase.getResponse());
    }

}