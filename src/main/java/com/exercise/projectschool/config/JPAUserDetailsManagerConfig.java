package com.exercise.projectschool.config;

import com.exercise.projectschool.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**JPAUserDetailsManagerConfigfile utilizzerà UserSecurityConfig, e UserEntityper per trovare l'utente e mapparloAuthentication*/

@Service
@RequiredArgsConstructor
public class JPAUserDetailsManagerConfig implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByUsername(username) // Create the method like this Optional<UserEntity> findByUsername(String username);
                .map(UserSecurityConfig::new)
                .orElseThrow(()-> new UsernameNotFoundException("User: "+username+" does not exist"));
    }
}