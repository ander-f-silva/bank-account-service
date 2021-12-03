package br.com.dock.bank_account_service.account.controller;

import br.com.dock.bank_account_service.account.controller.cases.SearchBalance;
import br.com.dock.bank_account_service.account.repository.AccountEntity;
import br.com.dock.bank_account_service.account.repository.AccountRepository;
import br.com.dock.bank_account_service.person.repository.PersonEntity;
import br.com.dock.bank_account_service.person.repository.PersonRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@DisplayName("Integration test to resource /balances")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class BalancesControllerTest {
    private static final String HEAD_ACCEPT_VERSION = "Accept-version";
    private static final String HEAD_CONTENT_TYPE = "Content-type";

    private static final String PATH_ACCOUNTS_RESOURCE = "/balances?accountId=";
    private static final String VERSION_1 = "v1";

    static MockMvc mockMvc;

    @BeforeAll
    static void setUp(@Autowired WebApplicationContext webApplicationContext, @Autowired PersonRepository personRepository, @Autowired AccountRepository accountRepository) {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        var person = PersonEntity.builder()
                .name("André da Silva")
                .document("07584886027")
                .birthday(LocalDate.of(1990, 1, 1))
                .build();

        var personCreated = personRepository.save(person);

        var account = AccountEntity.builder()
                .idPerson(personCreated.getIdPerson())
                .withdrawalDayLimit(10000.0)
                .flagActive(true)
                .balance(1000.0)
                .createdAt(LocalDate.now())
                .accountType(1)
                .build();

        accountRepository.save(account);
    }

    @AfterAll
    static void tearDown(@Autowired PersonRepository personRepository, @Autowired AccountRepository accountRepository) {
        accountRepository.deleteAll();
        personRepository.deleteAll();
    }

    @DisplayName("Should get balance of account")
    @ParameterizedTest(name = "{index} {0}")
    @MethodSource("br.com.dock.bank_account_service.account.controller.cases.SearchBalance#parametersSearchBalance")
    void testShouldGetBalanceOfAccount(String title, SearchBalance.UserCase userCase) throws Exception {
        var mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .get(PATH_ACCOUNTS_RESOURCE + userCase.getParamAccountId())
                        .header(HEAD_ACCEPT_VERSION, VERSION_1)
                        .header(HEAD_CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        assertThat(mvcResult.getResponse().getStatus(), equalTo(userCase.getHttpStatus().value()));
        assertThat(mvcResult.getResponse().getContentAsString(), equalTo(userCase.getResponse()));
    }
}