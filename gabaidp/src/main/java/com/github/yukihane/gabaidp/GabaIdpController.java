package com.github.yukihane.gabaidp;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.ConcurrentReferenceHashMap;
import org.springframework.util.ConcurrentReferenceHashMap.ReferenceType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/gabaidp")
public class GabaIdpController {

    private static final ConcurrentReferenceHashMap<String, String> NONCES = new ConcurrentReferenceHashMap<>(200,
        ReferenceType.WEAK);
    private static final Date EXP;
    static {
        final Instant instant = LocalDateTime.of(2050, 12, 31, 23, 59).toInstant(ZoneOffset.UTC);
        EXP = Date.from(instant);
    }

    /**
     * <code>
     * http://localhost:8080/login/oauth2/code/myspring?state=-t1bljJx_gXUWyhOCPMBczvNQtO3aTZU-NDcTFamgQc%3D&session_state=f5c1d220-11ff-460e-bdd8-f98881621146&code=fd10234e-a9de-4223-85f2-c2145fa2a40d.f5c1d220-11ff-460e-bdd8-f98881621146.5860bae9-7c17-4ce8-b182-2e0b17bda1d9
     * </code>
     */
    @RequestMapping("/auth")
    public String auth(@RequestParam("state") final String state, @RequestParam("nonce") final String nonce) {
        final String code = UUID.randomUUID().toString();
        NONCES.put(code, nonce);
        return "redirect:http://localhost:8080/login/oauth2/code/myspring?code=" + code + "&state=" + state;
    }

    /**
     *
     * @see <a href=
     * "https://openid-foundation-japan.github.io/openid-connect-core-1_0.ja.html#TokenResponse">
     * 3.1.3.3. Successful Token Response</a>
     * @see <a href=
     * "https://openid-foundation-japan.github.io/openid-connect-core-1_0.ja.html#IDToken">
     * 2. ID Token</a>
     */
    @RequestMapping(value = "/token", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Object> token(@RequestParam("code") final String code) {
        final Algorithm alg = Algorithm.HMAC256("e3b8886b-5b6e-49a7-91c2-c28caadf0a2b");

        final String nonce = NONCES.remove(code);

        final String idToken = JWT.create()
            .withIssuer("https://gaba.example.com")
            .withSubject("dummy-subject-identifier")
            .withAudience("myclient")
            .withExpiresAt(EXP)
            .withIssuedAt(new Date())
            .withClaim("nonce", nonce)
            .withClaim("preferred_username", "dummy_user_name")
            .sign(alg);

        final Map<String, Object> ret = new HashMap<>();
        ret.put("access_token", "dummy_access_token");
        ret.put("token_type", "Bearer");
        ret.put("refresh_token", "dummy_refresh_token");
        ret.put("expires_in", 3600);
        ret.put("id_token", idToken);

        return ret;
    }

    @RequestMapping(value = "/userinfo", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Object> userinfo() {
        final Map<String, Object> ret = new HashMap<>();
        ret.put("fav-number", 8);
        ret.put("sub", "dummy-subject-identifier");
        ret.put("preferred_username", "dummy_user_name");
        return ret;
    }

    @RequestMapping("/certs")
    public String certs() {
        throw new UnsupportedOperationException();
    }
}
