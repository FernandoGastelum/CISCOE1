/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModuloReservas;

import java.awt.Color;

/**
 *
 * @author gaspa
 */
public class ColorUtil {

    public static Color parseColor(String colorStr) {
        if (colorStr == null) return Color.GRAY; // color por defecto

        colorStr = colorStr.trim().toLowerCase();

        // Colores por nombre
        switch (colorStr) {
            case "red": return Color.RED;
            case "blue": return Color.BLUE;
            case "green": return Color.GREEN;
            case "yellow": return Color.YELLOW;
            case "orange": return Color.ORANGE;
            case "black": return Color.BLACK;
            case "white": return Color.WHITE;
            case "gray": return Color.GRAY;
            case "pink": return Color.PINK;
            case "cyan": return Color.CYAN;
            case "magenta": return Color.MAGENTA;
        }

        // Si es un color hexadecimal (#RRGGBB)
        if (colorStr.matches("^#([A-Fa-f0-9]{6})$")) {
            return Color.decode(colorStr);
        }

        // Si es rgb(r,g,b)
        if (colorStr.startsWith("rgb")) {
            try {
                String valores = colorStr.substring(colorStr.indexOf("(") + 1, colorStr.indexOf(")"));
                String[] rgb = valores.split(",");
                int r = Integer.parseInt(rgb[0].trim());
                int g = Integer.parseInt(rgb[1].trim());
                int b = Integer.parseInt(rgb[2].trim());
                return new Color(r, g, b);
            } catch (Exception e) {
                return Color.GRAY;
            }
        }

        return Color.GRAY; // fallback
    }
}
