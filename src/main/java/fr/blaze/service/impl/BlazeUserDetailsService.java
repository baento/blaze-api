package fr.blaze.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import fr.blaze.security.AuthoritiesConstants;
import fr.blaze.security.BlazeUserDetails;

/**
 * Authenticate a user from the database.
 */
public class BlazeUserDetailsService implements AuthenticationUserDetailsService<CasAssertionAuthenticationToken> {

    public BlazeUserDetailsService() {
        super();
    }

    @Override
    public UserDetails loadUserDetails(CasAssertionAuthenticationToken token) throws UsernameNotFoundException {
        String userPrincipal = (String) token.getPrincipal();

        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();

        grantedAuthorities.add(new GrantedAuthority() {
            private static final long serialVersionUID = 1L;

            @Override
            public String getAuthority() {
                return AuthoritiesConstants.USER;
            }
        });

        return new BlazeUserDetails(userPrincipal, grantedAuthorities);
    }
}