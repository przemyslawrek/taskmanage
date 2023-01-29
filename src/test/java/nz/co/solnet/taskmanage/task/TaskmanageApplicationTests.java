package nz.co.solnet.taskmanage;

import nz.co.solnet.taskmanage.task.Task;
import nz.co.solnet.taskmanage.task.TaskController;
import nz.co.solnet.taskmanage.task.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TaskController.class)
class
TaskmanageApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskRepository repository;


    Task TASK_1 = new Task()
    @Test
    public void greetingShouldReturnPlainMessage() throws Exception {

        this.mockMvc.perform(get("/task/hello"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello there!")));

    }
    @Test
    public void greetingShouldReturnMessageFromService() throws Exception {

        this.mockMvc.perform(get("/task/hello"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello there!")));

    }
}
