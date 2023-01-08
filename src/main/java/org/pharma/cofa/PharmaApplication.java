package org.pharma.cofa;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class PharmaApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(PharmaApplication.class)
				.bannerMode(Banner.Mode.OFF)
				.logStartupInfo(false)
				.run(args);
	}

}

