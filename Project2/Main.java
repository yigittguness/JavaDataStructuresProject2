
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        DoubleLinkedList studentList = new DoubleLinkedList();

        // / dosyayı okuyup linkedleste eklemece
        LinkedList<String> allNames = new LinkedList<>();
        File file = new File("src/names.txt");
        Scanner fileScanner = new Scanner(file);

        while (fileScanner.hasNextLine()) {
            allNames.add(fileScanner.nextLine());
        }
        fileScanner.close();

        // random indexi tutsun
        LinkedList<Integer> selectedIndices = new LinkedList<>();
        Random random = new Random();

        while (selectedIndices.size() < 30) {
            int randomIndex = random.nextInt(allNames.size());


            if (!selectedIndices.contains(randomIndex)) {
                selectedIndices.add(randomIndex);
                String name = allNames.get(randomIndex);
                studentList.addStudent(name);
            }
        }

        // hub sayısını ve başlangıç mesajını kullanıcıdan almakv için
        Scanner inputScanner = new Scanner(System.in);
        System.out.println("Enter the number of hub students:");
        int hubCount = inputScanner.nextInt();
        inputScanner.nextLine();
        System.out.println("Enter the initial message:");
        String initialMessage = inputScanner.nextLine();

        studentList.startMessageTransfer(hubCount, initialMessage);
    }
}
