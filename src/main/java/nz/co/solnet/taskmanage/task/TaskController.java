package nz.co.solnet.taskmanage.task;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("task")
@Tag(name="Task API", description="API for Task Manage. Solnet code challenge")
public class TaskController {
    private final TaskRepository repository;

    public TaskController(TaskRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    @Operation(summary = "Retrieves all tasks")
    List<Task> getAllTasks(){
        return repository.findAll();
    }

    @GetMapping("/overdue")
    @Operation(summary = "Retrieves only tasks with dueDate from the past")
    List<Task> getOverdueTasks(){
        return repository.findAllByDueDateBefore(new Date());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retrieves task for given ID")
    Task getTask(@PathVariable Long id){
        return repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    @PostMapping("/")
    @Operation(summary = "Adds provided task")
    Task newTask(@Valid @RequestBody Task task){
        return repository.save(task);
    }

    @PostMapping("/example")
    @Operation(summary = "Adds example task")
    Task addExampleTask(){
        Task exampleTask = new Task();
        exampleTask.setTitle("example Task");
        return repository.save(exampleTask);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletes task with given ID")
    void deleteTask(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Upserts task for given ID")
    Task replaceTask(@Valid @RequestBody Task newTask, @PathVariable Long id) {

        return repository.findById(id)
                .map(task -> {
                    task.setTitle(newTask.getTitle());
                    task.setDescription(newTask.getDescription());
                    task.setDueDate(newTask.getDueDate());
                    task.setStatus(newTask.getStatus());
                    task.setCreationDate(newTask.getCreationDate());

                    return repository.save(task);
                })
                .orElseGet(() -> {
                    newTask.setId(id);
                    return repository.save(newTask);
                });
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
