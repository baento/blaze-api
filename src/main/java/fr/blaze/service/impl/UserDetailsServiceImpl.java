package fr.blaze.service.impl;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.blaze.model.User;
import fr.blaze.repository.UserRepository;
import fr.blaze.security.UserPrincipal;
import fr.blaze.service.UserDetailsService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    public UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByHandle(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        return UserPrincipal.builder()
                .id(user.getId())
                .username(user.getHandle())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }

    @Override
    public UserDetails loadById(Integer id) throws UsernameNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        return UserPrincipal.builder()
                .id(user.getId())
                .username(user.getHandle())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }
}
