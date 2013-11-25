package com.cripty;

import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * protocolo : aaaabbbbccccccccddddddddeee
 * a = codigo da empresa;
 * b = codigo do programa;
 * c = data de validade
 * d = ultima verificacao pelo software via web service
 * e = codigo de verificacao
 * 
 * valores transformador:
 *  10 = j ; 14 = n ; 18 = r ; 22 = v ; 
 *  11 = k ; 15 = o ; 19 = s ; 23 = w ;
 *  12 = l ; 16 = p ; 20 = t ; 24 = x ;
 *  13 = m ; 17 = q ; 21 = u ; `  = 0 ;
 * 
 * ex.:
 *  protocolo: 10001000311020132610201328
 *  chave: aa10ea20icjakal`mbn`oapc30qbrfsat`ubv`waxc402850
 * 
 *  solução:
 *   vetor codigo empresa : {1,a,4,0
 * 
 * 
 */

public class AppCripty {
public static void main(String [] Args){

Criptografia cript = new Criptografia();
String cod_cliente = "1000";
String cod_app = "1000";
String data_validade = "01122013";
String data_atualizacao = "25112013";
String protocoloSD = cod_cliente+cod_app+data_validade+data_atualizacao;
String protocolo = geraCodVerificador(protocoloSD);

System.out.println(cript.encriptar(protocolo));
System.out.println(cript.descriptar("aa10ea20icjakalbmbn`oapc30qbresataubv`waxc403050"));
       //(S3)
//System.out.println(dataAtual());

 
}

private static String dataAtual(){
	SimpleDateFormat formato = new SimpleDateFormat("ddMMyyyy");
	String data = formato.format(new Date());
	return data;
}

private static String geraCodVerificador(String protocolo)
{
int soma = 0;
for (int i = 0; i < protocolo.length(); i++)
{
    soma += Integer.parseInt(protocolo.substring(i, i+1));
}

return protocolo+soma;
}


}
