package br.com.dock.bank_account_service.transaction.controller;

import br.com.dock.bank_account_service.transaction.controller.cases.ApplyDeposit;
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
class DepositsControllerTest {
    public static final String HEAD_ACCEPT_VERSION = "Accept-version";
    public static final String HEAD_CONTENT_TYPE = "Content-type";

    public static final String PATH_DEPOSITS_RESOURCE = "/deposits";
    public static final String FIRST_QUERY_STRING_ACCOUNT_ID = "?accountId=1";
    public static final String VERSION_1 = "v1";

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp(@Autowired WebApplicationContext webApplicationContext) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @DisplayName("Should apply an deposit to an account")
    @ParameterizedTest(name = "{index} {0}")
    @MethodSource("br.com.dock.bank_account_service.transaction.controller.cases.ApplyDeposit#parametersApplyDeposit")
    void testShouldCreateAnNewAccount(String title, ApplyDeposit.UserCase userCase) throws Exception {
        var mvcResult = this.mockMvc.perform(MockMvcRequestBuilders
                        .post(PATH_DEPOSITS_RESOURCE + FIRST_QUERY_STRING_ACCOUNT_ID)
                        .header(HEAD_ACCEPT_VERSION, VERSION_1)
                        .header(HEAD_CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .content(userCase.getRequest()))
                .andReturn();

        assertThat(mvcResult.getResponse().getStatus(), equalTo(userCase.getHttpStatus().value()));
        assertThat(mvcResult.getResponse().getContentAsString(), equalTo(userCase.getResponse()));
    }

}