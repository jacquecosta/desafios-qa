package runners;

import static org.hamcrest.Matchers.equalTo;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/cenarios_conhecidos.feature",
				 plugin = {"pretty", "html:target/report-html","json:target/report-json"},
				 monochrome = true,
				 snippets = SnippetType.CAMELCASE,
				 dryRun = false, //dryRun valida se todos os metodos foram criados antes de executar
				 strict = false) //strict executa ou nao os testes indefinidos 

public class Runner {
	
	

}