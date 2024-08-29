package org.xumin.mytheater.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.xumin.mytheater.entity.Account;
import org.xumin.mytheater.entity.AuthUser;
import org.xumin.mytheater.entity.Role;
import org.xumin.mytheater.repository.AccountRepository;
import org.xumin.mytheater.service.AccountService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class AccountServiceImpl implements AccountService, UserDetailsService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Account user = accountRepository.findByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException("not found");
            }
            Set<Role> roles = user.getRoles();
            //list role --> list grantedAuthority
            List<GrantedAuthority> authorities = new ArrayList<>();
            if (roles != null) {
                for(Role role: roles){
                    GrantedAuthority authority = new SimpleGrantedAuthority(role.getRoleName());
                    authorities.add(authority);
                }
            }
            return new AuthUser(
                    user.getUsername(),
                    user.getPassword(),
                    authorities,
                    user.getId(),
                    user.getCreatedDate(),
                    user.getStatus()
            );
        }catch (Exception e) {
            e.printStackTrace();
            throw new UsernameNotFoundException("not found");
        }
    }

    @Override
    public Account findByUsername(String username) {
        return accountRepository.findByUsername(username);
    }
}
