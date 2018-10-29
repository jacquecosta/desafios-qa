package steps;

import org.junit.Assert;

import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class APIConsultaRelatorioErrosTestSteps {
	
	private Response response;
	private ValidatableResponse json;
	private RequestSpecification request;
    private String ENDPOINT_POST_RELATORIOS = "https://api-v2.idwall.co/relatorios";
    private String TOKEN = "9a5c457e-d004-46d2-ad0b-b3f015af0c58";
    private String TYPE = "application/json";

	@Dado("^A API de relatorio$")
	public void a_API_de_relatorio() {
		request = RestAssured.with().contentType(TYPE).header("Authorization", TOKEN).accept(TYPE);
		
	}

	@Quando("^A requisicao for realizada sem informar todos os parametros$")
	public void a_requisicao_for_realizada_sem_informar_todos_os_parametros() throws Throwable {
		response =  request
				   .body("{\r\n    \"matriz\": \"consultaPessoaDefault\",\r\n    \"parametros\": {\r\n        \"cpf_data_de_nascimento\": \"\",\r\n        \"cpf_nome\": \"\",\r\n        \"cpf_numero\": \"\"\r\n    }\r\n}")
				   .when()
				   .post(ENDPOINT_POST_RELATORIOS)
				   .then().contentType(ContentType.JSON).extract().response();
		

	}
	
	@Entao("^A API deve retornar um erro de \"([^\"]*)\"$")
	public void a_API_deve_retornar_um_erro_de(String error) throws Throwable {
		String erroRetornado = response.jsonPath().getString("error");
		Assert.assertEquals(error, erroRetornado);
	}

	@E("^uma mensagem de erro tratada \"([^\"]*)\"$")
	public void uma_mensagem_de_erro_tratada(String msg) throws Throwable {
		String msgRetornada = response.jsonPath().getString("message");
		Assert.assertEquals(msg, msgRetornada);
	}
	
	@E("^A API deve retornar um status code (\\d+)$")
	public void a_API_deve_retornar_um_status_code(int statusCode) throws Throwable {
		json = response.then().statusCode(statusCode);
	}
	
}