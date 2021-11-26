package br.com.dock.bank_account_service.account;

import br.com.dock.bank_account_service.account.cases.BlockAccount;
import br.com.dock.bank_account_service.account.cases.CreateNewAccount;
import br.com.dock.bank_account_service.account.cases.FindAccountStatement;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class AccountsControllerTest {
    public static final String HEAD_ACCEPT_VERSION = "Accept-version";
    public static final String HEAD_CONTENT_TYPE = "Content-type";

    public static final String PATH_ACCOUNTS_RESOURCE = "/accounts";
    public static final String VERSION_1 = "v1";
    public static final String PATH_BLOCKS = "/blocks";
    public static final Long ACCOUNT_ID = 1L;

    @Autowired
    WebTestClient client;

    @DisplayName("Should create an new account")
    @ParameterizedTest(name = "{index} {0}")
    @MethodSource("br.com.dock.bank_account_service.account.cases.CreateNewAccount#parametersCreateNewAccount")
    void testShouldCreateAnNewAccount(String title, CreateNewAccount.UserCase userCase) {
        client.post().uri(PATH_ACCOUNTS_RESOURCE)
                .header(HEAD_ACCEPT_VERSION, VERSION_1)
                .header(HEAD_CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .bodyValue(userCase.getRequest())
                .exchange()
                .expectStatus().isEqualTo(userCase.getHttpStatus())
                .expectBody()
                .json(userCase.getResponse());
    }

    @DisplayName("Should block an account")
    @ParameterizedTest(name = "{index} {0}")
    @MethodSource("br.com.dock.bank_account_service.account.cases.BlockAccount#parametersBlockAccount")
    void testShouldBlockAnAccount(String title, BlockAccount.UserCase userCase) {
        client.patch().uri(PATH_ACCOUNTS_RESOURCE + "/" + ACCOUNT_ID + PATH_BLOCKS)
                .header(HEAD_ACCEPT_VERSION, VERSION_1)
                .header(HEAD_CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .bodyValue(userCase.getRequest())
                .exchange()
                .expectStatus().isEqualTo(userCase.getHttpStatus());
    }

    @DisplayName("Should find the account statement")
    @ParameterizedTest(name = "{index} {0}")
    @MethodSource("br.com.dock.bank_account_service.account.cases.FindAccountStatement#parametersFindAccountStatement")
    void testShouldFindTheAccountStatement(String title, FindAccountStatement.UserCase userCase) {
        client.get().uri(PATH_ACCOUNTS_RESOURCE + "/" + ACCOUNT_ID)
                .header(HEAD_ACCEPT_VERSION, VERSION_1)
                .header(HEAD_CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus().isEqualTo(userCase.getHttpStatus())
                .expectBody()
                .json(userCase.getResponse());
    }
}