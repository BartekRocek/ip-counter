import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class IpAddressing {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String start = scanner.next();
        String end = scanner.next();

        System.out.println(ipsBetween(start, end));

    }

    private static long ipsBetween(String start, String end) {
        String[] firstAddress = start.split("[.]");
        String[] secondAddress = end.split("[.]");

        return Long.parseLong(decToHex(secondAddress)) - Long.parseLong(decToHex(firstAddress));
    }

    private static long convertHexToDec(char hex) {

        return switch (hex) {
            case '0' -> 0;
            case '1' -> 1;
            case '2' -> 2;
            case '3' -> 3;
            case '4' -> 4;
            case '6' -> 6;
            case '5' -> 5;
            case '7' -> 7;
            case '8' -> 8;
            case '9' -> 9;
            case 'A' -> 10;
            case 'B' -> 11;
            case 'C' -> 12;
            case 'D' -> 13;
            case 'E' -> 14;
            case 'F' -> 15;
            default -> throw new IllegalArgumentException("Incorrect value!");
        };
    }

    private static String decToHex(String[] address) {

        ArrayList<String> resultingHex = new ArrayList<>();
        StringBuilder stringOfHex = new StringBuilder();
        String zero = "0";
        int indexBis = 0;
        long result = 0L;

        int[] arrayOfAddressParts = new int[4];
        for (int i = 0; i < 4; i++) {
            arrayOfAddressParts[i] = Integer.parseInt(address[i]);
        }

        for (int item : arrayOfAddressParts) {
            ArrayList<String> bits = new ArrayList<>();
            int index = 0;

            do {
                switch (item % 16) {
                    case 10 ->  bits.add(index, "A");
                    case 11 ->  bits.add(index, "B");
                    case 12 ->  bits.add(index, "C");
                    case 13 ->  bits.add(index, "D");
                    case 14 ->  bits.add(index, "E");
                    case 15 ->  bits.add(index, "F");
                    default ->  bits.add(index, Integer.toString(item % 16));
                }
                 index++;
                item /= 16;
            } while (item > 0);

            Collections.reverse(bits);

            resultingHex.add(indexBis, String.join("", bits));
            if (resultingHex.get(indexBis).length() == 1) {
                String singleHexDigit = resultingHex.get(indexBis);
                resultingHex.set(indexBis, zero.concat(singleHexDigit));
            }
            indexBis++;
        }

        for (String hex : resultingHex) {
            stringOfHex.append(hex);
        }

        for (int i = stringOfHex.length() - 1,  k = 0; i >= 0 && k < stringOfHex.length(); i--, k++) {
            result += convertHexToDec(stringOfHex.charAt(k)) * (long)Math.pow(16, i);
        }
        return Long.toString(result);
    }
}