package tech.getarrays.employeemanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
@SpringBootApplication
@EnableJpaRepositories(basePackages = "tech.getarrays.employeemanager.repo")
@EntityScan(basePackages = "tech.getarrays.employeemanager.model")
public class EmployeemanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeemanagerApplication.class, args);
	}

	@Bean
	public CorsFilter corsFilter() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
				"Accept", "Authorization", "Origin, Accept", "X-Requested-With",
				"Access-Control-Request-Method", "Access-Control-Request-Headers"));
		corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",
				"Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}


}

/*tech.getarrays.employeemanager
├── config
│   ├── SecurityConfig.java           // Configuration de la sécurité
│   └── JwtUtil.java                  // Utilitaire pour générer et valider les JWT
├── model
│   ├── Department.java               // Modèle pour le département
│   ├── Employee.java                 // Modèle pour l'employé
│   ├── Project.java                  // Modèle pour le projet
│   ├── Task.java                     // Modèle pour la tâche
│   ├── LoginRequest.java             // Modèle pour la requête de login
│   └── JwtResponse.java              // Modèle pour la réponse contenant le JWT
├── repo
│   ├── DepartmentRepo.java           // Interface pour les opérations sur les départements
│   ├── EmployeeRepo.java             // Interface pour les opérations sur les employés
│   ├── ProjectRepo.java              // Interface pour les opérations sur les projets
│   └── TaskRepo.java                 // Interface pour les opérations sur les tâches
├── service
│   ├── DepartmentService.java        // Logique métier pour les départements
│   ├── EmployeeService.java          // Logique métier pour les employés
│   ├── ProjectService.java           // Logique métier pour les projets
│   └── TaskService.java              // Logique métier pour les tâches
├── resource
│   ├── DepartmentResource.java       // Endpoint pour les opérations sur les départements
│   ├── EmployeeResource.java         // Endpoint pour les opérations sur les employés
│   ├── ProjectResource.java          // Endpoint pour les opérations sur les projets
│   ├── TaskResource.java             // Endpoint pour les opérations sur les tâches
│   └── AuthController.java           // Endpoint pour l'authentification (login)
└── EmployeemanagerApplication.java   // Classe principale de l'application
*/