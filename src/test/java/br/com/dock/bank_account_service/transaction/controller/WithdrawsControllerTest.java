package br.com.dock.bank_account_service.transaction.controller;

import br.com.dock.bank_account_service.account.repository.AccountEntity;
import br.com.dock.bank_account_service.account.repository.AccountRepository;
import br.com.dock.bank_account_service.person.repository.PersonEntity;
import br.com.dock.bank_account_service.person.repository.PersonRepository;
import br.com.dock.bank_account_service.transaction.controller.cases.ApplyWithDraw;
import br.com.dock.bank_account_service.transaction.repository.TransactionRepository;
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
@DisplayName("Integration test to resource /withdraws")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class WithdrawsControllerTest {
    public static final String HEAD_ACCEPT_VERSION = "Accept-version";
    public static final String HEAD_CONTENT_TYPE = "Content-type";

    public static final String PATH_WITHDRAWS_RESOURCE = "/withdraws?accountId=";
    public static final String VERSION_1 = "v1";

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
                .withdrawalDayLimit(100.00)
                .flagActive(false)
                .balance(1000.0)
                .createdAt(LocalDate.now())
                .accountType(1)
                .build();

        accountRepository.save(account);
    }

    @AfterAll
    static void tearDown(@Autowired PersonRepository personRepository, @Autowired AccountRepository accountRepository, @Autowired TransactionRepository transactionRepository) {
        transactionRepository.deleteAll();
        accountRepository.deleteAll();
        personRepository.deleteAll();
    }

    @DisplayName("Should apply an withdraw to an account")
    @ParameterizedTest(name = "{index} {0}")
    @MethodSource("br.com.dock.bank_account_service.transaction.controller.cases.ApplyWithDraw#parametersApplyWithDraw")
    void testShouldCreateAnNewAccount(String title, ApplyWithDraw.UserCase userCase) throws Exception {
        var mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .post(PATH_WITHDRAWS_RESOURCE + userCase.getQueryAccountId())
                        .header(HEAD_ACCEPT_VERSION, VERSION_1)
                        .header(HEAD_CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .content(userCase.getRequest()))
                .andReturn();

        assertThat(mvcResult.getResponse().getStatus(), equalTo(userCase.getHttpStatus().value()));
        assertThat(mvcResult.getResponse().getContentAsString(), equalTo(userCase.getResponse()));
    }
}