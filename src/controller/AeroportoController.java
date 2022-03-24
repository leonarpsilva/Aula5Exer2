package controller;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class AeroportoController extends Thread {
	public static Semaphore pistaSul = new Semaphore(1);
	public static Semaphore pistaNorte = new Semaphore(1);
	int pistaId;
	
	int _aviaoId;
	public AeroportoController(int aviaoId) {
		this._aviaoId = aviaoId;
	}
	
	public void run() {
		try {
			this.manobrar();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void manobrar() throws InterruptedException {
		Random random = new Random();
		int tempoManobra = random.nextInt((7000 - 3000)+1)+3000;
		
		System.out.println(String.format("Avião %d saiu do portão de embarque", this._aviaoId ));
		Thread.sleep(tempoManobra);
		System.out.println(String.format("Avião %d manobrou", this._aviaoId ));
		
		this.taxiar();
	}
	
	public void taxiar() throws InterruptedException {
		Random random = new Random();
		int tempoTaxiar = random.nextInt((10000 - 5000)+1)+5000;
		
		System.out.println(String.format("Avião %d entrou na pista de rolamento", this._aviaoId ));
		Thread.sleep(tempoTaxiar);
		System.out.println(String.format("Avião %d percorreu a pista de rolamento", this._aviaoId ));
		
		Random random2 = new Random();
		this.pistaId = random2.nextInt((4 - 1)+1)+1;
		
		if((this.pistaId % 2) == 0) {
			AeroportoController.pistaNorte.acquire();
			this.decolar("norte");
		} else {
			AeroportoController.pistaSul.acquire();
			this.decolar("sul");
		}
	}

	public void decolar(String pistaNome) throws InterruptedException {
		Random random = new Random();
		int tempoDecolagem = random.nextInt((4000 - 1000)+1)+1000;
		
		System.out.println(String.format("Avião %d iniciou a decolagem pela pista %s", this._aviaoId, pistaNome ));
		Thread.sleep(tempoDecolagem);
		System.out.println(String.format("Avião %d decolou pela pista %s", this._aviaoId, pistaNome ));
		
		this.afastar();
	}

	private void afastar() throws InterruptedException {
		Random random = new Random();
		int tempoAfastamento = random.nextInt((8000 - 3000)+1)+3000;
		
		Thread.sleep(tempoAfastamento);
		System.out.println(String.format("Avião %d se afastou da pista de decolagem", this._aviaoId));
		
		AeroportoController.pistaNorte.release();
		AeroportoController.pistaSul.release();
	}
	
}
