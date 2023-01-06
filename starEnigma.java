//The war is at its peak, but you, young Padawan, can turn the tides with your programming skills. You are tasked to create a program to decrypt the messages of The Order and prevent the death of hundreds of lives.
//You will receive several messages, which are encrypted using the legendary star enigma. You should decrypt the messages following these rules:
//To properly decrypt a message, you should count all the letters [s, t, a, r] – case insensitive and remove the count from the current ASCII value of each symbol of the encrypted message.
//After decryption:
//Each message should have a planet name, population, attack type ('A', as an attack or 'D', as destruction), and soldier count.
//The planet's name starts after '@' and contains only letters from the Latin alphabet.
//The planet population starts after ':' and is an Integer;
//The attack type may be "A"(attack) or "D"(destruction) and must be surrounded by "!" (Exclamation mark).
//The soldier count starts after "->" and should be an Integer.
//The order in the message should be: planet name -> planet population -> attack type -> soldier count. Each part can be separated from the others by any character except: '@', '-', '!', ':' and '>'.
//Input / Constraints
//    • The first line holds n – the number of messages – integer in the range [1…100].
//    • On the next n lines, you will be receiving encrypted messages.
//Output
//After decrypting all messages, you should print the decrypted information in the following format:
//First print the attacked planets, then the destroyed planets.
//"Attacked planets: {attackedPlanetsCount}"
//"-> {planetName}"
//"Destroyed planets: {destroyedPlanetsCount}"
//"-> {planetName}"
//The planets should be ordered by name alphabetically.



//Example Input:
//2
//STCDoghudd4=63333$D$0A53333
//EHfsytsnhf?8555&I&2C9555SR

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class starEnigma {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //string builder where the word "star" will be held
        StringBuilder sb = new StringBuilder();

        //count of the found letters in order to sum up to "star"
        int countLettersFound = 0;

        //number of inputs
        int inputCount = Integer.parseInt(scanner.nextLine());

        //console input
        String input = scanner.nextLine();

        //true if decrypted successfully
        boolean decrypted = false;

        //list to hold and save all inputs to come
        List<String> inputHolder = new ArrayList<>();

        //decryption key holder
        List<Integer> decryptionKeyHolder = new ArrayList<>();
        int decryptionKeyCount = 0;

        String regex = "(?<letter>[a-zA-z])";

        inputHolder.add(input);
        for (int i = 1; i <= inputCount; i++) {

            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(input);

            while (matcher.find()) {
                String letter = matcher.group("letter");

                if (letter.toLowerCase().equals("s")) {
                    if (sb.toString().contains("s")) {
                        decryptionKeyCount++;
                        continue;
                    } else {
                        sb.append(letter.toLowerCase());
                        countLettersFound++;
                        decryptionKeyCount++;
                    }
                } else if (letter.toLowerCase().equals("t")) {
                    if (sb.toString().contains("t")) {
                        decryptionKeyCount++;
                        continue;
                    } else {
                        sb.append(letter.toLowerCase());
                        countLettersFound++;
                        decryptionKeyCount++;
                    }
                } else if (letter.toLowerCase().equals("a")) {
                    if (sb.toString().contains("a")) {
                        decryptionKeyCount++;
                        continue;
                    } else {
                        sb.append(letter.toLowerCase());
                        countLettersFound++;
                        decryptionKeyCount++;
                    }
                } else if (letter.toLowerCase().equals("r")) {
                    if (sb.toString().contains("r")) {
                        decryptionKeyCount++;
                        continue;
                    } else {
                        sb.append(letter.toLowerCase());
                        countLettersFound++;
                        decryptionKeyCount++;
                    }
                }

            }
            decryptionKeyHolder.add(decryptionKeyCount);
            decryptionKeyCount = 0;
            if (i == inputCount) {
                break;
            }
            input = scanner.nextLine();
            inputHolder.add(input);
        }
        String result = sb.toString();
        if (result.equals("star")) {
            decrypted = true;
        }


        if (decrypted) {

            for (int i = 0; i < inputHolder.size(); i++) {
                //decryptionkeyholder holds the keys(count to remove from the symbols ascii value;
                char[] toBeConverted = inputHolder.get(i).toCharArray();
                for (int j = 0; j < toBeConverted.length; j++) {
                    int ascii = toBeConverted[j];
                    ascii -= decryptionKeyHolder.get(i);
                    toBeConverted[j] = ((char) ascii);
                }
                inputHolder.remove(i);
                inputHolder.add(i, String.valueOf(toBeConverted));
            }

            //inputHolder contains all decrypted messages
            String groupSeparatorRegex = "@(?<planet>[A-z]\\w+):(?<population>[0-9]\\d+)!(?<action>[A|D])!->(?<soldierCount>\\d+)";
            Pattern group1 = Pattern.compile(groupSeparatorRegex);

            int attackedPlanets = 0;
            int destroyedPlanets = 0;
            List<String> attackedPlanetsNames = new ArrayList<>();
            List<String> destroyedPlanetsNames = new ArrayList<>();
            String planetName = "";
            String population = "";
            String action = "";
            String soldierCount = "";

            for (int i = 0; i < inputHolder.size(); i++) {

                Matcher groupMatcher = group1.matcher(inputHolder.get(i));
                if (groupMatcher.find()) {
                    planetName = groupMatcher.group("planet");
                    population = groupMatcher.group("population");
                    action = groupMatcher.group("action");
                    soldierCount = groupMatcher.group("soldierCount");
                }

                if (action.equals("A")){
                    attackedPlanets++;
                    attackedPlanetsNames.add(planetName);
                }else if (action.equals("D")){
                    destroyedPlanets++;
                    destroyedPlanetsNames.add(planetName);
                }
            }
            System.out.printf("Attacked planets: %d\n", attackedPlanets);

            for (int i = 0; i <attackedPlanetsNames.size(); i++) {
                System.out.printf("-> %s\n",attackedPlanetsNames.get(i));
            }
            System.out.printf("Destroyed planets: %d\n",destroyedPlanets);
            for (int i = 0; i < destroyedPlanetsNames.size(); i++) {
                System.out.printf("-> %s\n",destroyedPlanetsNames.get(i));
            }
        } else {
            System.out.println("Attack on hold");
        }
    }
}

