package fr.blaze.calendar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.AllArgsConstructor;
import lombok.Getter;

@SpringBootApplication
public class CalendarApplication {
	public static final Version VERSION = new Version(1, 0, 1);

	public static void main(String[] args) {
		SpringApplication.run(CalendarApplication.class, args);
	}

	@AllArgsConstructor
	public static class Version {
		private @Getter int major;
		private @Getter int minor;
		private @Getter int patch;
	}
}
