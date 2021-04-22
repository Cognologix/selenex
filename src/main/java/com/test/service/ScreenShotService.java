package com.test.service;

import com.test.config.FakerConfig;
import com.test.exception.AutomationRunTimeError;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Provides ability to take screenshots of the web driver.
 * By default screenshots are stored in ./target/logs/screenshots directory.
 */
@Lazy
@Service
public class ScreenShotService {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private FakerConfig fakerConfig;

    @Value("${screenshot.path:./target/logs/screenshots}")
    private Path path;

    @PostConstruct
    public void setup() {
        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            throw new AutomationRunTimeError();
        }
    }

    public void takeScreenShot() throws IOException {

        File sourceFile = this.context.getBean(TakesScreenshot.class).getScreenshotAs(OutputType.FILE);
        FileCopyUtils.copy(sourceFile, this.path.resolve(fakerConfig.faker().name().firstName() + ".png").toFile());
    }

    public byte[] getScreenshot() {
        return this.context.getBean(TakesScreenshot.class).getScreenshotAs(OutputType.BYTES);
    }
}
