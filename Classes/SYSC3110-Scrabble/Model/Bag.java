package Model;

import java.io.Serializable;
import java.util.*;

/**
 * The bag contains all the available letters for the game
 * 
 * @author Laurence Lamarche-Cliche 101173070
 * @version 3.0
 */

public class Bag implements Serializable {
    public static HashSet<Letter> gameLetters;

    public Bag() {
        this.gameLetters = new HashSet<>();
        Letter A1 = new Letter(Letter.Character.A);
        Letter A2 = new Letter(Letter.Character.A);
        Letter A3 = new Letter(Letter.Character.A);
        Letter A4 = new Letter(Letter.Character.A);
        Letter A5 = new Letter(Letter.Character.A);
        Letter A6 = new Letter(Letter.Character.A);
        Letter A7 = new Letter(Letter.Character.A);
        Letter A8 = new Letter(Letter.Character.A);
        Letter A9 = new Letter(Letter.Character.A);

        Letter B1 = new Letter(Letter.Character.B);
        Letter B2 = new Letter(Letter.Character.B);

        Letter C1 = new Letter(Letter.Character.C);
        Letter C2 = new Letter(Letter.Character.C);

        Letter D1 = new Letter(Letter.Character.D);
        Letter D2 = new Letter(Letter.Character.D);
        Letter D3 = new Letter(Letter.Character.D);
        Letter D4 = new Letter(Letter.Character.D);

        Letter E1 = new Letter(Letter.Character.E);
        Letter E2 = new Letter(Letter.Character.E);
        Letter E3 = new Letter(Letter.Character.E);
        Letter E4 = new Letter(Letter.Character.E);
        Letter E5 = new Letter(Letter.Character.E);
        Letter E6 = new Letter(Letter.Character.E);
        Letter E7 = new Letter(Letter.Character.E);
        Letter E8 = new Letter(Letter.Character.E);
        Letter E9 = new Letter(Letter.Character.E);
        Letter E10 = new Letter(Letter.Character.E);
        Letter E11 = new Letter(Letter.Character.E);
        Letter E12 = new Letter(Letter.Character.E);

        Letter F1 = new Letter(Letter.Character.F);
        Letter F2 = new Letter(Letter.Character.F);

        Letter G1 = new Letter(Letter.Character.G);
        Letter G2 = new Letter(Letter.Character.G);
        Letter G3 = new Letter(Letter.Character.G);

        Letter H1 = new Letter(Letter.Character.H);
        Letter H2 = new Letter(Letter.Character.H);

        Letter I1 = new Letter(Letter.Character.I);
        Letter I2 = new Letter(Letter.Character.I);
        Letter I3 = new Letter(Letter.Character.I);
        Letter I4 = new Letter(Letter.Character.I);
        Letter I5 = new Letter(Letter.Character.I);
        Letter I6 = new Letter(Letter.Character.I);
        Letter I7 = new Letter(Letter.Character.I);
        Letter I8 = new Letter(Letter.Character.I);
        Letter I9 = new Letter(Letter.Character.I);

        Letter J1 = new Letter(Letter.Character.J);

        Letter K1 = new Letter(Letter.Character.K);

        Letter L1 = new Letter(Letter.Character.L);
        Letter L2 = new Letter(Letter.Character.L);
        Letter L3 = new Letter(Letter.Character.L);
        Letter L4 = new Letter(Letter.Character.L);

        Letter M1 = new Letter(Letter.Character.M);
        Letter M2 = new Letter(Letter.Character.M);

        Letter N1 = new Letter(Letter.Character.N);
        Letter N2 = new Letter(Letter.Character.N);
        Letter N3 = new Letter(Letter.Character.N);
        Letter N4 = new Letter(Letter.Character.N);
        Letter N5 = new Letter(Letter.Character.N);
        Letter N6 = new Letter(Letter.Character.N);

        Letter O1 = new Letter(Letter.Character.O);
        Letter O2 = new Letter(Letter.Character.O);
        Letter O3 = new Letter(Letter.Character.O);
        Letter O4 = new Letter(Letter.Character.O);
        Letter O5 = new Letter(Letter.Character.O);
        Letter O6 = new Letter(Letter.Character.O);
        Letter O7 = new Letter(Letter.Character.O);
        Letter O8 = new Letter(Letter.Character.O);

        Letter P1 = new Letter(Letter.Character.P);
        Letter P2 = new Letter(Letter.Character.P);

        Letter Q1 = new Letter(Letter.Character.Q);

        Letter R1 = new Letter(Letter.Character.R);
        Letter R2 = new Letter(Letter.Character.R);
        Letter R3 = new Letter(Letter.Character.R);
        Letter R4 = new Letter(Letter.Character.R);
        Letter R5 = new Letter(Letter.Character.R);
        Letter R6 = new Letter(Letter.Character.R);

        Letter S1 = new Letter(Letter.Character.S);
        Letter S2 = new Letter(Letter.Character.S);
        Letter S3 = new Letter(Letter.Character.S);
        Letter S4 = new Letter(Letter.Character.S);

        Letter T1 = new Letter(Letter.Character.T);
        Letter T2 = new Letter(Letter.Character.T);
        Letter T3 = new Letter(Letter.Character.T);
        Letter T4 = new Letter(Letter.Character.T);
        Letter T5 = new Letter(Letter.Character.T);
        Letter T6 = new Letter(Letter.Character.T);

        Letter U1 = new Letter(Letter.Character.U);
        Letter U2 = new Letter(Letter.Character.U);
        Letter U3 = new Letter(Letter.Character.U);
        Letter U4 = new Letter(Letter.Character.U);

        Letter V1 = new Letter(Letter.Character.V);
        Letter V2 = new Letter(Letter.Character.V);

        Letter W1 = new Letter(Letter.Character.W);
        Letter W2 = new Letter(Letter.Character.W);

        Letter X1 = new Letter(Letter.Character.X);

        Letter Y1 = new Letter(Letter.Character.Y);
        Letter Y2 = new Letter(Letter.Character.Y);

        Letter Z1 = new Letter(Letter.Character.Z);

        Collections.addAll(gameLetters, A1, A2, A3, A4, A5, A6, A7, A8, A9, B1, B2, C1, C2, D1, D2, D3, D4,
                E1, E2, E3, E4, E5, E6, E7, E8, E9, E10, E11, E12, F1, F2, G1, G2, G3, H1, H2,
                I1, I2, I3, I4, I5, I6, I7, I8, I9, J1, K1, L1, L2, L3, L4, M1, M2, N1, N2, N3, N4, N5, N6,
                O1, O2, O3, O4, O5, O6, O7, O8, P1, P2, Q1, R1, R2, R3, R4, R5, R6, S1, S2, S3, S4,
                T1, T2, T3, T4, T5, T6, U1, U2, U3, U4, V1, V2, W1, W2, X1, Y1, Y2, Z1);
    }

    public HashSet<Letter> getGameLetters() {
        return this.gameLetters;
    }

    public void print_bag() {
        // Print the letters in the bag
        System.out.println(
                ">>> Bag: There are 9 A's, 2 B's, 2 C's, 4 D's, 12 E's, 2 F's, 3 G's, 2 H's, 9 I's, 1 J's, 1 K's, 4 L's, 2 M's, 6 N's, 8 O's, 2 P's, 1 Q's, 6 R's, 4 S's, 6 T's, 4 U's, 2 V's, 2 W's, 1 X's, 2 Y's, 1 Z's");
    }

    private static <E> E getRandomElement(Set<? extends E> set) {
        Random random = new Random();
        // Generate a random number using nextInt
        // method of the Random class.
        int randomNumber = random.nextInt(set.size());
        Iterator<? extends E> iterator = set.iterator();
        int currentIndex = 0;
        E randomElement = null;
        // iterate the HashSet
        while (iterator.hasNext()) {
            randomElement = iterator.next();
            // if current index is equal to random number
            if (currentIndex == randomNumber)
                return randomElement;
            // increase the current index
            currentIndex++;
        }
        return randomElement;
    }

    public static ArrayList<Letter> drawLetters(Integer numberOfLetters) {
        ArrayList<Letter> lettersDrawn = new ArrayList<Letter>();

        for (int i = 0; i < numberOfLetters; i++) {
            Letter randomLetter = Bag.getRandomElement(Bag.gameLetters);
            lettersDrawn.add(randomLetter);
            Bag.gameLetters.remove(randomLetter); // that letter cannot be drawn again
        }
        return lettersDrawn;
    }

}