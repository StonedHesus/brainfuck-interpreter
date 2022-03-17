
public class Memory {

    // Attributes of the class.
    private Memory previous;
    private Memory next;
    private int value;

    // Constructors of the class.
    public Memory(){

        this.previous = null;
        this.next     = null;
        this.value    = 0;
    }

    public Memory(int size){

        this();
        Memory temporary = this;
        size -= 1; // We placed the initial value outside the loop.

        while(size != 0){

            temporary.next     = new Memory();
            temporary.next.previous = temporary;
            temporary = temporary.next;
            size              -= 1;
        }
    }

    // Getters of the class.
    public Memory getPrevious() {return this.previous;}
    public Memory getNext() {return this.next;}
    public int getValue() {return this.value;}

    // Setters of the class.
    public void setValue(int value) {this.value = value;}

    // Methods of the class.
    public void increment() {this.value += 1;}
    public void decrement() {this.value -= 1;}

    public void inspect(){

        int lengthOfSequence = this.length();

        assert lengthOfSequence >= 0;

        System.out.println("-".repeat(2 * lengthOfSequence));
        sequence(this);
        location(this);
        System.out.println("-".repeat(2 * lengthOfSequence));
    }

    private void location(Memory current){

        if(current.getPrevious() != null){

            Memory temporary = current;

            while(temporary != null){

                temporary = temporary.previous;
                System.out.print("  ");
            }

        }

        System.out.print("^");

        while(current != null){

            current = current.next;
            System.out.print("  ");
        }

        System.out.print("\n");
    }

    private void sequence(Memory current){

      String line = "";
      if(current.getPrevious() != null){

          Memory temporary = current;

          while(temporary != null){

              line = temporary.getValue() + " " + line;
              temporary = temporary.previous;
          }

          current = current.next;
      }


      while(current != null){

          line += current.getValue() + " ";
          current = current.next;
      }

        System.out.println(line);
    }

    private int length(){

        Memory temporary = this;
        int size = 0;

        // Since the starting position may not be the head or the tail of the list, we will have to check three possible
        // scenarios and act in accordance to them.

        if(temporary.previous == null){
            // We only have values on the right side for we had commenced counting from the head.

            while(temporary != null){

                size += 1;
                temporary = temporary.next;
            }

            return size;
        } else if(temporary.next == null){
            // We only have values on the left side for we had commenced counting from the tail.

            while(temporary != null){

                size += 1;
                temporary = temporary.previous;
            }

            return size;
        }

        if(temporary != null){

            Memory goingLeft = temporary;

            while(goingLeft != null){

                size += 1;
                goingLeft = goingLeft.previous;
            }

            while(temporary != null){
                // We use temporary so as to count how many values lye on the right side of the cell which invoked the
                // function.

                size += 1;
                temporary = temporary.next;
            }

            return size;
        } else return -1;
    }

    @Override
    public String toString() {return "" + this.value + " ";}
}
