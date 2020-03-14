package it.polito.tdp.indovinanumero.model;

import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.Set;

public class Model {
	private final int NMAX = 100;
	private final int TMAX = 8;
	private int segreto;
	private int tentativiFatti;
	private boolean inGioco;
	
	private Set<Integer> tentativi;
	public Model()
	{
		this.inGioco=false;
		this.tentativiFatti=0;
	}
	
	public void nuovaPartita() {
		//gestione dell'inizio di una nuova partita - Logica del gioco
    	this.segreto = (int)(Math.random() * NMAX) + 1;
    	this.tentativiFatti = 0;
    	this.inGioco = true; 
    	this.tentativi=new HashSet<Integer>();
    	
	}
	
	public int tentativo(int tentativo) {
		// controllo se la partita è in corso
		if(!inGioco)
		{
			throw new IllegalStateException("Partita già terminata");
		}
		
		//controllo l'input
		if(!tentativoValido(tentativo))
		{
			throw new InvalidParameterException("Devi inserire che non hai già messo un numero tra 1 e "+NMAX);
		}
			
		this.tentativiFatti ++;
		this.tentativi.add(tentativo);
		if(tentativiFatti == TMAX) {
    		//Ho esaurito i tentativi -> HO PERSO
			this.inGioco = false;
    	}
    	
		if(tentativo == this.segreto) {
    		//HO INDOVINATO!
    		this.inGioco = false;
    		return 0;
    	}
    	
    	if(tentativo < this.segreto) 
    		return -1;
    	else return 1;
	}
	
	private boolean tentativoValido(int tentativo)
	{
		if(tentativo<1 || tentativo>NMAX)
			return false;
		else  if(this.tentativi.contains(tentativo))
			return false;
		else return true;
	}

	public int getSegreto() {
		return segreto;
	}

	public int getTentativiFatti() {
		return tentativiFatti;
	}

	public int getNMAX() {
		return NMAX;
	}

	public int getTMAX() {
		return TMAX;
	}

	public boolean isInGioco() {
		return inGioco;
	}
	
	
}
