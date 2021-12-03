package br.com.dock.bank_account_service.account.controller;

import br.com.dock.bank_account_service.account.controller.cases.BlockAccount;
import br.com.dock.bank_account_service.account.controller.cases.CreateNewAccount;
import br.com.dock.bank_account_service.account.controller.cases.FindAccountStatement;
import br.com.dock.bank_account_service.account.repository.AccountEntity;
import br.com.dock.bank_account_service.account.repository.AccountRepository;
import br.com.dock.bank_account_service.person.repository.PersonEntity;
import br.com.dock.bank_account_service.person.repository.PersonRepository;
import br.com.dock.bank_account_service.transaction.repository.TransactionEntity;
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
@DisplayName("Integration test to resource /accounts")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class AccountsControllerTest {
    private static final String HEAD_ACCEPT_VERSION = "Accept-version";
    private static final String HEAD_CONTENT_TYPE = "Content-type";

    private static final String PATH_ACCOUNTS_RESOURCE = "/accounts";
    private static final String VERSION_1 = "v1";
    private static final String PATH_BLOCKS = "/blocks";
    private static final String PATH_TRANSACTIONS = "/transactions";

    static MockMvc mockMvc;

    @BeforeAll
    static void setUp(@Autowired WebApplicationContext webApplicationContext, @Autowired PersonRepository personRepository, @Autowired AccountRepository accountRepository,  @Autowired TransactionRepository transactionRepository) {
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
                .balance(100.0)
                .createdAt(LocalDate.now())
                .accountType(1)
                .build();

        accountRepository.save(account);

        var depositTransaction = TransactionEntity.builder()
                .idAccount(account.getIdAccount())
                .amount(100.0)
                .createdAt(LocalDate.of(2021, 12, 1))
                .build();

        transactionRepository.save(depositTransaction);

        var withdrawTransaction = TransactionEntity.builder()
                .idAccount(account.getIdAccount())
                .amount(-50.0)
                .createdAt(LocalDate.of(2021, 12, 1))
                .build();

        transactionRepository.save(withdrawTransaction);
    }

    @AfterAll
    static void tearDown(@Autowired PersonRepository personRepository, @Autowired AccountRepository accountRepository, @Autowired TransactionRepository transactionRepository) {
        transactionRepository.deleteAll();
        accountRepository.deleteAll();
        personRepository.deleteAll();
    }

    @DisplayName("Should create an new account")
    @ParameterizedTest(name = "{index} {0}")
    @MethodSource("br.com.dock.bank_account_service.account.controller.cases.CreateNewAccount#parametersCreateNewAccount")
    void testShouldCreateAnNewAccount(String title, CreateNewAccount.UserCase userCase) throws Exception {
        var mvcResult = mockMvc.perform(MockMvcRequestBuilders
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
        var mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .patch(PATH_ACCOUNTS_RESOURCE + "/" + userCase.getParamAccountId() + PATH_BLOCKS)
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
        var mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .get(PATH_ACCOUNTS_RESOURCE + "/" + userCase.getParamAccountId() + PATH_TRANSACTIONS)
                        .header(HEAD_ACCEPT_VERSION, VERSION_1)
                        .header(HEAD_CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        assertThat(mvcResult.getResponse().getStatus(), equalTo(userCase.getHttpStatus().value()));
        assertThat(mvcResult.getResponse().getContentAsString(), equalTo(userCase.getResponse()));
    }
}