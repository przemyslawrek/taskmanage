package nz.co.solnet.taskmanage.task;

class TaskNotFoundException extends RuntimeException {

    TaskNotFoundException(Long id) {
        super("Could not find task " + id);
    }
}