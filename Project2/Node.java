
public class Node {

    String name;
    Node next;
    Node previous;
    boolean isHub;
    boolean visited;
    int commonLetterCount; // ortak harflerin toplam sayısı

    public Node(String name) {
        this.name = name.toLowerCase();
        this.isHub = false;
        this.visited = false;
        this.commonLetterCount = 0;      // ortak harf sayısı başlangıvı sıfır
    }

    // mesajdaki harfleri isimdeki harflerle karşılaştır
    public void visitMessage(String message) {
        String lowerCaseName = name.toLowerCase();  //dosyadaki isimlerii küçük harfe çeviriyoruz
        int pointsGained = 0;

        System.out.println("------------");
        System.out.println(name + "  has been reached. \n");

        // mesajdaki her harfi kontrol edelim
        for (int i = 0; i < message.length(); i++) {
            char msgChar = message.charAt(i);  // mesajdaki her bir harfi al


            for (int j = 0; j < lowerCaseName.length(); j++) {
                char nameChar = lowerCaseName.charAt(j);  // isimdeki her bir harfi almak içimnn

                if (msgChar == nameChar) {
                    commonLetterCount++;
                    pointsGained++;
                    System.out.println(name + " has matched the letter: " + msgChar);
                    break;
                }
            }
        }

        // Eğernharf eşleştiyse kaç puan kazanıldığını printle
        if (pointsGained > 0) {
            System.out.println(name + " has gained " + pointsGained + " points for having matching letters.\n");
        }
    }

    public int getVisitedLetterCount() {
        return commonLetterCount;
    }

    @Override
    public String toString() {
        if (isHub) {
            return "✷" + name + "✷";
        } else {
            return name;
        }
    }

}
