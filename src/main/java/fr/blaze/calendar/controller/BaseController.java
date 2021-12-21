package fr.blaze.calendar.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.blaze.calendar.CalendarApplication;
import fr.blaze.calendar.CalendarApplication.Version;

@RestController
@RequestMapping("/api")
public class BaseController {
    
    @GetMapping("/version")
    private Version getVersion() {
        return CalendarApplication.VERSION;
    }
}
