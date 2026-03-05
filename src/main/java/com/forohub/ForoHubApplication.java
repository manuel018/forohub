package com.forohub;

import com.forohub.model.Topico;
import com.forohub.model.Usuario;
import com.forohub.repository.TopicoRepository;
import com.forohub.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class ForoHubApplication {
    @Bean
    CommandLineRunner initDatabase(UsuarioRepository usuarioRepo, TopicoRepository topicoRepo, PasswordEncoder passwordEncoder) {
        return args -> {

            Usuario u1 = new Usuario(null, passwordEncoder.encode("12345"), "juan@mail.com", "Juan Perez", "ROLE_USER");
            Usuario u2 = new Usuario(null, passwordEncoder.encode("12345"), "ana@mail.com", "Ana Lopez", "ROLE_USER");
            Usuario u3 = new Usuario(null, passwordEncoder.encode("12345"), "jorge@mail.com", "Jorge Manuel", "ROLE_USER");

            usuarioRepo.saveAll(List.of(u1, u2, u3));

            topicoRepo.saveAll(List.of(
                    new Topico(null, "Duda sobre Spring Boot", "No entiendo el @Autowired", "2023-10-27", "activo", u1.getNombre(), "Spring Boot"),
                    new Topico(null, "Error en JPA", "Me sale NullPointerException al guardar", "2023-10-28", "activo", u2.getNombre(), "Java Data"),
                    new Topico(null, "Duda sobre Records", "Son mejores que las clases?", "2023-10-29", "inactivo", u1.getNombre(), "Java 17"),
                    new Topico(null, "Configuración Maven", "Cómo agrego dependencias?", "2023-10-30", "activo", u3.getNombre(), "Herramientas"),
                    new Topico(null, "Validaciones Bean", "NotBlank no funciona en Records", "2023-10-31", "activo", u2.getNombre(), "Spring Validation")
            ));

            System.out.println("¡Data dummy cargada exitosamente!");
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(ForoHubApplication.class, args);
    }

}
