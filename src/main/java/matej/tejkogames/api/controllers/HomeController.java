package matej.tejkogames.api.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HomeController {

	@GetMapping("")
	public void home(HttpServletResponse response) throws IOException {
		response.sendRedirect("/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config");
	}
}
