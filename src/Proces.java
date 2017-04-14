import java.util.ArrayList;

public class Proces implements Comparable<Proces>, Cloneable{
    int czasPrzyjscia;
    int dlugosc;
    int czasTrwania;
    int obciazenie;
    int numerProcesora;
    int czasWejscia;

    static ArrayList<Proces> listaWszystkichProcesow = new ArrayList<Proces>();
    static ArrayList<Proces> listaWszystkichProcesow2 = new ArrayList<Proces>();
    static ArrayList<Proces> listaWszystkichProcesow3 = new ArrayList<Proces>();

    Proces(int czasPrzyjscia, int dlugosc, int obciazenie, int numerProcesora) throws CloneNotSupportedException {
	this.czasPrzyjscia = czasPrzyjscia;
	this.dlugosc = dlugosc;
	this.obciazenie = obciazenie;
	this.numerProcesora = numerProcesora;
	listaWszystkichProcesow.add((Proces) this.clone());
	listaWszystkichProcesow2.add((Proces) this.clone());
	listaWszystkichProcesow3.add((Proces) this.clone());
    }

    public String toString() {
	return "czasP:"+czasPrzyjscia +" czasW:" + czasWejscia + ", dl:" + dlugosc + ", obc:" + obciazenie;
    }

    @Override
    public int compareTo(Proces arg0) {
	return Integer.compare(this.czasPrzyjscia, arg0.czasPrzyjscia);
    }
    
    
}
