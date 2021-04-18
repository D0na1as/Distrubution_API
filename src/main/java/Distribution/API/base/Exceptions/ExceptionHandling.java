package Distribution.API.base.Exceptions;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionHandling extends ResponseEntityExceptionHandler{

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>("Bad Request", HttpStatus.BAD_REQUEST);
    }

    //For Heroku, port is not constant
    @Bean
    public TomcatServletWebServerFactory tomcatCustomizer() {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        factory.addConnectorCustomizers((connector -> {

            /**
             * Gets PORT env variable and sets it as tomcat server port.
             * In Heroku env, the PORT env variable must be used by tomcat
             * because if not the process will fail.
             * Error R10 (Boot timeout) -> Web process failed to bind to $PORT within 60 seconds of launch
             */
            String envPort = System.getenv("PORT");
            // If PORT is NULL, we'll use the default port
            if (envPort != null) {
                connector.setPort(Integer.parseInt(envPort));
            }
        }));
        return factory;
    }
}
