package fr.blaze.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

public interface UserDetailsService extends org.springframework.security.core.userdetails.UserDetailsService {
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
    
    @Transactional
    public UserDetails loadById(Integer id) throws UsernameNotFoundException;
}
