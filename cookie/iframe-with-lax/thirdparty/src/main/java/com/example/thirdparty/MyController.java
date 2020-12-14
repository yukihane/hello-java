package com.example.thirdparty;

import java.util.Optional;
import javax.servlet.http.HttpSession;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {

    private static final String KEY = "mycounter";

    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String index(final HttpSession sess) {
        final int value = Optional.ofNullable((Integer) sess.getAttribute(KEY)).orElse(Integer.valueOf(0));
        sess.setAttribute(KEY, value + 1);

        return String.format("<html><body>%d 回目のアクセスです</body></html>", value);
    }

}
