import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        Runnable r = new MyRunnable();
        Thread th = new Thread(r);
        th.start();

        // instead of creating a Runnable class, use lambda expression to do the same job
        Runnable r1 =  () ->  System.out.println("Hello world from Lambda!");
        Thread t1 = new Thread(r1);
        t1.start();

        // calculate square. Function takes only 1 arg
        Function<Integer, Integer> squareIt = x -> x * x;
        System.out.println(squareIt.apply(9));

        // can take 2 args
        BiFunction<Integer,Integer,Integer> sum = (a,b) -> a+b;
        System.out.println(sum.apply(8,8));

        // we create our own trifunction interface for more than 2 args
        TriFunction<Integer,Integer,Integer,Integer> sum1 = (u,v,w) -> u+v+w;
        System.out.println(sum1.apply(8,8, 8));

        // check even number using Predicate Functional Interface
        Predicate<Integer> checkEven = num -> num % 2 == 0;
        System.out.println(checkEven.test(87));

        // Consumer functional interface takes an input but does not give an output
        Student s = new Student();
        s.setEnrollmentId(7);
        s.setFirstName("Joe");
        s.setLastName("Doe");

        Consumer<Student> studentDetails = student -> {
            System.out.println(s.getFirstName() + " " + s.getLastName() + " has enrollment Id " + s.getEnrollmentId());
        };
        studentDetails.accept(s);

        // Supplier functional interface does not take any input but gives an output
        Supplier<String> otpGenerator = () -> {
            String otp = "";
            for(int i = 1; i <=6; i++){
                otp = otp + (int)(Math.random() * 10);
            }
            return otp;
        };

        System.out.println(otpGenerator.get());
        System.out.println(otpGenerator.get());
        System.out.println(otpGenerator.get());
        System.out.println(otpGenerator.get());
        System.out.println(otpGenerator.get());

        Student s1 = new Student();
        s1.setEnrollmentId(7);
        s1.setFirstName("Joe");
        s1.setLastName("Doe");

        Student s2 = new Student();
        s2.setEnrollmentId(17);
        s2.setFirstName("Chris");
        s2.setLastName("Pat");

        Student s3 = new Student();
        s3.setEnrollmentId(27);
        s3.setFirstName("Alex");
        s3.setLastName("Bennett");

        ArrayList<Student> students = new ArrayList<>();
        students.add(s1);
        students.add(s2);
        students.add(s3);

        // print first name of students with ID below 20
        for(Student student : students){
            if(student.getEnrollmentId() < 20)
                System.out.println(student.getFirstName());
        }

        System.out.println("---------------------------------------------");

        // print first name of students with ID below 20 using streams
        students.stream().
                filter(x -> x.getEnrollmentId() < 20).forEach(x -> System.out.println(x.getFirstName()));

        System.out.println("---------------------------------------------");

        // collect the students with ID below 20 using streams
        List<Student> idBelow20= students.stream().filter(x -> x.getEnrollmentId() < 20).collect(Collectors.toList());

        // find square of numbers in a list
        List<Integer> numbers = new ArrayList<>();
        numbers.add(3);
        numbers.add(4);
        numbers.add(1);
        numbers.add(2);

        List<Integer> squares = numbers.stream().map(x -> x*x)
                .collect(Collectors.toList());
        System.out.println(numbers);
        System.out.println(squares);

        System.out.println("---------------------------------------------");

        List<Integer> squaresOfEvens = numbers.stream().filter(x -> x%2 == 0).map(x -> x*x)
                .collect(Collectors.toList());
        System.out.println(squaresOfEvens);

        System.out.println("---------------------------------------------");

        // print sorted squares of even using for each
        numbers.stream().filter(x -> x%2 == 0)
                .map(x -> x*x)
                .sorted()
                .forEach(x -> System.out.println(x));

        System.out.println("---------------------------------------------");

        // print sorted first name of students with ID below 20 using streams
        students.stream()
                .filter(x -> x.getEnrollmentId() < 20)
                .sorted()
                .forEach(x -> System.out.println(x.getFirstName()));

        System.out.println("---------------------------------------------");

        // using :: operator (method reference operator)
        students.stream()
                .filter(x -> x.getEnrollmentId() < 20)
                .forEach(Student :: printDetails);

        System.out.println("---------------------------------------------");

        // if the model class does not have compareTo
        students.stream()
                .filter(x -> x.getEnrollmentId() < 20)
                .sorted((x,y) -> x.getFirstName().compareTo(y.getFirstName()))
                .forEach(x -> System.out.println(x.getFirstName()));

        System.out.println("---------------------------------------------");

        int[] marks = {89,70,71,82,99,97};
        System.out.println(Arrays.stream(marks).filter(x -> x>=75).count());

        System.out.println("---------------------------------------------");

        ArrayList<String> words = new ArrayList<>();
        words.add("apple");
        words.add("banana");
        words.add("orange");
        words.add("pineapple");
        words.add("grapes");

        // count of words with >=7 alphabets
        long count = words.stream()
                .filter(x -> x.length() >= 7)
                .count();

        System.out.println(count);

        System.out.println("---------------------------------------------");

        // print words with >=7 alphabets
        words.stream()
                .filter(x -> x.length() >= 7)
                .forEach(x -> System.out.println(x));

        System.out.println("---------------------------------------------");

        Optional<Student> studentOptional = getStudentDetailsFromDb(1);
        if(studentOptional.isPresent()) {
            Student student = studentOptional.get();
            student.printDetails();
        }
        else {
            System.out.println("Student not found!");
        }

        System.out.println("---------------------------------------------");

    }

    private static Optional<Student> getStudentDetailsFromDb(int i) {
        // some DB operations
        //return Optional.of(new Student(1, "Chris", "C"));
        return Optional.empty(); //null
    }
}