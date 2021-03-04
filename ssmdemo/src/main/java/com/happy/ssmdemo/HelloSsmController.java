package com.happy.ssmdemo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloSsmController {

        @GetMapping("/hello")
        public String index(ModelMap map) {
            map.addAttribute("host", "hellossm-happy");
            return "index";
        }
}
