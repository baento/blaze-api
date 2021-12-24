package fr.blaze.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlazeUserDetails implements UserDetails {

	private String username;

	private Collection<? extends GrantedAuthority> authorities;

	private List<String> roles = new ArrayList<>();;

	public BlazeUserDetails() {
		super();
	}

	public BlazeUserDetails(String username, Collection<? extends GrantedAuthority> authorities) {
		this.username = username;
		this.authorities = authorities;

		for (GrantedAuthority authority : authorities) {
			this.roles.add(authority.getAuthority());
		}
	}

	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}