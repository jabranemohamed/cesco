package com.adservio.cesco.services.impl;

import com.adservio.cesco.repositories.UserRepository;
import com.adservio.cesco.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

}
