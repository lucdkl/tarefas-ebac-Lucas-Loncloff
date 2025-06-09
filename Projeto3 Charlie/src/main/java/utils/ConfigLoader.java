package utils;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private static final Properties props = new Properties();

    static {
        try (InputStream input = ConfigLoader.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("Arquivo config.properties não encontrado no classpath!");
            }
        } catch (IOException e) {
            throw new RuntimeException("Arquivo config.properties não encontrado na raiz!", e);
        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }
}