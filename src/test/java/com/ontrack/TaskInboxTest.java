package com.ontrack;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;
import java.util.Optional;

public class TaskInboxTest {

    @Test
    public void testAddAndRetrieveTasks() {
        TaskInbox inbox = new TaskInbox();
        inbox.addTask("T001", "Complete Math Assignment");
        inbox.addTask("T002", "Prepare for Science Project");

        List<TaskInbox.Task> tasks = inbox.getTasks();
        assertEquals(2, tasks.size());
        assertEquals("Complete Maths Assignment", tasks.get(0).getDescription());
        assertEquals("Prepare for Science Project", tasks.get(1).getDescription());
    }

    @Test
    public void testMarkTaskAsCompleted() {
        TaskInbox inbox = new TaskInbox();
        inbox.addTask("T001", "Submit Java Project");
        
        boolean result = inbox.completeTask("T001");
        assertTrue(result);

        Optional<TaskInbox.Task> task = inbox.getTaskById("T001");
        assertTrue(task.isPresent());
        assertTrue(task.get().isCompleted());
    }

    @Test
    public void testGetPendingAndCompletedTasks() {
        TaskInbox inbox = new TaskInbox();
        inbox.addTask("T001", "Upload Assignment");
        inbox.addTask("T002", "Schedule Meeting");

        inbox.completeTask("T001");

        List<TaskInbox.Task> pendingTasks = inbox.getPendingTasks();
        List<TaskInbox.Task> completedTasks = inbox.getCompletedTasks();

        assertEquals(1, pendingTasks.size());
        assertEquals("Schedule Meeting", pendingTasks.get(0).getDescription());

        assertEquals(1, completedTasks.size());
        assertEquals("Upload Assignment", completedTasks.get(0).getDescription());
    }

    @Test
    public void testGetTaskById() {
        TaskInbox inbox = new TaskInbox();
        inbox.addTask("T003", "Attend Workshop");

        Optional<TaskInbox.Task> task = inbox.getTaskById("T003");
        assertTrue(task.isPresent());
        assertEquals("Attend Workshop", task.get().getDescription());
    }
}
