package com.devPortes;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.security.autoconfigure.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(exclude = UserDetailsServiceAutoConfiguration.class)
public class DevPortesBackendApplication {

  public static void main(String[] args) {
    loadDotenvIfPresent();
    SpringApplication.run(DevPortesBackendApplication.class, args);
  }

  /**
   * Carga el archivo .env (si existe) como propiedades del sistema antes de arrancar.
   * Las variables de entorno reales siempre tienen prioridad sobre el .env.
   */
  private static void loadDotenvIfPresent() {
    try {
      Dotenv dotenv = Dotenv.configure().ignoreIfMissing().ignoreIfMalformed().load();
      dotenv.entries().forEach(entry -> {
        String key = entry.getKey();
        if (System.getenv(key) == null && System.getProperty(key) == null) {
          System.setProperty(key, entry.getValue());
        }
      });
    } catch (Exception e) {
      // Sin .env o ilegible: se usan únicamente las variables de entorno.
    }
  }
}
