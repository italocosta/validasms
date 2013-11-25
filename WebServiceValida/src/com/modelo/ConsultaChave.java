package com.modelo;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.cripty.Criptografia;

public class ConsultaChave {

	public String getChave(String cod_cliente, String cod_app){
		
		//ConsultaNoBD(codEmpresa,codSoft);
		
		Criptografia cript = new Criptografia();
		String data_validade = "01122013";
		String data_atualizacao = dataAtual();
		String protocoloSD = cod_cliente+cod_app+data_validade+data_atualizacao;
		String protocolo = geraCodVerificador(protocoloSD);

		System.out.println(cript.encriptar(protocolo));
		//System.out.println(cript.descriptar("aa10ea20icjakalbmbn`oapc30qbresataubv`waxc403050"));
		       //(S3)
		
		return cript.encriptar(protocolo);
		//return "aa10ea20jakalbmbn`oapc30qbresataubv`waxc402750";
	}
	
	private String dataAtual(){
		SimpleDateFormat formato = new SimpleDateFormat("ddMMyyyy");
		String data = formato.format(new Date());
		return data;
	}
	
	private String geraCodVerificador(String protocolo)
	{
	int soma = 0;
	for (int i = 0; i < protocolo.length(); i++)
	{
	    soma += Integer.parseInt(protocolo.substring(i, i+1));
	}

	return protocolo+soma;
	}
}
