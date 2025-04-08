
public class Main_BuscaZip {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BuscaEmZip Busca = new BuscaEmZip();

		Thread BuscaGlauco = new Thread(new Buscar(Busca),"glauco");

		Thread BuscaJulia= new Thread(new Buscar(Busca),"juliana");

		Thread BuscaEli = new Thread(new Buscar(Busca),"elias");

		Thread BuscaArt = new Thread(new Buscar(Busca),"arthur");
		
		BuscaGlauco.start();
		BuscaEli.start();
		BuscaJulia.start();
		BuscaArt.start();
	}

}
