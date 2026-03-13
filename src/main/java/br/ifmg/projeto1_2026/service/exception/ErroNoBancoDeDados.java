package br.ifmg.projeto1_2026.service.exception;

public class ErroNoBancoDeDados extends RuntimeException {
    public ErroNoBancoDeDados(String msg){
        super(msg);
    }
    public ErroNoBancoDeDados(){
    }
}
