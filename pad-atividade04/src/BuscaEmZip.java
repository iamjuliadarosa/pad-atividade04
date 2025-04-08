import java.io.*;
import java.util.Enumeration;
import java.util.zip.*;
import java.util.concurrent.Semaphore;

public class BuscaEmZip{
	Semaphore sem = new Semaphore(2);
	String caminhoZip = "/home/juliadarosa/eclipse-workspace/pad-atividade04/arquivosNomes.zip";
	
	void Busca(String busca) throws InterruptedException {
		
			sem.acquire();
			System.out.printf("Acquired para a busca de: "+busca+"\n");
        try (ZipFile zipFile = new ZipFile(caminhoZip)) {
            Enumeration<? extends ZipEntry> entradas = zipFile.entries();

            while (entradas.hasMoreElements()) {
                ZipEntry entrada = entradas.nextElement();
                if (entrada.isDirectory()) continue;

                try (BufferedReader reader = new BufferedReader(
                        new InputStreamReader(zipFile.getInputStream(entrada)))) {
                	sem.acquire();
        			System.out.printf("Acquired para a busca de "+busca+" no arquivo "+entrada.getName()+"\n");
                	ProcuraEmArquivo(reader, entrada.getName(), busca);
        			System.out.printf("Released a busca de "+busca+" no arquivo "+entrada.getName()+"\n");
                	Thread.sleep(100);
            		sem.release();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
		Thread.sleep(100);
		sem.release();
	}
	private void ProcuraEmArquivo(BufferedReader reader, String name, String busca) throws IOException {
		String linha;
        int numeroLinha = 0;

        while ((linha = reader.readLine()) != null) {
            numeroLinha++;
            if (linha.toLowerCase().startsWith(busca.toLowerCase())) {
                System.out.printf(name+" - linha %d: %s%n", numeroLinha, linha);
            }
        }
	}
}
