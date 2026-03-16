/**
 * 
 */
package br.com.vkl.vendas.onlineconfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author rodrigo.pires
 *
 */
@Configuration
@EnableMongoRepositories(basePackages = "br.com.vkl.vendas.online.repository")
public class MongoConfig {

}
