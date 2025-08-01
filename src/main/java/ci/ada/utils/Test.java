package ci.ada.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Test {
    private String matricule;
    private final List<Integer> generatedNum = new ArrayList<>();
    private static final Random random = new Random();

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public Test() {
        this.matricule = generateMatricule("ahi","hhsh ss yyuu");
    }

    public static void main(String[] args) {
        Test test = new Test();
        System.out.println(test.getMatricule());
    }

    private String generateMatricule(String nom, String prenom){
        String nomComplet = nom+" "+prenom;
        String[] noms = nomComplet.trim().split("\\s+");
        StringBuilder initiales = new StringBuilder();

        for (String name : noms) {
            if (!name.isEmpty()) {
                initiales.append(Character.toUpperCase(name.charAt(0)));
            }
        }

        int nb = generateNum();

        return "ADA-CH3-2025-" + initiales + "-" + String.format("%04d", nb);

    }
    private int generateNum (){
        int numero;
        do {
            numero = 1 + random.nextInt(1000); // 1 à 1000 inclus
        } while (generatedNum.contains(numero));

        generatedNum.add(numero);
        return numero;
    }

}
