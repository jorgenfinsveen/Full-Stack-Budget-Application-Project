//package no.idata1002.group19;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//
//import org.springframework.http.MediaType;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.stereotype.Component;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
///**
// * Represents an component that implements the AuthenticationEntryPoint
// * interface. Contains a method to handle authenticaton and sends an HTTP
// * unauthorized response with JSON error message.
// *
// * @author  Group19
// * @since   16.04.2023
// * @version 16.04.2023
// */
//@Component
//public class AuthEntryPoint implements AuthenticationEntryPoint {
//    @Override
//    public void commence(HttpServletRequest request, HttpServletResponse response,
//            AuthenticationException authException) throws IOException, ServletException {
//
//        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//        PrintWriter writer = response.getWriter();
//        writer.println("Error: " + authException.getMessage());
//    }
//}
