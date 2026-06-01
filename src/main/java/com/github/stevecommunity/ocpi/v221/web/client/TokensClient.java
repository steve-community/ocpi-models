package com.github.stevecommunity.ocpi.v221.web.client;

import com.github.stevecommunity.ocpi.v221.model.tokens.AuthorizationInfo;
import com.github.stevecommunity.ocpi.v221.model.tokens.Token;
import com.github.stevecommunity.ocpi.v221.model.tokens.TokenPatch;
import com.github.stevecommunity.ocpi.v221.model.tokens.types.LocationReferences;
import com.github.stevecommunity.ocpi.v221.model.tokens.types.TokenType;
import com.github.stevecommunity.ocpi.v221.web.OcpiRequestHeaders;
import com.github.stevecommunity.ocpi.v221.web.OcpiRequestParameters;
import com.github.stevecommunity.ocpi.v221.web.OcpiResponse;
import com.github.stevecommunity.ocpi.v221.web.OcpiResponseVoid;
import com.github.stevecommunity.ocpi.v221.web.api.TokensReceiverApi;
import com.github.stevecommunity.ocpi.v221.web.api.TokensSenderApi;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;

public class TokensClient extends AbstractClient implements TokensReceiverApi, TokensSenderApi {

    private static final ParameterizedTypeReference<OcpiResponse<Token>> TOKEN_RESPONSE = new ParameterizedTypeReference<>() {
    };
    private static final ParameterizedTypeReference<OcpiResponse<List<Token>>> TOKENS_RESPONSE = new ParameterizedTypeReference<>() {
    };
    private static final ParameterizedTypeReference<OcpiResponse<AuthorizationInfo>> AUTHORIZATION_INFO_RESPONSE = new ParameterizedTypeReference<>() {
    };

    private final RestTemplate restTemplate;

    public TokensClient(RestTemplate restTemplate, String tokensEndpointRoot, String authorizationToken) {
        super(tokensEndpointRoot, authorizationToken);
        this.restTemplate = Objects.requireNonNull(restTemplate, "restTemplate must not be null");
    }

    @Override
    public ResponseEntity<OcpiResponse<List<Token>>> getTokens(OcpiRequestHeaders headers, OcpiRequestParameters params) {
        return restTemplate.exchange(
            uri(params),
            HttpMethod.GET,
            new HttpEntity<>(httpHeaders(headers)),
            TOKENS_RESPONSE
        );
    }

    @Override
    public ResponseEntity<OcpiResponse<Token>> getToken(OcpiRequestHeaders headers,
                                                        String countryCode,
                                                        String partyId,
                                                        String tokenUid,
                                                        TokenType type) {
        return restTemplate.exchange(
            tokenUri(countryCode, partyId, tokenUid, type),
            HttpMethod.GET,
            new HttpEntity<>(httpHeaders(headers)),
            TOKEN_RESPONSE
        );
    }

    @Override
    public ResponseEntity<OcpiResponseVoid> putToken(OcpiRequestHeaders headers,
                                                     String countryCode,
                                                     String partyId,
                                                     String tokenUid,
                                                     TokenType type,
                                                     Token token) {
        return restTemplate.exchange(
            tokenUri(countryCode, partyId, tokenUid, type),
            HttpMethod.PUT,
            new HttpEntity<>(token, httpHeaders(headers)),
            OcpiResponseVoid.class
        );
    }

    @Override
    public ResponseEntity<OcpiResponseVoid> patchToken(OcpiRequestHeaders headers,
                                                       String countryCode,
                                                       String partyId,
                                                       String tokenUid,
                                                       TokenType type,
                                                       TokenPatch token) {
        return restTemplate.exchange(
            tokenUri(countryCode, partyId, tokenUid, type),
            HttpMethod.PATCH,
            new HttpEntity<>(token, httpHeaders(headers)),
            OcpiResponseVoid.class
        );
    }

    @Override
    public ResponseEntity<OcpiResponse<AuthorizationInfo>> authorizeToken(OcpiRequestHeaders headers,
                                                                          String tokenUid,
                                                                          TokenType type,
                                                                          LocationReferences locationReferences) {
        return restTemplate.exchange(
            authorizeUri(tokenUid, type),
            HttpMethod.POST,
            new HttpEntity<>(locationReferences, httpHeaders(headers)),
            AUTHORIZATION_INFO_RESPONSE
        );
    }

    private URI tokenUri(String countryCode, String partyId, String tokenUid, TokenType type) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(endpoint("/{country_code}/{party_id}/{token_uid}"));
        addIfPresent(builder, "type", type);
        return builder.build(countryCode, partyId, tokenUid);
    }

    private URI authorizeUri(String tokenUid, TokenType type) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(endpoint("/{token_uid}/authorize"));
        addIfPresent(builder, "type", type);
        return builder.build(tokenUid);
    }
}
