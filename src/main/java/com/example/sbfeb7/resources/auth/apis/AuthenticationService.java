package com.example.sbfeb7.resources.auth.apis;

import com.example.sbfeb7.config.JwtService;
import com.example.sbfeb7.resources.token.entities.Token;
import com.example.sbfeb7.resources.token.entities.TokenType;
import com.example.sbfeb7.resources.token.repos.TokenRepository;
import com.example.sbfeb7.resources.users.entities.User;
import com.example.sbfeb7.resources.users.mappers.UserMapper;
import com.example.sbfeb7.resources.users.repos.UserRepository;
import com.example.sbfeb7.resources.users.request.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final UserRepository repository;
  private final TokenRepository tokenRepository;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthenticationResponse register(UserRequest request) {
    var user = UserMapper.INSTANCE.userRequestToUser(request);
    user.setId(UUID.randomUUID());
    var savedUser = repository.save(user);
    var jwtToken = jwtService.generateToken(Map.of("role", user.getRole().toString()),user);
    saveUserToken(savedUser, jwtToken);
    return AuthenticationResponse.builder()
            .token(jwtToken)
            .build();
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    //TODO: Do OTP Validation
//    authenticationManager.authenticate(
//        new UsernamePasswordAuthenticationToken(
//            request.getMobileNumber(),
//            "IGNORED"
//        )
//    );
    var user = repository.findByMobileNumber(request.getMobileNumber())
        .orElseThrow();
    var jwtToken = jwtService.generateToken(Map.of("role", user.getRole().toString()),user);
    revokeAllUserTokens(user);
    saveUserToken(user, jwtToken);
    return AuthenticationResponse.builder()
        .token(jwtToken)
        .build();
  }

  private void saveUserToken(User user, String jwtToken) {
    var token = Token.builder()
        .user(user)
        .token(jwtToken)
        .tokenType(TokenType.BEARER)
        .expired(false)
        .revoked(false)
        .build();
    tokenRepository.save(token);
  }

  private void revokeAllUserTokens(User user) {
    var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
    if (validUserTokens.isEmpty())
      return;
    validUserTokens.forEach(token -> {
      token.setExpired(true);
      token.setRevoked(true);
    });
    tokenRepository.saveAll(validUserTokens);
  }
}
