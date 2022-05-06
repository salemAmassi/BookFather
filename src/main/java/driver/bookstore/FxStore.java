package driver.bookstore;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;
/**
 * Our main driver of the project
 * */
public class FxStore extends Application {
    //application context
    private ConfigurableApplicationContext applicationContext;
    @Override
    public void start(Stage stage){
        applicationContext.publishEvent(new StageReadyEvent(stage));
    }

    @Override
    public void init()  {
        applicationContext =
                new SpringApplicationBuilder(BookStoreApplication.class).run();
    }

    @Override
    public void stop() {
       applicationContext.close();
        Platform.exit();
    }
    //to give access to all listeners into the primary stage!
    static class StageReadyEvent extends ApplicationEvent {
        public StageReadyEvent(Stage stage) {
            super(stage);
        }
        public Stage getStage(){
            return ((Stage) super.getSource());
        }
    }
}
