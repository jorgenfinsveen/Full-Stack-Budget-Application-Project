package no.idata1002.group19;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


/**
 * JUnit test class for testing the authentication mechanism for
 * user-login with user credentials.
 */
@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class AuthenticationTest {
    
    @Autowired
    private MockMvc mvc;


    /**
     * Sending HTTP /POST request to the /login endpoint with user credentials
     * which are valid. Expected to return a response from server with status
     * code 200 OK.
     */
    @Test
    public void authentication_request_with_valid_credentials() throws Exception {
        this.mvc
            .perform(
                post("/login")
                    .content("{\"username\":\"user\",\"password\":\"user\"}")
                    .header(HttpHeaders.CONTENT_TYPE, "application/json")
                )
            .andDo(print()).andExpect(status().isOk());
    }


    /**
     * Sending HTTP /POST request to the /login endpoint with user credentials
     * which are invalid. Expected to return a response from server with status
     * code 401 UNAUTHORIZED.
     */
    @Test
    public void authentication_request_with_invalid_credentials() throws Exception {
        this.mvc
            .perform(
                post("/login")
                    .content("{\"username\":\"hello\",\"password\":\"world\"}")
                    .header(HttpHeaders.CONTENT_TYPE, "application/json")
                )
            .andDo(print()).andExpect(status().isUnauthorized());
    }
}
