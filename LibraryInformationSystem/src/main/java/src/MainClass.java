package src;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 
 * Matúš Čongrády
 */

public class MainClass {

    public static void main(String args[]) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(PersistenceApplicationContext.class);
        System.out.println("Hello PA165!");
    }
}