package org.pragadeesh.gri.controller;

import com.azure.core.annotation.Get;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/demo")
    public String demo() {
        return "Welcome to authenticated URL";
    }
}
