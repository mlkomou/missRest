package com.miss.api.auth.security;

import com.miss.api.auth.entity.Role;
import com.miss.api.auth.entity.User;
import com.miss.api.auth.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Set;

@Service
@Transactional
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    private static Collection<? extends GrantedAuthority> getAuthorities(Set<Role> roles) {
        String[] userRoles = roles.stream().map(Role::getAuthority).toArray(String[]::new);
        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
        return authorities;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {

        final User user = userRepository.findByUsername(username);
        if (user == null) throw new UsernameNotFoundException("Username : " + username + " not found");
        // if (!user.isEnabled()) throw new UsernameNotFoundException("User : " + username + " is not enable");
        // return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthorities(user));
        return org.springframework.security.core.userdetails.User//
                .withUsername(user.getUsername())//
                .password(user.getPassword())
                .authorities(getAuthorities(user.getRoles()))
                .accountLocked(!user.isActive())
                .disabled(!user.isActive())
                .build();
    }
}
