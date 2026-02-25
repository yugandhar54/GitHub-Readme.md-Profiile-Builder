package com.student.readmebuilder;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        String errorMessage = "An unexpected error occurred.";
        int statusCode = 500;

        if (status != null) {
            statusCode = Integer.parseInt(status.toString());
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                errorMessage = "The page you are looking for was not found.";
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                errorMessage = "An internal server error occurred.";
            }
        }

        return String.format(
                "<html><body>" +
                        "<h2>Error %d</h2>" +
                        "<p>%s</p>" +
                        "<p>If you are looking for the API, please use the /api/ endpoints.</p>" +
                        "<a href=\"/\">Go to Home</a>" +
                        "</body></html>",
                statusCode, errorMessage);
    }

    @RequestMapping("/")
    public String home() {
        return "<html><body>" +
                "<h2>Welcome to the README Profile Builder API</h2>" +
                "<p>The backend is running successfully. Please use the frontend application to interact with this API.</p>"
                +
                "</body></html>";
    }
}
