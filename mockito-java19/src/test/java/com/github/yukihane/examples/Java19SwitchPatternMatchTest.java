package com.github.yukihane.examples;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class Java19SwitchPatternMatchTest {

    @Test
    void test() {
        final LinkedList<Object> mockedList = mock(LinkedList.class);
        when(mockedList.get(0)).thenReturn("first");

        final var result = new Java19SwitchPatternMatch().func(mockedList);
        assertThat(result).isEqualTo("first");
    }

}
