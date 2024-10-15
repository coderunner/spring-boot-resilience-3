
package com.inf5190.resilience.unstable;

import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.inf5190.resilience.model.User;

@RestController
@PropertySource("application.properties")
public class UnstableController {
    private static final Logger LOG = LoggerFactory.getLogger(UnstableController.class);

    private final Random r = new Random();

    @Value("${unstable-value}")
    int unstableValue;

    @GetMapping("/unstable/users/{id}")
    public User getUser(@PathVariable("id") String id) throws InterruptedException {
        LOG.info("Un log dans le controller INSTABLE.");
        if (this.r.nextInt(10) < this.unstableValue) {
            // Simulate errors
            throw new RuntimeException("Problème!");
        }

        return new User(id);
    }
}
