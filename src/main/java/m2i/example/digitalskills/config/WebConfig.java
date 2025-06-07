package m2i.example.digitalskills.config;

import m2i.example.digitalskills.interceptor.ClientTempInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Le chemin d'accès aux images depuis l'extérieur
        registry.addResourceHandler("/uploads/**")
                // Le répertoire réel où sont stockées les images
                .addResourceLocations("file:uploads/");
    }

    @Autowired
    private ClientTempInterceptor clientTempInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(clientTempInterceptor)
                .addPathPatterns("/**") // Appliquer à toutes les routes
                .excludePathPatterns(
                        "/api/client/enter", // Exclure l'endpoint de création pour éviter la duplication
                        "/static/**",        // Exclure les ressources statiques
                        "/css/**",
                        "/js/**",
                        "/images/**",
                        "/favicon.ico",
                        "/error"             // Exclure les pages d'erreur
                );
    }
}
