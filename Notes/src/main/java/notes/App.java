package notes;

import lombok.AllArgsConstructor;
import notes.controller.NoteController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication()
@AllArgsConstructor
public class App implements CommandLineRunner {

    private NoteController noteController;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    }
}
