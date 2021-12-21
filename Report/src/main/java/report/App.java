package report;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import report.controller.ReportController;

@SpringBootApplication
@EnableFeignClients("report")
@AllArgsConstructor
public class App implements CommandLineRunner {

    @Autowired
    ReportController reportController;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(reportController.getReport(1L).getBody());
    }
}
