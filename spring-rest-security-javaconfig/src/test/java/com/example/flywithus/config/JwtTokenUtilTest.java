package com.example.flywithus.config;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.assertj.core.util.DateUtil;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.context.WebApplicationContext;
import com.example.flywithus.config.UserDetailsDummy;

import com.examples.flywithus.config.JwtTokenUtil;

import io.jsonwebtoken.Clock;

public class JwtTokenUtilTest {

    private static final String TEST_USERNAME = "testUser";

    @Mock
    private Clock clockMock;

    @InjectMocks
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

        ReflectionTestUtils.setField(jwtTokenUtil, "secret", "omkar");
    }

    @Test
    public void testGenerateTokenGeneratesDifferentTokensForDifferentCreationDates() throws Exception {
        when(clockMock.now()).thenReturn(DateUtil.yesterday()).thenReturn(DateUtil.now());

        final String token = createToken();
        final String laterToken = createToken();

        assertThat(token).isNotEqualTo(laterToken);
    }
    
    @Test
    public void canValidateToken() throws Exception {
        when(clockMock.now())
            .thenReturn(DateUtil.now());
        UserDetails userDetails = mock(UserDetails.class);
        when(userDetails.getUsername()).thenReturn(TEST_USERNAME);

        String token = createToken();
        assertThat(jwtTokenUtil.validateToken(token, userDetails)).isTrue();
    }

    
    @Test
    public void getUsernameFromToken() throws Exception {
        when(clockMock.now()).thenReturn(DateUtil.now());

        final String token = createToken();

        assertThat(jwtTokenUtil.getUsernameFromToken(token)).isEqualTo(TEST_USERNAME);
    }
    
    @Test
    public void getExpirationDateFromToken() throws Exception {
        final Date now = DateUtil.now();
        when(clockMock.now()).thenReturn(now);
        final String token = createToken();

        final Date expirationDateFromToken = jwtTokenUtil.getExpirationDateFromToken(token);
        assertThat(DateUtil.timeDifference(expirationDateFromToken, now)).isCloseTo(JwtTokenUtil.JWT_TOKEN_VALIDITY*1000, within(1000L));
    }

    private String createToken() {
        return jwtTokenUtil.generateToken(new UserDetailsDummy(TEST_USERNAME));
    }

}
