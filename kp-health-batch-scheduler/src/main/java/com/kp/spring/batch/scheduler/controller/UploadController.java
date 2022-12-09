package com.kp.spring.batch.scheduler.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class UploadController {

    private static final Logger LOG = LoggerFactory.getLogger(UploadController.class);
    //private static final String UPLOADED_FOLDER = System.getProperty("user.home") + "/Documents/TestProject_New/Test/InputData//" ;

    private static final String UPLOADED_FOLDER = System.getProperty("user.home") + "/Documents/InputData/" ;

    @Autowired
    JobLauncher jobLauncher;
    @Autowired
    Job processTransaction;

    @GetMapping("/upload")
    public String index() {
        return "upload";
    }

    @PostMapping("/upload")
    public String singleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }
        try {
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        redirectAttributes.addFlashAttribute("uploadTransactionFile", file.getOriginalFilename());
        return "redirect:invokejob";
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }

    @GetMapping("/invokejob")
    public String handle(RedirectAttributes redirectAttributes,@ModelAttribute("uploadTransactionFile") String uploadTransactionFile) throws Exception {
        LOG.info("starting batch job with uploadTransactionFile::"+uploadTransactionFile);
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("JobID", String.valueOf(System.currentTimeMillis()))
                .addString("inputTransactionFile", uploadTransactionFile)
                .addString("outputTransactionFile", "uploadTransactionProcessedFile.csv")
                .toJobParameters();
        jobLauncher.run(processTransaction, jobParameters);
        redirectAttributes.addFlashAttribute("message", "Processed file location is "+UPLOADED_FOLDER+"/uploadTransactionProcessedFile.csv");
        return "redirect:uploadStatus";
    }
}
