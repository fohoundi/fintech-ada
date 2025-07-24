package utils;

public final class DisplayUtil {

    private DisplayUtil(){

    }

    public static void display(String message){
        System.out.println(message);
    }

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

        //System.out.print("\r" + message + " ok \n");

        // Effacer la ligne avec des espaces
        String blank = " ".repeat(fullLength);
        System.out.print("\r" + blank + "\r"); // Efface et ramène au début
    }


}
