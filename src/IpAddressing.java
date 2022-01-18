import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.stream.Stream;

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

        return Long.parseLong(decToHex(firstAddress));

    }

    private static long convert32bitToInt(String octet) {
        return -1;
    }

    private static String decToHex(String[] address) {

        int[] arrayOfAddressParts = new int[4];
        for (int i = 0; i < 3; i++) {
            arrayOfAddressParts[i] = Integer.parseInt(address[i]);
        }

        ArrayList<String> resultingHex = new ArrayList<>();
        int indexBis = 0;
        String zero = "0";

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

//        resultingHex = Stream.of(bits)
//                .map(bits:: )
//                .collect(Collectors.joining(""));
//
//        indexBis++;

        }


        Stream.of(resultingHex)

                .forEach(System.out::print);



//        System.out.println(String.valueOf(resultingHex.toArray()));

        return null;
    }

}