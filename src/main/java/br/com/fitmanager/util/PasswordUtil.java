
package br.com.fitmanager.util;

import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PasswordUtil {

    private static final int ITERATIONS = 210_000;
    private static final int KEY_LENGTH = 256;
    private static final int SALT_LENGTH = 16;

    private static final SecureRandom RANDOM = new SecureRandom();

    public static String gerarSalt() {

        byte[] salt = new byte[SALT_LENGTH];
        RANDOM.nextBytes(salt);

        return Base64.getEncoder().encodeToString(salt);
    }

    public static String gerarHash(String senha, String salt)
            throws GeneralSecurityException {

        byte[] saltBytes = Base64.getDecoder().decode(salt);

        PBEKeySpec spec = new PBEKeySpec(
            senha.toCharArray(),
            saltBytes,
            ITERATIONS,
            KEY_LENGTH
        );

        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance(
                "PBKDF2WithHmacSHA256"
            );

            byte[] hash = factory.generateSecret(spec).getEncoded();

            return Base64.getEncoder().encodeToString(hash);

        } finally {
            spec.clearPassword();
        }
    }

    public static boolean verificarSenha(
            String senha,
            String salt,
            String hashArmazenado
    ) throws GeneralSecurityException {

        String hashCalculado = gerarHash(senha, salt);

        return java.security.MessageDigest.isEqual(
            Base64.getDecoder().decode(hashCalculado),
            Base64.getDecoder().decode(hashArmazenado)
        );
    }
}
