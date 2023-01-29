package nz.co.solnet.taskmanage.task;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.Date;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TaskmanageApplicationTests {

    @Value(value = "http://localhost:${local.server.port}/task/")
    private String taskUrl;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private TaskRepository repository;

    Task TASK_VALID = new Task(null, "Title1", "Description1", DateUtils.addDays(new Date(), -30), "PASSED", null);
    Task TASK_NULL_TITLE = new Task(2L, null, "Description2", DateUtils.addDays(new Date(), +30), "PASSED", null);
    Task TASK_FROM_FUTURE = new Task(3L, "Title3", "Description3", DateUtils.addDays(new Date(), +30), "PASSED", DateUtils.addDays(new Date(), +30));


    @Test
    public void postShouldSaveValidTask() throws Exception {


        URI uri = new URI(taskUrl);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<Task> request = new HttpEntity<>(TASK_VALID, headers);

        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);

        //Verify request succeed
        Assert.assertEquals(200, result.getStatusCodeValue());
    }

    @Test
    public void postShouldFailIfNoTitle() throws Exception {


        URI uri = new URI(taskUrl);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<Task> request = new HttpEntity<>(TASK_NULL_TITLE, headers);

        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);

        //Verify request failure
        Assert.assertNotEquals(200, result.getStatusCodeValue());
    }

    @Test
    public void postShouldFailIfCreationDateIsFromFuture() throws Exception {


        URI uri = new URI(taskUrl);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<Task> request = new HttpEntity<>(TASK_FROM_FUTURE, headers);

        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);

        //Verify request failure
        Assert.assertNotEquals(200, result.getStatusCodeValue());
    }

}
