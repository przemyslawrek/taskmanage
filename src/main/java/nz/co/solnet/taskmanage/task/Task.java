package nz.co.solnet.taskmanage.task;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
class Task {
    private @Id
    @GeneratedValue Long id;
    @NotNull
    @Size(min = 1, max = 255)
    private String title;
    @Size(max = 1024)
    private String description;
    private Date dueDate;
    @Size(max = 10)
    private String status;
    @Column(nullable = false)
    @Past
    private Date creationDate;

    @PrePersist
    void preInsert() {
        if (this.creationDate == null) {
            this.creationDate = new Date();
        }
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}
