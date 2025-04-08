/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilidades;

import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author gaspa
 */
public class ContraseniaUtil {
    // Encripta la contraseña y devuelve el hash
    public static String encriptar(String contrasenia) {
        return BCrypt.hashpw(contrasenia, BCrypt.gensalt(12)); 
    }

    // Verifica una contraseña contra el hash almacenado
    public static boolean verificar(String contrasenia, String hashAlmacenado) {
        return BCrypt.checkpw(contrasenia, hashAlmacenado);
    }
}
