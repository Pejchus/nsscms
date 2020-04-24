package cms.config;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ResourceBundle;

import cms.logging.ExceptionWriter;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
@Configuration
public class AppJavaConfig {

    @Autowired
    SpringFXMLLoader springFXMLLoader;

    @Bean
    @Scope("prototype")
    public ExceptionWriter exceptionWriter(){
        return new ExceptionWriter(new StringWriter());
    }

    @Bean
    public ResourceBundle resourceBundle(){
        return ResourceBundle.getBundle("Bundle");
    }

    @Bean
    @Lazy(value = true)
    public StageManager stageManager(Stage stage) throws IOException{
        return new StageManager(springFXMLLoader, stage);
    }
}
