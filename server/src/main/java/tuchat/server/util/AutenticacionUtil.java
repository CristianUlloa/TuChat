package tuchat.server.util;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Base64;
import java.util.Date;

public class AutenticacionUtil {

    public static String generarCodigoValidacion(int longitud) {
        // Usamos SecureRandom para mayor seguridad en la generación de códigos
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[longitud];
        random.nextBytes(bytes);
        
        // Usamos Base64 para convertir los bytes en una cadena
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes).substring(0, longitud);
    }
    
    public static Date generarFechaVencimiento(int plusHours) {
    	return Date.from(LocalDateTime.now().plusHours(plusHours).toInstant(ZoneOffset.UTC));
    }


}
