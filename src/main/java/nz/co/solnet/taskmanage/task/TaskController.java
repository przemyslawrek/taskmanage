package nz.co.solnet.taskmanage;


import nz.co.solnet.taskmanage.task.TaskRepository;
import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.*;

@RestController
public class TaskController {
    private final TaskRepository repository;

    public TaskController(TaskRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/hello")
    String hello() {
        return new String("Hello there!");
    }

    @PostMapping("/task")
    Long putTask(@RequestBody Task task){
        return repository.save(task);
    }
}
