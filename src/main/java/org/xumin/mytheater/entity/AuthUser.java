package org.xumin.mytheater.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;

@Getter
@Setter
public class AuthUser extends org.springframework.security.core.userdetails.User {
    private Long id;
    private LocalDate createAt;
    private int status;
    public AuthUser(String username,
                    String password,
                    Collection<? extends GrantedAuthority> authorities,
                    Long id,
                    LocalDate createAt,
                    int status) {
        super(username, password, authorities);
        this.id = id;
        this.createAt = createAt;
        this.status = status;
    }
}
