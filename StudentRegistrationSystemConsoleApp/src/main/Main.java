package main;

import beans.Student;
import util.InputUtil;
import util.MenuUtil;
import util.StudentUtil;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int menu = 0;
        while (true) {
            menu = InputUtil.requireNumber("What do you want to do?"
                    + "\n1. Register Student"
                    + "\n2. Show all students"
                    + "\n3. Find student"
                    + "\n4. Update student"
                    + "\n5. Exit");
            while (true) {
                MenuUtil.processMenu(menu);
                menu = InputUtil.requireNumber("What do you want to do else?");
//   
            }

        }
    }
}
