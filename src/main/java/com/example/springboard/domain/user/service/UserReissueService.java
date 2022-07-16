package com.example.springboard.domain.user.service;

import com.example.springboard.domain.user.domain.dto.response.TokenResponse;

public interface UserReissueService {

    TokenResponse userReissue(String RefreshToken);
}
