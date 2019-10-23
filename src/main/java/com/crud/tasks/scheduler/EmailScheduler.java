package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailScheduler {

    private final SimpleEmailService simpleEmailService;
    private final TaskRepository taskRepository;
    private final AdminConfig adminConfig;

    @Autowired
    public EmailScheduler(SimpleEmailService simpleEmailService, TaskRepository taskRepository, AdminConfig adminConfig) {
        this.simpleEmailService = simpleEmailService;
        this.taskRepository = taskRepository;
        this.adminConfig = adminConfig;
    }

    private static final String SUBJECT = "Tasks: One a day email";

    /* Testowanie funkcjonalności "jobów". Wysyłka email co 10 sekund.
    @Scheduled(fixedDelay = 10000)
    public void sendInformationEmail() {
        final long size = taskRepository.count();
        final String taskString = (size == 1) ? " task" : " tasks";

        simpleEmailService.send(new Mail(
                adminConfig.getAdminMail(),
                SUBJECT,
                "Currently in database you got: " + size + taskString,
                "cc@wp.pl")
        );
    }*/
}
