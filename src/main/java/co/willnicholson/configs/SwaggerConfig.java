package co.willnicholson.configs;




import io.swagger.annotations.SwaggerDefinition;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(info = @Info(
        description = "Query info from Will Nicholson's UFC database", version = ".8", title = "UFC API", contact = @Contact(name = "Will Nicholson", email = "William.Nicholson.dev@gmail.com")
))
public class SwaggerConfig {
}
