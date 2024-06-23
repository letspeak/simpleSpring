package com.example.springboot;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@RestController
@CrossOrigin
public class HelloController {


	private Counter apiCounter;
	private Counter helloCounter;

	private MeterRegistry meterRegistry;


	
	public HelloController(MeterRegistry meterRegistry){
		this.meterRegistry = meterRegistry;
		this.apiCounter = Counter.builder("Get.Counter")
						.tags("status","created")
						.description("Total Get Request")
						.register(meterRegistry);
		this.helloCounter = Counter.builder("Hello.Counter")
		.tags("status","created")
		.description("Total Hello Request")
		.register(meterRegistry);

	}

	@GetMapping("/api")
	public String api() {
		apiCounter.increment();
		return "Greetings from Spring Boot!";
	}

	@GetMapping("/hello")
	public String hello() {
		helloCounter.increment();
		return "Hello from Spring Boot!";
	}

	@Timed(value = "slow.request",description = "Slow API Call",histogram = true,percentiles = {0.5,0.9,0.95})
	@GetMapping("/show")
	public String slowAPI() throws InterruptedException {
		Random random = new Random();
		TimeUnit.SECONDS.sleep(random.nextInt(10));
		return "Success";
	}

}
