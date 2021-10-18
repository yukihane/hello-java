package com.example.demo;

import org.springframework.stereotype.Service;

// validator にインジェクションするコンポーネント
@Service
public class ComplexBussinessLogic {

    public boolean isValidName(final String name, final String prohibited) {
        return name != null && !name.contains(prohibited);
    }
}
