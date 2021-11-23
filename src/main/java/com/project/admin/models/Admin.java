package com.project.admin.models;


import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Data
@Entity
public class Admin implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer adminId;
    @Column(unique = true)
    private String email;
    private String password;
    private String name;
    private String contactNumber;
    private String role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {

        SimpleGrantedAuthority simpleGrantedAuthority=new SimpleGrantedAuthority(this.getRole());
        return List.of(simpleGrantedAuthority);

    }

    @Override
    public String getUsername() {
        return this.email;
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
