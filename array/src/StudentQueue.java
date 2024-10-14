import java.util.Queue;
import java.util.LinkedList;
import java.util.NoSuchElementException;
public class StudentQueue {
        private Queue<Student> queue = new LinkedList<>();

        public void enqueue(Student student) {
            queue.add(student);
        }

        public Student dequeue() {
            if (!queue.isEmpty()) {
                return queue.poll();
            } else {
                throw new NoSuchElementException("The queue is empty..");
            }
        }

        public Student peek() {
            if (!queue.isEmpty()) {
                return queue.peek();
            } else {
                throw new NoSuchElementException("The queue is empty..");
            }
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }

        public void displayQueue() {
            if (queue.isEmpty()) {
                System.out.println("The queue is empty..");
            } else {
                for (Student student : queue) {
                    System.out.println(student);
                }
            }
        }


}
