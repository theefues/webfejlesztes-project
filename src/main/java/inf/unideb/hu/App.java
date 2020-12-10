package inf.unideb.hu;

import inf.unideb.hu.model.Inventory;
import inf.unideb.hu.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


/**
 * Run -> Configuration -> Environment Variables
 *  - DB_HOST localhost /127.0.0.1
 *  - DB_PORT 3306
 *  - DB_NAME sakila
 *  - DB_USER root
 *  - DB_PASS secret
 *
 *  Example configuration
 * @see {project.basedir}/src/main/resources/sql/sakila.sh
 */
@Slf4j
@SpringBootApplication
public class App
{


    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
    }


}
