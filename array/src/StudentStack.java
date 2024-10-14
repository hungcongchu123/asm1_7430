import java.util.Stack;
import java.util.EmptyStackException;

public class StudentStack {
    private Stack<Student> stack = new Stack<>();

    public void push(Student student) {
        stack.push(student);
    }

    public Student pop() {
        if (!stack.isEmpty()) {
            return stack.pop();
        } else {
            throw new EmptyStackException();
        }
    }

    public Student peek() {
        if (!stack.isEmpty()) {
            return stack.peek();
        } else {
            throw new EmptyStackException();
        }
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public void displayStack() {
        if (stack.isEmpty()) {
            System.out.println("Stack is empty.");
        } else {
            for (Student student : stack) {
                System.out.println(student);
            }
        }
    }
}
