package ci.ada.utils;

import java.math.BigDecimal;
import java.text.NumberFormat;

public class InputUtils {

    public static String format_FCFA(BigDecimal montant) {
        NumberFormat formatter = NumberFormat.getNumberInstance(); // Locale par défaut (ex: fr_FR)
        return formatter.format(montant) + " F CFA";
    }

}
