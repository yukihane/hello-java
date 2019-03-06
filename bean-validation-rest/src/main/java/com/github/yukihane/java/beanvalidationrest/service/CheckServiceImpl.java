package com.github.yukihane.java.beanvalidationrest.service;

import java.util.regex.Pattern;
import org.springframework.stereotype.Service;

@Service
public class CheckServiceImpl implements CheckService {

    @Override
    public boolean checkRoomname(final String name) {
        return Pattern.matches("\\d\\-\\d", name);
    }
}
