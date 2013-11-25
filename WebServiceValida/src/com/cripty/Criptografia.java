/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cripty;

import javax.swing.JOptionPane;

/**
 *
 * @author italo
 */
public class Criptografia {
    private String aux = "";
    private String pos1 = "0000";
    private String pos2 = "0000";
    private String pos3 = "00000000";
    private String pos4 = "00000000";
    private String pos5 = "000";
    private String protocolo = "000000000000000000000000";
    //private String chaveP = "";
    
    public String descriptar(String chave)
    {
        try{
        for (int i = 0; i < chave.length(); i++)
        {
            aux = aux + chave.charAt(i);
            
            if((chave.charAt(i) == '0' && pos4.equalsIgnoreCase("00000000")) || i == chave.length()-1)
            {
                switch(aux.charAt(aux.length()-2))
                        {
                            case '1': pos1 = aux.substring(0, aux.length()-2); break;
                            case '2': pos2 = aux.substring(0, aux.length()-2); break;
                            case '3': pos3 = aux.substring(0, aux.length()-2); break;
                            case '4': pos4 = aux.substring(0, aux.length()-2); break;
                            case '5': pos5 = aux.substring(0, aux.length()-2); break;
                            
                        }
                aux = "";
            }

        }
        }catch(Exception erro)
        {
            System.out.println("Chave de validação do Banco está inválida, entre em contato com o suporte.");
        }
    
        aux = pos1+pos2+pos3+pos4;
        int cont = 0;
        //System.out.println(aux);
        for (int i= 0 ; i< aux.length(); i++)
        {
            cont ++;
            if(cont % 2 == 0)
            {
                protocolo = substituirValor(protocolo, aux.charAt(i)-96,aux.charAt(i-1)-97);
                cont = 0;
            }
        }
        try {
            verificaIntegridade(protocolo+pos5);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
        return protocolo+pos5;
    }
    
    private String substituirValor(String protocolo, int valor, int posicao)
    {
        String protocoloAlterado="";
        for(int i = 0; i < protocolo.length(); i++)
        {
            if( posicao == i)
            {
                protocoloAlterado += valor;
            }else
            {
                protocoloAlterado += protocolo.charAt(i);
            }
        }
        
        return protocoloAlterado;
    }
    
    private void verificaIntegridade(String proto) throws Exception
    {
        int soma = 0;
        for(int i = 0; i < 24; i++)
        {
            //System.out.println(proto.substring(i, i+1));
            soma+= Integer.parseInt(proto.substring(i,i+1));
        }
                
        //System.out.println(soma);
        
        if( soma != Integer.parseInt(proto.substring(24, proto.length())))
        {
            throw new Exception("Chave inválida !");
        }
        
    }
    
    public String encriptar(String proto)
    {
        String chave = "";
        for(int i = 0; i < 24 ; i++)
        {
           
            
           if(proto.charAt(i) != '0' || i > 8)
           {
               char caracterePos = (char) (i+97);
               char caractere = (char) (Integer.parseInt(""+proto.charAt(i))+96);
               //System.out.println(caracterePos+""+caractere);
               chave += caracterePos+""+caractere; 
           }
           
           if(i == 3 || i == 7 || i == 15 || i == 23)
           {
               switch(i)
               {
                   case 3 : chave += "10";break;
                   case 7 : chave += "20";break;
                   case 15 : chave += "30";break;
                   case 23 : chave += "40"+proto.substring(24, proto.length())+"50";break;
                  
               }
                
           }
           //System.out.println(chave);
        }
        try {
            verificaIntegridade(proto);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return chave;
    }
    
}


/*  protocolo: 10001000311020132610201328
 *  chave: aa10ea20icjakal`mbn`oapc30qbrfsat`ubv`waxc402850
 */