
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

//        Memory firstTest = new Memory(5);
//
//        firstTest.inspect();
//
//        firstTest.setValue(42);
//        firstTest.inspect();
//        firstTest.getNext().setValue(41);
//        firstTest.inspect();
//        firstTest.getNext().getNext().setValue(40);
//        firstTest.getNext().inspect();
//        firstTest.getNext().inspect();
//        firstTest.getNext().getNext().setValue(40);
//        firstTest.getNext().inspect();
//        firstTest.getNext().getNext().inspect();

//        firstTest.getNext().getNext().getNext().getNext().setValue(42);
//        firstTest.inspect();


        //brainf_ck(",>,[-<+>]<.");
        //brainf_ck(",>,[-<->]<."); // This program computes the difference of two numbers.
        brainf_ck(">>,>,<[>[<<+<+>>>-]<<[>>+<<-]>-]>[-]<<[-]<."); // This program multiplies two numbers.
        //brainf_ck("[<<+<+>>>-]<<[>>+<<-]>-."); // Fraudulent expression.
    }

    // Methods of the class.
    public static int correspondingOpenTag(String program, int depart) {

        int index = 0;

        for (int i = depart; i >= 0; --i) {

            if(program.charAt(i) == ']') index += 1;

            if(program.charAt(i) == '[' && index != 0) index -= 1;

            if (program.charAt(i) == '[' && index == 0) {

                return i;
            }
        }


        return -1;
    }

    public static int correspondingEndTag(String program, int depart) {

        int index = 0;

        for (int i = depart; i < program.length(); ++i) {

            if(program.charAt(i) == '['){

                index += 1;
            }

            if(program.charAt(i) == ']' && index != 0){

                index -= 1;
            }

            if (program.charAt(i) == ']' && index == 0) {

                return i;
            }
        }
        return -1;
    }

    public static void brainf_ck(String program) {

        Memory memory = new Memory(program.length());

        for (int index = 0; index < program.length(); ++index) {

            memory.inspect();

            switch (program.charAt(index)) {

                case '>' ->{

                    if(memory.getNext() == null){

                        throw new OutOfMemoryError("Your code makes the program to overflow.");
                    }

                    memory = memory.getNext();
                }

                case '<' -> {

                    if(memory.getPrevious() == null){

                        throw new OutOfMemoryError("Your code makes the program to underflow.");
                    }
                    memory = memory.getPrevious();
                }

                case '+' -> memory.increment();

                case '-' -> memory.decrement();

                case '.' -> {

                    System.out.println(memory.getValue());
                    if (index == program.length() - 1) {

                        System.exit(0);
                    }
                }

                case ',' -> {

                    Scanner scanner = new Scanner(System.in);
                    memory.setValue(scanner.nextInt());
                }

                case '[' -> {

                    if (memory.getValue() == 0) {

                        index = Main.correspondingEndTag(program, index);
                    }
                }

                case ']' -> index = Main.correspondingOpenTag(program, index)-1;
            }
        }
    }
}
