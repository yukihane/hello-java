package com.github.yukihane.hello_mapstruct.partialcopy;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ObjMapperTest {

    /**
     * @see <a href=
     * "http://mapstruct.org/documentation/stable/reference/html/#updating-bean-instances">
     * 3.4. Updating existing bean instances</a>
     */
    @Test
    public void test() {

        final FullObj full = new FullObj("my name", 20, "my address");
        final PartObj part = new PartObj(21, null);

        ObjMapper.INSTANCE.updateObj(part, full);

        assertEquals("PartObj には name フィールドがないので もともとの情報が保持される", "my name", full.getName());
        assertEquals(21, full.getAge());
        assertEquals(null, full.getAddr());
    }

}
