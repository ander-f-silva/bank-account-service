package br.com.dock.bank_account_service.account.controller;

import br.com.dock.bank_account_service.account.controller.cases.BlockAccount;
import br.com.dock.bank_account_service.account.controller.cases.CreateNewAccount;
import br.com.dock.bank_account_service.account.controller.cases.FindAccountStatement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class AccountsControllerTest {
    public static final String HEAD_ACCEPT_VERSION = "Accept-version";
    public static final String HEAD_CONTENT_TYPE = "Content-type";

    public static final String PATH_ACCOUNTS_RESOURCE = "/accounts";
    public static final String VERSION_1 = "v1";
    public static final String PATH_BLOCKS = "/blocks";
    public static final String PATH_TRANSACTIONS = "/transactions";
    public static final Long ACCOUNT_ID = 1L;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp(@Autowired WebApplicationContext webApplicationContext) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @DisplayName("Should create an new account")
    @ParameterizedTest(name = "{index} {0}")
    @MethodSource("br.com.dock.bank_account_service.account.controller.cases.CreateNewAccount#parametersCreateNewAccount")
    void testShouldCreateAnNewAccount(String title, CreateNewAccount.UserCase userCase) throws Exception {
        var mvcResult = this.mockMvc.perform(MockMvcRequestBuilders
                        .post(PATH_ACCOUNTS_RESOURCE)
                        .header(HEAD_ACCEPT_VERSION, VERSION_1)
                        .header(HEAD_CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .content(userCase.getRequest()))
                .andReturn();

        assertThat(mvcResult.getResponse().getStatus(), equalTo(userCase.getHttpStatus().value()));
        assertThat(mvcResult.getResponse().getContentAsString(), equalTo(userCase.getResponse()));
    }

    @DisplayName("Should block an account")
    @ParameterizedTest(name = "{index} {0}")
    @MethodSource("br.com.dock.bank_account_service.account.controller.cases.BlockAccount#parametersBlockAccount")
    void testShouldBlockAnAccount(String title, BlockAccount.UserCase userCase) throws Exception {
        var mvcResult = this.mockMvc.perform(MockMvcRequestBuilders
                        .patch(PATH_ACCOUNTS_RESOURCE + "/" + ACCOUNT_ID + PATH_BLOCKS)
                        .header(HEAD_ACCEPT_VERSION, VERSION_1)
                        .header(HEAD_CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .content(userCase.getRequest()))
                .andReturn();

        assertThat(mvcResult.getResponse().getStatus(), equalTo(userCase.getHttpStatus().value()));
    }

    @DisplayName("Should find the account statement")
    @ParameterizedTest(name = "{index} {0}")
    @MethodSource("br.com.dock.bank_account_service.account.controller.cases.FindAccountStatement#parametersFindAccountStatement")
    void testShouldFindTheAccountStatement(String title, FindAccountStatement.UserCase userCase) throws Exception {
        var mvcResult = this.mockMvc.perform(MockMvcRequestBuilders
                        .get(PATH_ACCOUNTS_RESOURCE + "/" + ACCOUNT_ID + PATH_TRANSACTIONS)
                        .header(HEAD_ACCEPT_VERSION, VERSION_1)
                        .header(HEAD_CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        assertThat(mvcResult.getResponse().getStatus(), equalTo(userCase.getHttpStatus().value()));
        assertThat(mvcResult.getResponse().getContentAsString(), equalTo(userCase.getResponse()));
    }
}