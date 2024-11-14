
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class DoubleLinkedList {

    Node head;
    Node tail;
    LinkedList<Node> students;  // öğrencileri depolamak için LinkedList
    Random random;

    public DoubleLinkedList() {
        students = new LinkedList<>();
        random = new Random();
    }

    public void addStudent(String name) {
        Node newNode = new Node(name);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.previous = tail;
            tail = newNode;
        }
        students.add(newNode);  // öğrenciyi LinkedList'e ekle
    }

    // Hub öğrencileri işaretle
    public void markHubs(int hubCount) {
        for (int i = 0; i < hubCount; i++) {
            Node hub;
            do {
                hub = students.get(random.nextInt(students.size()));  // rastgele bir öğrenci seç
            } while (hub.isHub);  // hub olmadığı sürece devam etsin
            hub.isHub = true;  // hub olarak işaretle
        }
    }

    // Tüm öğrencileri listele
    public void printList() {
        Node current = head;
        System.out.println("\n -------------------------------------------------- \n");

        while (current != null) {
            System.out.print(current);
            if (current.next != null) {
                System.out.print(" <---> ");
            }
            current = current.next;
        }

        System.out.println("\n\n -------------------------------------------------- \n");
    }

    public void startMessageTransfer(int hubCount, String initialMessage) {
        markHubs(hubCount);  // hubb öğrencileri işaretle

        // rastgele bir öğrenci seç  mesaj yönünü belirle
        Node current = students.get(random.nextInt(students.size()));
        boolean movingRight = random.nextBoolean();
        String currentMessage = initialMessage;
        if (movingRight) {
            System.out.println("Initial message direction: right ➞➞➞");
        } else {
            System.out.println("Initial message direction: 🠔🠔🠔 left");
        }
        System.out.println("Starting with student: " + current.name);

        printList();

        current.visitMessage(currentMessage);  // ilk mesajı ilet
        current.visited = true;  // ziyaret edildi olarak işaretle

        Scanner scanner = new Scanner(System.in);

        while (!allVisited()) {

            if (current.isHub) {
                System.out.println("Reached hub student: " + current.name);
                System.out.print("Enter a new message: ");
                currentMessage = scanner.nextLine();  // yeni mesaj al
                current.visitMessage(currentMessage);  // yeni mesajı öğrenciye ilet
                movingRight = !movingRight;  // yönü değiştir
                System.out.println("Direction changed to: " + (movingRight ? " right ➞➞➞" : " 🠔🠔🠔 left "));
            }

            int steps = random.nextInt(5) + 1;
            System.out.println("Moving " + steps + " steps from " + current.name + ". " + " Moving... " + (movingRight ? "right ➞➞➞" : "🠔🠔🠔 left"));

            // eğer hareket sırasında liste sonuna ulaşıldıysa yönü değiştir ve kalan adımlarla devam et
            while (steps > 0) {
                if ((movingRight && current.next == null) || (!movingRight && current.previous == null)) {
                    System.out.println("Reached the end, changing direction.");
                    movingRight = !movingRight;  // Yönü değiştir
                    System.out.println("New direction: " + (movingRight ? "right ➞➞➞" : "🠔🠔🠔 left"));
                }

                // bir  adım hareket et ve adım sayısını azalt
                current = move(current, movingRight, 1);
                steps--;
            }

            // mesajı ilet
            if (current != null) {
                current.visited = true;
                current.visitMessage(currentMessage);  // güncellenen mesajı öğrenciye ilet
            }

            printList();
        }

        System.out.println("All students have been visited.");
        printVisitedLetterCounts();
    }

    public Node move(Node current, boolean movingRight, int steps) {
        for (int i = 0; i < steps; i++) {
            if (movingRight) {
                if (current.next != null) {
                    current = current.next;
                }
            } else {
                if (current.previous != null) {
                    current = current.previous;
                }
            }
        }
        return current;
    }

    private boolean allVisited() {
        for (int i = 0; i < students.size(); i++) {
            if (!students.get(i).visited) {
                return false;
            }
        }
        return true;  // herkes ziyaret edilmişse true returnla
    }

    // ziyaret edilen öğrencilerin harf sayısını yazdır
    private void printVisitedLetterCounts() {
        for (int i = 0; i < students.size(); i++) {
            Node student = students.get(i);
            System.out.println(student.name + " visited letters count: " + student.getVisitedLetterCount());
        }
    }

}
