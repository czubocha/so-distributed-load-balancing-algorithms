import java.util.ArrayList;

public class Procesor implements Cloneable{

    int minObciazenie;
    int maxObciazenie;
    int aktualneObciazenie;

    ArrayList<Proces> listaProcesow;
    ArrayList<Proces> listaProcesow2;
    ArrayList<Proces> listaProcesow3;
    ArrayList<Proces> listaOczekujacychProcesow;

    Procesor(int minObciazenie, int maxObciazenie) throws CloneNotSupportedException {
	listaProcesow = new ArrayList<Proces>();
	listaProcesow2 = new ArrayList<Proces>();
	listaProcesow3 = new ArrayList<Proces>();
	listaOczekujacychProcesow = new ArrayList<Proces>();
	this.minObciazenie=minObciazenie;
	this.maxObciazenie=maxObciazenie;
	Algorytmy.listaProcesorow.add(this);
	Algorytmy.listaProcesorow2.add((Procesor) this.clone());
	Algorytmy.listaProcesorow3.add((Procesor) this.clone());
    }

    int setMinObciazenie(int minObciazenie) {
	return this.minObciazenie = minObciazenie;
    }

    int setMaxObciazenie(int maxObciazenie) {
	return this.maxObciazenie = maxObciazenie;
    }

    public String toString() {
	return "Procesy:" + listaProcesow.size() + ", obciazenie:" + aktualneObciazenie;
    }
}
