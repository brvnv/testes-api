package testes-api;

import org.junit.Assert;
import org.junit.Test;

import common.LeitorJson;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ConsultaDeCep {
	public ConsultaDeCep() {
		RestAssured.baseURI="https://viacep.com.br/";
		RestAssured.basePath="/ws";
	}
	
	LeitorJson leitorJson=new LeitorJson();
	String jsonFile="ConsultaDeCep";
	
	@Test
	public void consultaCepValido() {
		System.out.println("Consultando cep válido...");
		RequestSpecification req=RestAssured.given();
		Response response=req.get("/" + leitorJson.getFromJson(jsonFile, "valido") + "/json");
		response.getBody();
		JsonPath evaluator = response.jsonPath();
		String cep=evaluator.get("cep");
		String logradouro=evaluator.get("logradouro");
		String complemento=evaluator.get("complemento");
		String bairro=evaluator.get("bairro");
		String localidade=evaluator.get("localidade");
		String uf=evaluator.get("uf");
		String ibge=evaluator.get("ibge");
		System.out.println(cep + "\n" + logradouro + "\n" + complemento + "\n" + bairro + "\n" + localidade + "\n" + uf + "\n" + ibge + ".");
	}
	
	@Test
	public void consultaCepInvalido() {
		System.out.println("Consultando cep inválido...");
		RequestSpecification req=RestAssured.given();
		Response response=req.get("/" + leitorJson.getFromJson(jsonFile, "invalido") + "/json");
		Assert.assertEquals("Cep é válido", 400, response.getStatusCode());
		System.out.println("Cep inválido. Código 400 retornado");
	}
	
	@Test
	public void consultarCepInexistente() {
		System.out.println("Consultando cep inexistente...");
		RequestSpecification req=RestAssured.given();
		Response response=req.get("/" + leitorJson.getFromJson(jsonFile, "inexistente") + "/json");
		response.getBody();
		JsonPath evaluator = response.jsonPath();
		boolean error=evaluator.get("erro");
		Assert.assertTrue("Cep existe.", error);
		System.out.println("Cep inexistente.");
	}
}
