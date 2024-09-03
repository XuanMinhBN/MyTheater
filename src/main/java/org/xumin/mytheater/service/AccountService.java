package org.xumin.mytheater.service;

import org.xumin.mytheater.entity.Account;

public interface AccountService {
    Account findByUsername(String username);

    String getCurrentUsername();
}
