package cms.config;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.apache.catalina.core.ApplicationContext;
import org.graalvm.compiler.lir.CompositeValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ResourceBundle;


    @Component
    public class SpringFXMLLoader {
        private final ResourceBundle resourceBundle;
        private final ApplicationContext context;


        @Autowired
        public SpringFXMLLoader(ApplicationContext context, ResourceBundle resourceBundle){
            this.resourceBundle = resourceBundle;
            this.context = context;
        }

        public Parent load(String fxmlPath) throws IOException {
            FXMLLoader loader = new FXMLLoader();
            loader.setControllerFactory(context::getBean);
            loader.setResources(resourceBundle);
            loader.setLocation(getClass().getResource(fxmlPath));
            return loader.load();
        }

    }
