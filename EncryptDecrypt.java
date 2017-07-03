import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;


public class EncryptDecrypt {

    public static void main(String[] args) {
		/*These two variables separate the messages from the text file to Encrypt into a
		Separate variable and the message to Decrypt into a separate variable.*/
        String toEncrypt, toDecrypt;
        //This reads from the file, one line at a time, and then separates the messages.
        BufferedReader reader = null;

        try {
            File file = new File("Cryptography Text File");
            reader = new BufferedReader(new FileReader(file));

            String line;
            while ((line = reader.readLine()) != null) {
                toEncrypt = line;
                Encrypt(toEncrypt);
                line = reader.readLine();
                toDecrypt = line;
                Decrypt(toDecrypt);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void Encrypt(String str){
        //Created an Output file.
        String file = "Encrypted.txt";
        BufferedReader reader = null;
        //Created an 2d array to store the message, character by character.
        char[][] data = new char[6][5];
        char[] letters = str.toCharArray();
        //This For loop fills the 2d array by filling one character at a time diagonally.
        for(int i = 0; i < data.length; i++){
            for (int j = 0; j < 5; j++){
                data[i][j] = letters[i*5 + j];
            }
        }
        //This prints to the output file.
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write("The Encrypted Message is:\n");
            for(int i = 0; i < 5; i++){
                for(int j = 0; j < 6; j++){
                    bw.write(data[j][i] + " ");
                }
            }
            System.out.println("Done");
        } catch(IOException e){
            e.printStackTrace();
        }
    }



    public static void Decrypt(String str){
        //Created a new output file.
        String file1 = "Decrypted.txt";
        BufferedReader reader = null;
        //Created a new array to store the message by characters.
        int row = str.length()/6;
        //Calculated the number of rows needed.
        char[][] data1 = new char[row][6];
        char[] letters = str.toCharArray();
		/*
		 This removes the extra "Z", that is placed at the end of the Decrypted message with a "*" that the message is supposed to end with.
		 */
        for(int i = str.length()-1; i > 0;i--){
            if (letters[i] == 'Z')
                letters[i] = '*';
        }
        //This fills the 2d array one character at a time, diagonally.
        for(int i = 0; i < data1.length; i++){
            for(int j = 0; j < 6; j++){
                data1[i][j] = letters[j*6 + i];
            }

        }
        //This prints to the output file, the Decrypted message.
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file1))) {
            bw.write("The Decrypted Message is:\n");
            for(int i = 0; i < 6; i++){
                for(int j = 0; j < 6; j++){
                    bw.write(data1[i][j] + " ");
                }
            }
            System.out.println("Done");
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}