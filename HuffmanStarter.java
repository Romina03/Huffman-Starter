package a05;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * This program creates a Huffman tree,
 * 
 * @author Romina Rahman (A00447372)
 */
public class HuffmanStarter {

    public static final Scanner KBD = new Scanner(System.in);

    public static void main(String[] args) {
        // Declare the variables
        String line;
        PriorityQueue<Entry> pqueue = new PriorityQueue<>(
                (entry1, entry2) -> entry1.compareTo(entry2));
        List<Entry> theList = new ArrayList<>();
        String combinedString;
        int combinedFrequency;
        Entry firstEntry, secondEntry;

        printIntroduction();

        pause();

        // Asking for input
        System.out.println("Enter a line of text below: ");
        line = KBD.nextLine();

        // Making the list of Entries and counting the frequencies
        theList = makeTheList(line);

        // Show the frequencies
        System.out.println("\nCalculating Frequencies: ");
        for (int a = 0; a < theList.size(); a++) {
            System.out.println("\t" + theList.get(a));
        }

        // Add to the priority queue
        for (int i = 0; i < theList.size(); i++) {
            pqueue.add(theList.get(i));
        }

        // Making the Huffman tree
        System.out.println("\nProcessing the Priority Queue: ");
        firstEntry = pqueue.remove();
        while (pqueue.size() > 1) {
            secondEntry = pqueue.remove();
            combinedString = "(" + firstEntry.character + ","
                    + secondEntry.character + ")";
            combinedFrequency = firstEntry.frequency + secondEntry.frequency;
            System.out.println("\tCombining " + firstEntry + " and "
                    + secondEntry);
            firstEntry = new Entry(combinedString, combinedFrequency);
            pqueue.add(firstEntry);
            firstEntry = pqueue.remove();
        }

        if (pqueue.size() == 1) {
            secondEntry = pqueue.remove();
            combinedString = "(" + firstEntry.character + ","
                    + secondEntry.character + ")";
            combinedFrequency = firstEntry.frequency + secondEntry.frequency;
            System.out.println("\tCmbining " + firstEntry + " and "
                    + secondEntry);
            firstEntry = new Entry(combinedString, combinedFrequency);
        }

        // Add to the priority queue
        pqueue.add(firstEntry);

        // Output
        System.out.println("\nHuffman 'tree': \n"
                + "\t" + firstEntry.character);
    }

    /**
     * This method prints the introduction.
     */
    public static void printIntroduction() {
        System.out.println("Huffman Coding Preview\n---------------------"
                + "\n\nThis program creates a 'tree' that can be used to "
                + "do a Huffman coding of text\nentered by the user.\n\n"
                + "Program by Romina Rahman (A00447372)\n");
    }

    /**
     * This method pauses the program.
     */
    public static void pause() {
        System.out.println("Press enter...");
        KBD.nextLine();

    }

    /**
     * This method returns the list produced after going through the String.
     *
     * @param line the String to go through
     * @return the list of entries
     */
    public static List<Entry> makeTheList(String line) {
        List<Entry> theList = new ArrayList<>();
        theList.add(new Entry(String.valueOf(line.charAt(0)), 1));
        for (int i = 1; i < line.length(); i++) {
            Entry test = new Entry(String.valueOf(line.charAt(i)), 1);
            if (!theList.contains(test)) {
                theList.add(test);
            } else {
                for (int a = 0; a < theList.size(); a++) {
                    Entry test2 = theList.get(a);
                    if (test2.equals(test)) {
                        test2.increase();
                    }
                }
            }
        }
        return theList;
    }

    private static class Entry implements Comparable<Entry> {

        private String character;
        private int frequency;

        /**
         * The constructor gives values to the instance variables.
         *
         * @param theCharacter
         * @param theFrequency
         */
        public Entry(String theCharacter, int theFrequency) {
            character = theCharacter;
            frequency = theFrequency;
        }

        /**
         * Presents the Entry object as a String.
         * 
         * @return the String
         */
        public String toString() {
            return "(" + character + ", " + frequency + ")";
        }

        /**
         * Increases the frequency of the entry.
         */
        public void increase() {
            frequency++;
        }

        /**
         * This method compares two Entry objects and tells if they are equal or
         * not.
         *
         * @param other the Object
         * @return true if the objects are equal and false if not
         * @author Mark Young
         */
        public boolean equals(Object other) {
            if (other instanceof Entry) {
                Entry that = (Entry) other;
                return this.character.equals(that.character);
            }
            return false;
        }

        /**
         * This method compares two Entry objects and returns a value that
         * allows them to be ordered from lowest frequency to highest frequency.
         *
         * @param o the Entry
         * @return the integer value
         */
        @Override
        public int compareTo(Entry o) {
            return this.frequency - o.frequency;
        }

    }

}