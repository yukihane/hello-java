package com.github.yukihane.samplelinewebapp;

import java.net.URI;
import org.springframework.http.RequestEntity;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequestEntityConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

// OIDCのuserinfo_endpointに相当するのは /verify か？
// https://developers.line.biz/ja/reference/line-login/#verify-id-token
public class LineUserRequestEntityConverter extends OAuth2UserRequestEntityConverter {

    @Override
    public RequestEntity<?> convert(final OAuth2UserRequest userRequest) {
        final RequestEntity<?> base = super.convert(userRequest);

        final String token = (String) userRequest.getAdditionalParameters().get("id_token");
        final ClientRegistration clientRegistration = userRequest.getClientRegistration();
        final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("id_token", token);
        params.add("client_id", userRequest.getClientRegistration().getClientId());

        final URI uri = UriComponentsBuilder
            .fromUriString(clientRegistration.getProviderDetails().getUserInfoEndpoint().getUri())
            .replaceQueryParams(params)
            .build().toUri();

        return new RequestEntity<>(base.getBody(), base.getHeaders(), base.getMethod(), uri);
    }

}
