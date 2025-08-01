package ci.ada.utils;

public final class DisplayUtil {

    private DisplayUtil(){

    }

    public static void display(String message){
        System.out.println(message);
    }

    // Animation de chargement
    public static void loading(String message) {
        char[] spinner = {'|', '/', '-', '\\'};
        int index = 0;
        int durationSeconds = 3;
        long end = System.currentTimeMillis() + durationSeconds * 1000;
        int fullLength = message.length() + 2; // Pour le symbole + espace

        while (System.currentTimeMillis() < end) {
            System.out.print("\r" + message + " " + spinner[index]);
            index = (index + 1) % spinner.length;

            try {
                Thread.sleep(150); // vitesse de rotation
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Effacer la ligne avec des espaces
        String blank = " ".repeat(fullLength);
        System.out.print("\r" + blank + "\r"); // Efface et ramène au début
    }


    public static void successMsg(){
        DisplayUtil.display("Compte cree avec succes!!!");
        DisplayUtil.display("///////////////////////////////////////");
    }
}
