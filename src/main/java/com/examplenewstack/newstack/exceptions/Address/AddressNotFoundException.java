package com.examplenewstack.newstack.exceptions.Address;



public class AddressNotFoundException extends RuntimeException{

    public AddressNotFoundException(){super("Erro: Endereço esta vazio");}
    public AddressNotFoundException(String message){super(message);}

}
