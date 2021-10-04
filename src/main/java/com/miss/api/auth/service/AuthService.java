package com.miss.api.auth.service;

import com.miss.api.auth.entity.User;
import com.miss.api.auth.repository.UserRepository;
import com.miss.api.auth.security.jwt.JwtTokenProvider;
import com.miss.api.auth.wrapper.AuthBody;
import com.miss.api.response.CResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@AllArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenProvider jwtTokenProvider;

    private final AuthenticationManager authenticationManager;

    /**
     * @param username
     * @param password
     * @desc user's authentication
     */
    public CResponse<AuthBody> login(String username, String password) {
        try {
            User user = userRepository.findByUsername(username);
            if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
                return CResponse.error("Vos identifiants sont incorrects");
            }

            if (!user.isActive()) {
                return CResponse.error("Vous n'etes plus autorise a vous connecter.\n"
                        + "Veuillez contactez l'administrateur SVP.");
            }

            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            if (auth == null) {
                return CResponse.error("AUTH IS NULL");
            }
            user.setPassword(null);

            return CResponse.success(new AuthBody(user, jwtTokenProvider.createToken(user)), "Vous etes authentifie avec succes");

        } catch (Exception e) {
            e.printStackTrace(System.out);
            return CResponse.error("An error occured");
        }
    }

    /**
     * @param authBody
     * @return
     * @desc change user password
     */
    public CResponse<User> updatePassword(AuthBody authBody) {
        try {
            User user = userRepository.findUserById(authBody.getUserId());
            if (user == null) {
                return CResponse.error("Cet utilisateur n'existe pas");
            }
            if (!passwordEncoder.matches(authBody.getOldPassword(), user.getPassword())) {
                return CResponse.error("Votre ancien mot de passe est incorrect");
            }
            user.setPassword(passwordEncoder.encode(authBody.getNewPassword()));
            userRepository.save(user);

            return CResponse.success(user,"Votre mot de passe a ete change avec succes");

        } catch (Exception e) {
            e.printStackTrace(System.out);
            return CResponse.error("An error occured");
        }
    }

    public CResponse<User> resetPassword(Long id, String newPassword) {
        try {
            User user = userRepository.findUserById(id);
            if (user == null) {
                return CResponse.error("Cet utilisateur n'existe pas");
            }
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);

            return CResponse.success(user, "Le mot de passe a ete reinitialise avec succes");

        } catch (Exception e) {
            e.printStackTrace(System.out);
            return CResponse.error("Une erreur s'est survenue");
        }
    }

    public CResponse<User> forgetPassword(AuthBody authBody) {
        try {
            User user = userRepository.findByUsername(authBody.getUsername());
            if (user == null) {
                return CResponse.error("Cet utilisateur n'existe pas");
            }
            user.setPassword(passwordEncoder.encode(authBody.getNewPassword()));
            userRepository.save(user);
            return CResponse.success(user, "Le mot de passe a été reinitialisé avec succes");
        } catch (Exception e) {
            e.printStackTrace();
            return CResponse.error("Une erreur s'est survenue");
        }
    }

    public CResponse<User> whoIs(HttpServletRequest req) {
        try {
            User user = userRepository.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
            if (user == null) {
                return CResponse.error("Invalide token/session");
            }

            return CResponse.success(user, "Your are this user");
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return CResponse.error("Une erreur s'est survenue");
        }
    }

    public CResponse refreskToken(String username) {
        try {
            User user = userRepository.findByUsername(username);
            if (user == null) {
                return CResponse.error("Invalide username");
            }
            return CResponse.success(jwtTokenProvider.createToken(user), "New generated token");
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return CResponse.error("Une erreur s'est survenue");
        }
    }
}


