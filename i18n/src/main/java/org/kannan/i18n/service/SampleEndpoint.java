package org.kannan.i18n.service;

import org.kannan.i18n.Messages;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SampleEndpoint
{

    @GetMapping("/message")
    @ResponseBody
    public String getMood()
    {
        return Messages.getMessage("i18n.message");
    }
}
