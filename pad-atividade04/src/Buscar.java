public class Buscar implements Runnable {
	BuscaEmZip busca;
	public Buscar(BuscaEmZip Busca) {
		this.busca = Busca;
	}
	@Override
	public void run() {
		System.out.printf("Thread ativa: "+Thread.currentThread().getName()+"\n");
		try {
			busca.Busca(Thread.currentThread().getName());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
