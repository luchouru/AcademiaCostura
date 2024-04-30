package sistema.logica.monitor;

public class Monitor {
	private int cantLectores;
	private boolean escribiendo;
	private final int maxLectores = 3;

	public Monitor() {
		this.cantLectores = 0;
		this.escribiendo = false;
	}

	public synchronized void comienzoEscritura() {
		while (this.escribiendo) {
			try {
				wait();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				e.printStackTrace();
			}
		}
		this.escribiendo = true;
	}

	public synchronized void comienzoLectura() {

		while (this.escribiendo || this.cantLectores >= maxLectores) {
			try {
				wait();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				e.printStackTrace();
			}
		}
		this.cantLectores++;
	}

	public synchronized void terminoEscritura() {
		this.escribiendo = false;
		notifyAll();
	}

	public synchronized void terminoLectura() {
		this.cantLectores--;
		notifyAll();
	}
}
