package springangular.mvc.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import springangular.core.model.entity.Account;
import springangular.core.model.entity.Blog;
import springangular.core.services.AccountService;
import springangular.core.services.exceptions.AccountDoesnotExistException;
import springangular.core.services.util.AccountList;
import springangular.core.services.util.BlogList;
import springangular.rest.mvc.controller.AccountController;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.options;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by E5520 on 26.03.2017.
 */
public class AccountControllerTest {

    @InjectMocks
    private AccountController accountController;

    @Mock
    private AccountService accountService;

    private MockMvc mockMvc;

    private ArgumentCaptor<Account> accountArgumentCaptor;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();

        //captor
        accountArgumentCaptor = ArgumentCaptor.forClass(Account.class);

    }

    @Test
    public void getExistingAccount() throws Exception {
        Account account = new Account();
        account.setId(1L);
        account.setUsername("test");
        account.setPassword("test");

        when(accountService.findAccount(1L)).thenReturn(account);

        mockMvc.perform(get("/rest/accounts/1"))
                .andDo(print())
//                .andExpect(jsonPath("$.password", is(nullValue())))
                .andExpect(jsonPath("$.username", is(account.getUsername())))
                .andExpect(status().isOk());
    }

    @Test
    public void findAllBlogsForNonExistingAccount() throws Exception {
        List<Blog> list = new ArrayList<Blog>();

        Blog blogA = new Blog();
        blogA.setId(1L);
        blogA.setTitle("Title A");
        list.add(blogA);

        Blog blogB = new Blog();
        blogB.setId(2L);
        blogB.setTitle("Title B");
        list.add(blogB);

        BlogList blogList = new BlogList(list);

        when(accountService.findBlogsByAccount(1L)).thenThrow(new AccountDoesnotExistException());

        mockMvc.perform(get("/rest/accounts/1/blogs"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void findAllBlogsForAccount() throws Exception {
        List<Blog> list = new ArrayList<Blog>();

        Blog blogA = new Blog();
        blogA.setId(1L);
        blogA.setTitle("Title A");
        list.add(blogA);

        Blog blogB = new Blog();
        blogB.setId(2L);
        blogB.setTitle("Title B");
        list.add(blogB);

        BlogList blogList = new BlogList(list);

        when(accountService.findBlogsByAccount(1L)).thenReturn(blogList);

        mockMvc.perform(get("/rest/accounts/1/blogs"))
                .andDo(print())
                .andExpect(jsonPath("$.blogs[*].title",
                        hasItems(endsWith("Title A"), endsWith("Title B"))))
                .andExpect(status().isOk());
    }

    @Test
    public void createAccountNonExistingUsername() throws Exception {
        Account createdAccount = new Account();
        createdAccount.setId(1L);
        createdAccount.setPassword("test");
        createdAccount.setUsername("test");

        when(accountService.createAccount(any(Account.class))).thenReturn(createdAccount);

        mockMvc.perform(post("/rest/accounts")
                .content("{\"username\":\"test\",\"password\":\"test\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(header().string("Location", endsWith("/rest/accounts/1")))
                .andExpect(jsonPath("$.username", is(createdAccount.getUsername())))
                .andExpect(status().isCreated());

        verify(accountService).createAccount(accountArgumentCaptor.capture());

        String password = accountArgumentCaptor.getValue().getPassword();
        assertEquals("test", password);
    }

    @Test
    public void findAccountsByName() throws Exception {
        List<Account> accounts = new ArrayList<Account>();

        Account accountA = new Account();
        accountA.setId(1L);
        accountA.setPassword("accountA");
        accountA.setUsername("accountA");
        accounts.add(accountA);

        Account accountB = new Account();
        accountB.setId(2L);
        accountB.setPassword("accountB");
        accountB.setUsername("accountB");
        accounts.add(accountB);

        AccountList accountList = new AccountList(accounts);

        when(accountService.findAllAccounts()).thenReturn(accountList);
        when(accountService.findByAccountName(any(String.class))).thenReturn(accountA);

        mockMvc.perform(get("/rest/accounts").param("name","accountA"))
                .andDo(print())
                .andExpect(jsonPath("$.accounts[*].username",
                        everyItem(endsWith("accountA"))))
                .andExpect(status().isOk());
    }

}
