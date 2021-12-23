package fr.blaze.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Getter;


@RestController
@RequestMapping("/api")
public class VersionController {

    public static final Version VERSION = new Version(1, 0, 0);
    
    @GetMapping("/version")
    private Version getVersion() {
        return VERSION;
    }
    

  @AllArgsConstructor
  public static class Version {
    private @Getter int major;
    private @Getter int minor;
    private @Getter int patch;
  }
}
