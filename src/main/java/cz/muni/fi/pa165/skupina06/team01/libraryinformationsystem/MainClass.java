package cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 
 * Matúš Čongrády
 */

public class MainClass {

    public static void main(String args[]) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
                PersistenceApplicationContext.class);
        System.out.println("Hello PA165!");
    }
}