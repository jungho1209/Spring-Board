package com.example.springboard.domain.user.service;

import com.example.springboard.domain.user.domain.RefreshToken;
import com.example.springboard.domain.user.controller.dto.response.TokenResponse;
import com.example.springboard.domain.user.domain.repository.RefreshTokenRepository;
import com.example.springboard.domain.user.exception.InvalidRefreshTokenException;
import com.example.springboard.domain.user.exception.RefreshTokenNotFoundException;
import com.example.springboard.global.jwt.JwtProperty;
import com.example.springboard.global.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ReissueService implements UserReissueService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtProperty jwtProperty;


    @Override
    @Transactional
    public TokenResponse userReissue(String refreshToken) {
        return reIssue(refreshToken);
    }

    private TokenResponse reIssue(String refreshToken) {
        String parseToken = jwtTokenProvider.parseToken(refreshToken);
        if (parseToken == null) {
            throw InvalidRefreshTokenException.EXCEPTION;
        }

        RefreshToken redisRefreshToken = refreshTokenRepository.findByToken(parseToken)
                .orElseThrow(() -> RefreshTokenNotFoundException.EXCEPTION);


        TokenResponse tokens = jwtTokenProvider.generateTokens(redisRefreshToken.getAccountId());

        redisRefreshToken.updateToken(tokens.getRefreshToken(), jwtProperty.getRefreshExp() * 1000);

        return TokenResponse.builder()
                .accessToken(tokens.getAccessToken())
                .refreshToken(tokens.getRefreshToken())
                .build();
    }
}
