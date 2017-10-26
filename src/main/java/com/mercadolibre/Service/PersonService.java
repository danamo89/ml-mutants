package com.mercadolibre.Service;

import com.mercadolibre.Exceptions.HumanMalformedDnaException;
import com.mercadolibre.Model.Person;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import org.springframework.stereotype.Service;

/**
 *
 * @author david
 */
@Service
public class PersonService {

    static final String DNA_BASES = "ATCG";
    static final int WORD_SIZE = 4;
    static final Pattern PATTERN = Pattern.compile("([a-z\\d])\\1{3,}", Pattern.CASE_INSENSITIVE);

    public boolean isMutant(Person person) throws HumanMalformedDnaException {
        Map<String, Integer> coincidences = new HashMap<>();
        String[][] matrix = new String[person.getDna().length][person.getDna()[0].length()];

        for (int i = 0; i < person.getDna().length; i++) {
            //Matrix Load
            for (int j = 0; j < person.getDna()[i].length(); j++) {
                //Matrix Bases Validation
                String sigleChar = String.valueOf(person.getDna()[i].charAt(j)).toUpperCase();
                if (DNA_BASES.contains(sigleChar)) {
                    matrix[i][j] = sigleChar;
                } else {
                    throw new HumanMalformedDnaException();
                }
            }
        }

        //printMatrix(matrix);

        //Rows -- OK
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col + WORD_SIZE <= matrix[0].length; col++) {
                String word = "";
                for (int pos = col; pos < WORD_SIZE + col; pos++) {
                    word += matrix[row][pos];
                }

                try {
                    coincidences.put(word, coincidences.get(word) + 1);
                } catch (NullPointerException e) {
                    coincidences.put(word, 1);
                }
                //System.out.println(coincidences);
            }
        }

        //Columns
        for (int col = 0; col < matrix[0].length; col++) {
            for (int row = 0; row + WORD_SIZE <= matrix.length; row++) {
                String word = "";
                for (int pos = row; pos < WORD_SIZE + row; pos++) {
                    word += matrix[pos][col];
                }

                try {
                    coincidences.put(word, coincidences.get(word) + 1);
                } catch (NullPointerException e) {
                    coincidences.put(word, 1);
                }
                //System.out.println(coincidences);
            }
        }

        //Diagonal LTR
        for (int row = 0; row <= matrix.length - WORD_SIZE; row++) {
            for (int col = 0; col <= matrix[0].length - WORD_SIZE; col++) {
                int rowAux = row;
                int colAux = col;
                String word = "";
                for (int i = 0; i < WORD_SIZE; i++) {
                    word += matrix[rowAux][colAux];
                    rowAux += 1;
                    colAux += 1;
                }

                try {
                    coincidences.put(word, coincidences.get(word) + 1);
                } catch (NullPointerException e) {
                    coincidences.put(word, 1);
                }
                //System.out.println(coincidences);
            }
        }

        //Diagonal RTL
        for (int row = 0; row <= matrix.length - WORD_SIZE; row++) {
            for (int col = matrix[0].length - 1; col >= WORD_SIZE - 1; col--) {
                int rowAux = row;
                int colAux = col;
                String word = "";

                for (int i = 0; i < WORD_SIZE; i++) {
                    word += matrix[rowAux][colAux];
                    rowAux += 1;
                    colAux -= 1;
                }

                try {
                    coincidences.put(word, coincidences.get(word) + 1);
                } catch (NullPointerException e) {
                    coincidences.put(word, 1);
                }

                //System.out.println(coincidences);
            }
        }
        
        //Validation
        return validate(coincidences) > 1;
    }

    public static void printMatrix(String[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                System.out.print(matrix[row][col] + " ");
            }
            System.out.println();
        }
    }

    public static int validate(Map<String, Integer> coincidences) {
        int sum = 0;
        sum = coincidences.keySet().stream().filter((key) -> (PATTERN.matcher(key).matches())).map((key) -> {
            //System.out.println(key);
            return key;
        }).map((key) -> coincidences.get(key)).reduce(sum, Integer::sum);
        return sum;
    }

}
