import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Algorytmy {

    static ArrayList<Procesor> listaProcesorow = new ArrayList<Procesor>();
    static ArrayList<Procesor> listaProcesorow2 = new ArrayList<Procesor>();
    static ArrayList<Procesor> listaProcesorow3 = new ArrayList<Procesor>();
    double srednieObciazenie = 0;
    double srednieOdchylenie = 0;
    double srednieObciazenie2 = 0;
    double srednieOdchylenie2 = 0;
    double srednieObciazenie3 = 0;
    double srednieOdchylenie3 = 0;
    int zapytaniaDrugi=0;
    int zapytaniaTrzeci=0;
    int iloscProcesorow = 0;
    Random r = new Random();

    void tworzenieProcesow(int ilosc, int czasPrzyjscia, int dlugosc, int iloscProcesorow, int obciazenie)
	    throws CloneNotSupportedException {
	for (int i = 0; i < ilosc; i++) {
	    int czasPrzyjsciaaa = r.nextInt(czasPrzyjscia);
	    int dlugoscaa = r.nextInt(dlugosc - 5) + 5;
	    int obciazenieaa = r.nextInt(obciazenie - 2) + 2;
	    int numerProcesora = r.nextInt(iloscProcesorow);
	    new Proces(czasPrzyjsciaaa, dlugoscaa, obciazenieaa, numerProcesora);
	    this.iloscProcesorow = iloscProcesorow;
	}
    }

    void tworzenieProcesorow(int minObciazenie, int maxObciazenie)
	    throws CloneNotSupportedException {
	for (int i = 0; i < iloscProcesorow; i++) {
	    new Procesor(minObciazenie, maxObciazenie);
	}
    }

    void AlgorytmPierwszy(int z) {
	int numerProcesora = 0;
	int liczbaProb = z;
	int proby = 0;
	int czas = 0;
	boolean czyKoniec = false;
	boolean czyKoniecOczekujacych = false;
	double sumaObciazen = 0;
	double sumaOdchylen = 0;
	int sumaZapytan = 0;
	int sumaMigracji = 0;

	while (Proces.listaWszystkichProcesow.isEmpty() == false || czyKoniec == false
		|| czyKoniecOczekujacych == false) {
	    if (Proces.listaWszystkichProcesow.isEmpty() == false) {
		for (int i = 0; i < Proces.listaWszystkichProcesow.size(); i++) {
		    Proces aktualny = Proces.listaWszystkichProcesow.get(i);
		    if (czas >= Proces.listaWszystkichProcesow.get(i).czasPrzyjscia) {
			numerProcesora = Proces.listaWszystkichProcesow.get(i).numerProcesora;
			boolean czyDodany = false;
			proby = 0;
			while (proby < liczbaProb && czyDodany == false) {
			    int innyProcesor = r.nextInt(listaProcesorow.size());
			    while (numerProcesora == innyProcesor)
				innyProcesor = r.nextInt(listaProcesorow.size());
			    sumaZapytan++;
			    if (listaProcesorow.get(innyProcesor).aktualneObciazenie <= listaProcesorow
				    .get(innyProcesor).maxObciazenie) {

				int przed = listaProcesorow.get(innyProcesor).aktualneObciazenie;
				// System.out.println("dodaje do procesora " +
				// innyProcesor + " proces " + aktualny);

				listaProcesorow.get(innyProcesor).listaProcesow.add(aktualny);
				aktualny.czasWejscia = czas;

				// System.out.println("dodaje obiciazenie procesu "
				// + aktualny + " do procesora " +
				// innyProcesor);

				listaProcesorow.get(innyProcesor).aktualneObciazenie += +aktualny.obciazenie;
				// System.out.println("przed: " + przed +
				// " dodalem obciazenie: "
				// +
				// Proces.listaWszystkichProcesow.get(i).obciazenie
				// + " aktualne: "
				// +
				// listaProcesorow.get(innyProcesor).aktualneObciazenie);

				Proces.listaWszystkichProcesow.remove(i);
				i--;
				czyDodany = true;
				sumaMigracji++;

			    } else
				proby++;
			}
			if (czyDodany == false) {
			    if (listaProcesorow.get(numerProcesora).aktualneObciazenie < listaProcesorow
				    .get(numerProcesora).maxObciazenie) {
				listaProcesorow.get(numerProcesora).aktualneObciazenie += +aktualny.obciazenie;
				listaProcesorow.get(numerProcesora).listaProcesow.add(aktualny);
				aktualny.czasWejscia = czas;
				Proces.listaWszystkichProcesow.remove(i);
				i--;
			    } else {
				// System.out.println("dodaje oczekujacego");
				listaProcesorow.get(numerProcesora).listaOczekujacychProcesow.add(aktualny);
				Proces.listaWszystkichProcesow.remove(i);
				i--;
			    }
			}
		    }
		}
	    }
	    // oczekujace
	    for (int q = 0; q < listaProcesorow.size(); q++) {
		if (listaProcesorow.get(q).listaOczekujacychProcesow.isEmpty() == false) {
		    for (int k = 0; k < listaProcesorow.get(q).listaOczekujacychProcesow.size(); k++) {
			if (listaProcesorow.get(q).aktualneObciazenie <= listaProcesorow.get(q).maxObciazenie) {
			    listaProcesorow.get(q).listaProcesow.add(listaProcesorow.get(q).listaOczekujacychProcesow
				    .get(k));
			    listaProcesorow.get(q).listaOczekujacychProcesow.get(k).czasWejscia = czas;
			    int przed = listaProcesorow.get(q).aktualneObciazenie;
			    listaProcesorow.get(q).aktualneObciazenie += +listaProcesorow.get(q).listaOczekujacychProcesow
				    .get(k).obciazenie;
			    // System.out.println("przed: " + przed +
			    // " dodalem obciazenie: "
			    // +
			    // listaProcesorow.get(numerProcesora).listaOczekujacychProcesow.get(k).obciazenie
			    // + " aktualne: " +
			    // listaProcesorow.get(numerProcesora).aktualneObciazenie);
			    listaProcesorow.get(q).listaOczekujacychProcesow.remove(k);
			    k--;
			}
		    }
		}
	    }
	    // System.out.println("PRZED USUNIECIEM");
	    // drukujListeProcesow();

	    // usuwanie
	    for (int g = 0; g < listaProcesorow.size(); g++) {
		for (int h = 0; h < listaProcesorow.get(g).listaProcesow.size(); h++) {
		    listaProcesorow.get(g).listaProcesow.get(h).czasTrwania++;
		    if (listaProcesorow.get(g).listaProcesow.get(h).dlugosc == listaProcesorow.get(g).listaProcesow
			    .get(h).czasTrwania) {
			listaProcesorow.get(g).aktualneObciazenie = listaProcesorow.get(g).aktualneObciazenie
				- listaProcesorow.get(g).listaProcesow.get(h).obciazenie;
			// System.out.println("usunalem obiciazenie spowodowane procesem "
			// + listaProcesorow.get(g).listaProcesow.get(h) +
			// "\naktualne obciazenie wynosi: "
			// + listaProcesorow.get(g).aktualneObciazenie);
			listaProcesorow.get(g).listaProcesow.remove(h);
			h--;
		    }
		}
	    }

//	     System.out.println("lista procesow:");
//	     drukujListeProcesow();
//	     System.out.println();
//	     System.out.println("lista oczekujacych procesow:");
//	     drukujListeOczekujacychProcesow();
//	     System.out.println();

	    czyKoniec = true;
	    for (int y = 0; y < listaProcesorow.size(); y++) {
		if (listaProcesorow.get(y).listaProcesow.size() != 0)
		    czyKoniec = false;
	    }

	    czyKoniecOczekujacych = true;
	    for (int w = 0; w < listaProcesorow.size(); w++) {
		if (listaProcesorow.get(w).listaOczekujacychProcesow.size() != 0)
		    czyKoniecOczekujacych = false;
	    }

	    // System.out.println(czas);

	    // liczenie srednich
	    for (int e = 0; e < listaProcesorow.size(); e++) {
		sumaObciazen += listaProcesorow.get(e).aktualneObciazenie;
	    }
	    srednieObciazenie = sumaObciazen / (listaProcesorow.size() * (czas + 1));

	    for (int e = 0; e < listaProcesorow.size(); e++) {
		sumaOdchylen += Math.abs(listaProcesorow.get(e).aktualneObciazenie - srednieObciazenie);
	    }
	    srednieOdchylenie = sumaOdchylen / (listaProcesorow.size() * (czas + 1));

	    if (Proces.listaWszystkichProcesow.isEmpty() == true && czyKoniec == true && czyKoniecOczekujacych == true) {
		srednieObciazenie=srednieObciazenie*1000;
		srednieObciazenie=Math.round(srednieObciazenie);
		srednieObciazenie=srednieObciazenie/1000;
		
		
		srednieOdchylenie=srednieOdchylenie*1000;
		srednieOdchylenie=Math.round(srednieOdchylenie);
		srednieOdchylenie=srednieOdchylenie/1000;
		
		System.out.println();
		System.out.println("Algorytm Pierwszy");
		System.out.println("Srednie obciazenie :" + srednieObciazenie + "%");
		System.out.println("Srednie odchylenie :" + srednieOdchylenie + "%");
		System.out.println("Suma zapytan :" + sumaZapytan);
		System.out.println("Suma migracji :" + sumaMigracji);
	    }
	    czas++;
	    // System.out.println(czyKoniec + "," +
	    // Proces.listaWszystkichProcesow.isEmpty());
	}
    }

    void AlgorytmDrugi() {
	int numerProcesora = 0;
	int czas = 0;
	boolean czyKoniec = false;
	boolean czyKoniecOczekujacych = false;
	ArrayList<Proces> oczekujace = new ArrayList<Proces>();
	double sumaObciazen = 0;
	double sumaOdchylen = 0;
	int sumaZapytan = 0;
	int sumaMigracji = 0;

	while (Proces.listaWszystkichProcesow2.isEmpty() == false || czyKoniec == false
		|| czyKoniecOczekujacych == false) {
	    if (Proces.listaWszystkichProcesow2.isEmpty() == false) {
		for (int i = 0; i < Proces.listaWszystkichProcesow2.size(); i++) {
		    Proces aktualny = Proces.listaWszystkichProcesow2.get(i);

		    if (czas >= Proces.listaWszystkichProcesow2.get(i).czasPrzyjscia) {
			numerProcesora = Proces.listaWszystkichProcesow2.get(i).numerProcesora;
			boolean czyDodany = false;

			if (listaProcesorow2.get(numerProcesora).aktualneObciazenie <= listaProcesorow2
				.get(numerProcesora).maxObciazenie) {
			    listaProcesorow2.get(numerProcesora).aktualneObciazenie += +aktualny.obciazenie;
			    listaProcesorow2.get(numerProcesora).listaProcesow2.add(aktualny);
			    aktualny.czasWejscia = czas;
			    Proces.listaWszystkichProcesow2.remove(aktualny);
			    i--;
			    czyDodany = true;
			}

			if (czyDodany == false) {
			    for (int a = 0; a < listaProcesorow2.size(); a++) {
				if (a != numerProcesora) {
				    if (czyDodany == false) {
					sumaZapytan++;
					if (listaProcesorow2.get(a).aktualneObciazenie <= listaProcesorow2.get(a).maxObciazenie) {

					    int przed = listaProcesorow2.get(a).aktualneObciazenie;
					    // System.out.println("dodaje do procesora "
					    // +
					    // innyProcesor + " proces " +
					    // aktualny);

					    listaProcesorow2.get(a).listaProcesow2.add(aktualny);
					    aktualny.czasWejscia = czas;

					    // System.out.println("dodaje obiciazenie procesu "
					    // + aktualny + " do procesora " +
					    // innyProcesor);

					    listaProcesorow2.get(a).aktualneObciazenie += +aktualny.obciazenie;
					    // System.out.println("przed: " +
					    // przed
					    // +
					    // " dodalem obciazenie: "
					    // +
					    // Proces.listaWszystkichProcesow.get(i).obciazenie
					    // + " aktualne: "
					    // +
					    // listaProcesorow.get(innyProcesor).aktualneObciazenie);
					    Proces.listaWszystkichProcesow2.remove(aktualny);
					    i--;
					    czyDodany = true;
					    sumaMigracji++;

					}
				    }
				} else
				    a++;
			    }
			    if (czyDodany == false) {
				// System.out.println("dodaje oczekujacego");
				oczekujace.add(aktualny);
				Proces.listaWszystkichProcesow2.remove(i);
				i--;
			    }

			}
		    }
		}
	    }
	    // oczekujace
	    if (oczekujace.isEmpty() == false) {
		for (int k = 0; k < oczekujace.size(); k++) {
		    int numerProcesoraOczekujacego = oczekujace.get(k).numerProcesora;
		    // if
		    // (listaProcesorow2.get(numerProcesoraOczekujacego).aktualneObciazenie
		    // < listaProcesorow2
		    // .get(numerProcesoraOczekujacego).maxObciazenie) {
		    // listaProcesorow2.get(numerProcesoraOczekujacego).aktualneObciazenie
		    // += +oczekujace.get(k).obciazenie;
		    // listaProcesorow2.get(numerProcesoraOczekujacego).listaProcesow2.add(oczekujace.get(k));
		    // oczekujace.get(k).czasWejscia = czas;
		    //
		    // oczekujace.remove(k);
		    // k--;
		    // } else {
		    boolean czyZrobione = false;
		    for (int a = 0; a < listaProcesorow2.size(); a++) {
			if (a != numerProcesoraOczekujacego && czyZrobione == false) {
			    if (listaProcesorow2.get(a).aktualneObciazenie <= listaProcesorow2.get(a).maxObciazenie) {

				listaProcesorow2.get(a).listaProcesow2.add(oczekujace.get(k));
				oczekujace.get(k).czasWejscia = czas;

				listaProcesorow2.get(a).aktualneObciazenie += +oczekujace.get(k).obciazenie;
				oczekujace.remove(k);
				k--;
				czyZrobione = true;
			    }
			} else
			    a++;
		    }

		    // }
		}
	    }

	    // usuwanie
	    for (int g = 0; g < listaProcesorow2.size(); g++) {
		for (int h = 0; h < listaProcesorow2.get(g).listaProcesow2.size(); h++) {
		    listaProcesorow2.get(g).listaProcesow2.get(h).czasTrwania++;
		    if (listaProcesorow2.get(g).listaProcesow2.get(h).dlugosc == listaProcesorow2.get(g).listaProcesow2
			    .get(h).czasTrwania) {
			listaProcesorow2.get(g).aktualneObciazenie = listaProcesorow2.get(g).aktualneObciazenie
				- listaProcesorow2.get(g).listaProcesow2.get(h).obciazenie;
			// System.out.println("usunalem obiciazenie spowodowane procesem "
			// + listaProcesorow.get(g).listaProcesow.get(h) +
			// "\naktualne obciazenie wynosi: "
			// + listaProcesorow.get(g).aktualneObciazenie);
			listaProcesorow2.get(g).listaProcesow2.remove(h);
			h--;
		    }
		}
	    }
//	     System.out.println("lista procesow:");
//	     drukujListeProcesow2();
//	     System.out.println();
//	     System.out.println("lista oczekujacych procesow:");
//	     System.out.println(oczekujace);
//	     System.out.println();

	    czyKoniec = true;
	    for (int y = 0; y < listaProcesorow2.size(); y++) {
		if (listaProcesorow2.get(y).listaProcesow2.size() != 0)
		    czyKoniec = false;
	    }

	    czyKoniecOczekujacych = true;
	    if (oczekujace.size() != 0)
		czyKoniecOczekujacych = false;

	    // System.out.println(czas);

	    // liczenie srednich
	    for (int e = 0; e < listaProcesorow2.size(); e++) {
		sumaObciazen += listaProcesorow2.get(e).aktualneObciazenie;
	    }
	    srednieObciazenie = sumaObciazen / (listaProcesorow2.size() * (czas + 1));

	    for (int e = 0; e < listaProcesorow2.size(); e++) {
		sumaOdchylen += Math.abs(listaProcesorow2.get(e).aktualneObciazenie - srednieObciazenie);
	    }
	    srednieOdchylenie = sumaOdchylen / (listaProcesorow2.size() * (czas + 1));

	    if (Proces.listaWszystkichProcesow2.isEmpty() == true && czyKoniec == true && czyKoniecOczekujacych == true) {
		srednieObciazenie=srednieObciazenie*1000;
		srednieObciazenie=Math.round(srednieObciazenie);
		srednieObciazenie=srednieObciazenie/1000;
		
		
		srednieOdchylenie=srednieOdchylenie*1000;
		srednieOdchylenie=Math.round(srednieOdchylenie);
		srednieOdchylenie=srednieOdchylenie/1000;
		
		System.out.println();
		System.out.println("Algorytm Drugi");
		System.out.println("Srednie obciazenie :" + srednieObciazenie + "%");
		System.out.println("Srednie odchylenie :" + srednieOdchylenie + "%");
		System.out.println("Suma zapytan :" + sumaZapytan);
		System.out.println("Suma migracji :" + sumaMigracji);
		zapytaniaDrugi=sumaZapytan;
	    }

	    czas++;
	    // System.out.println(czyKoniec + "," +
	    // Proces.listaWszystkichProcesow.isEmpty());
	}
    }

    void AlgorytmTrzeci(int ile) {
	int numerProcesora = 0;
	int czas = 0;
	boolean czyKoniec = false;
	boolean czyKoniecOczekujacych = false;
	ArrayList<Proces> oczekujace = new ArrayList<Proces>();
	double sumaObciazen = 0;
	double sumaOdchylen = 0;
	int sumaZapytan = 0;
	int sumaMigracji = 0;
	int sumaZapytanOddane = 0;
	int sumaMigracjiOddane = 0;

	while (Proces.listaWszystkichProcesow3.isEmpty() == false || czyKoniec == false
		|| czyKoniecOczekujacych == false) {
	    if (Proces.listaWszystkichProcesow3.isEmpty() == false) {
		for (int i = 0; i < Proces.listaWszystkichProcesow3.size(); i++) {
		    Proces aktualny = Proces.listaWszystkichProcesow3.get(i);

		    if (czas >= Proces.listaWszystkichProcesow3.get(i).czasPrzyjscia) {
			numerProcesora = Proces.listaWszystkichProcesow3.get(i).numerProcesora;
			boolean czyDodany = false;

			if (listaProcesorow3.get(numerProcesora).aktualneObciazenie <= listaProcesorow3
				.get(numerProcesora).maxObciazenie) {
			    listaProcesorow3.get(numerProcesora).aktualneObciazenie += +aktualny.obciazenie;
			    listaProcesorow3.get(numerProcesora).listaProcesow3.add(aktualny);
			    aktualny.czasWejscia = czas;
			    Proces.listaWszystkichProcesow3.remove(aktualny);
			    i--;
			    czyDodany = true;
			}

			if (czyDodany == false) {
			    for (int a = 0; a < listaProcesorow3.size(); a++) {
				if (a != numerProcesora) {
				    if (czyDodany == false) {
					sumaZapytan++;
					if (listaProcesorow3.get(a).aktualneObciazenie <= listaProcesorow3.get(a).maxObciazenie) {

					    int przed = listaProcesorow3.get(a).aktualneObciazenie;
					    // System.out.println("dodaje do procesora "
					    // +
					    // innyProcesor + " proces " +
					    // aktualny);

					    listaProcesorow3.get(a).listaProcesow3.add(aktualny);
					    aktualny.czasWejscia = czas;

					    // System.out.println("dodaje obiciazenie procesu "
					    // + aktualny + " do procesora " +
					    // innyProcesor);

					    listaProcesorow3.get(a).aktualneObciazenie += +aktualny.obciazenie;
					    // System.out.println("przed: " +
					    // przed
					    // +
					    // " dodalem obciazenie: "
					    // +
					    // Proces.listaWszystkichProcesow.get(i).obciazenie
					    // + " aktualne: "
					    // +
					    // listaProcesorow.get(innyProcesor).aktualneObciazenie);

					    Proces.listaWszystkichProcesow3.remove(aktualny);
					    i--;
					    czyDodany = true;
					    sumaMigracji++;

					}
				    }
				} else
				    a++;
			    }
			    if (czyDodany == false) {
				// System.out.println("dodaje oczekujacego");
				oczekujace.add(aktualny);
				Proces.listaWszystkichProcesow3.remove(i);
				i--;
			    }

			}
		    }
		}
	    }
	    // oczekujace
	    if (oczekujace.isEmpty() == false) {
		for (int k = 0; k < oczekujace.size(); k++) {
		    int numerProcesoraOczekujacego = oczekujace.get(k).numerProcesora;
		    // if
		    // (listaProcesorow3.get(numerProcesoraOczekujacego).aktualneObciazenie
		    // < listaProcesorow3
		    // .get(numerProcesoraOczekujacego).maxObciazenie) {
		    // listaProcesorow3.get(numerProcesoraOczekujacego).aktualneObciazenie
		    // += +oczekujace.get(k).obciazenie;
		    // listaProcesorow3.get(numerProcesoraOczekujacego).listaProcesow3.add(oczekujace.get(k));
		    // oczekujace.get(k).czasWejscia = czas;
		    //
		    // oczekujace.remove(k);
		    // k--;
		    // } else {
		    boolean czyZrobione = false;
		    for (int a = 0; a < listaProcesorow3.size(); a++) {
			if (a != numerProcesoraOczekujacego && czyZrobione == false) {
			    if (listaProcesorow3.get(a).aktualneObciazenie <= listaProcesorow3.get(a).maxObciazenie) {

				listaProcesorow3.get(a).listaProcesow3.add(oczekujace.get(k));
				oczekujace.get(k).czasWejscia = czas;

				listaProcesorow3.get(a).aktualneObciazenie += +oczekujace.get(k).obciazenie;
				oczekujace.remove(k);
				k--;
				czyZrobione = true;
			    }
			} else
			    a++;
		    }

		    // }
		}
	    }

	    // usuwanie
	    for (int g = 0; g < listaProcesorow3.size(); g++) {
		for (int h = 0; h < listaProcesorow3.get(g).listaProcesow3.size(); h++) {
		    listaProcesorow3.get(g).listaProcesow3.get(h).czasTrwania++;
		    if (listaProcesorow3.get(g).listaProcesow3.get(h).dlugosc == listaProcesorow3.get(g).listaProcesow3
			    .get(h).czasTrwania) {
			listaProcesorow3.get(g).aktualneObciazenie = listaProcesorow3.get(g).aktualneObciazenie
				- listaProcesorow3.get(g).listaProcesow3.get(h).obciazenie;
			// System.out.println("usunalem obiciazenie spowodowane procesem "
			// + listaProcesorow.get(g).listaProcesow.get(h) +
			// "\naktualne obciazenie wynosi: "
			// + listaProcesorow.get(g).aktualneObciazenie);
			listaProcesorow3.get(g).listaProcesow3.remove(h);
			h--;
		    }
		}
	    }

	    // oddawanie
	    for (int f = 0; f < listaProcesorow3.size(); f++) {
		if (listaProcesorow3.get(f).aktualneObciazenie <= listaProcesorow3.get(f).minObciazenie) {
		    // System.out.println("mam min " + listaProcesorow3.get(f));
		    int wylosowany = r.nextInt(listaProcesorow3.size());
		    while (f == wylosowany)
			wylosowany = r.nextInt(listaProcesorow3.size());
		    sumaZapytan++;
		    sumaZapytanOddane++;
		    if (listaProcesorow3.get(wylosowany).aktualneObciazenie >= listaProcesorow3.get(wylosowany).maxObciazenie) {
			// System.out.println("mam max " +
			// listaProcesorow3.get(wylosowany));
			boolean czyOddalem = false;
			for (int p = 0; p < ile; p++) {
			    Proces oddawany = listaProcesorow3.get(wylosowany).listaProcesow3.get(p);
			    if (listaProcesorow3.get(f).aktualneObciazenie + oddawany.obciazenie <= 100) {
				listaProcesorow3.get(f).listaProcesow3.add(oddawany);
				listaProcesorow3.get(f).aktualneObciazenie += oddawany.obciazenie;
				// System.out.println("oddaje z " + (wylosowany
				// + 1) + " do " + (f + 1));
				czyOddalem = true;
				sumaMigracji++;
				sumaMigracjiOddane++;
				listaProcesorow3.get(wylosowany).aktualneObciazenie += -listaProcesorow3
					    .get(wylosowany).listaProcesow3.get(p).obciazenie;
				    listaProcesorow3.get(wylosowany).listaProcesow3.remove(p);
			    }
			}
		    }
		}
	    }
//	     System.out.println("lista procesow:");
//	     drukujListeProcesow3();
//	     System.out.println();
//	     System.out.println("lista oczekujacych procesow:");
//	     System.out.println(oczekujace);
//	     System.out.println();

	    czyKoniec = true;
	    for (int y = 0; y < listaProcesorow3.size(); y++) {
		if (listaProcesorow3.get(y).listaProcesow3.size() != 0)
		    czyKoniec = false;
	    }

	    czyKoniecOczekujacych = true;
	    if (oczekujace.size() != 0)
		czyKoniecOczekujacych = false;

	    // System.out.println(czas);

	    // liczenie srednich
	    for (int e = 0; e < listaProcesorow3.size(); e++) {
		sumaObciazen += listaProcesorow3.get(e).aktualneObciazenie;
	    }
	    srednieObciazenie = sumaObciazen / (listaProcesorow3.size() * (czas + 1));

	    for (int e = 0; e < listaProcesorow3.size(); e++) {
		sumaOdchylen += Math.abs(listaProcesorow3.get(e).aktualneObciazenie - srednieObciazenie);
	    }
	    srednieOdchylenie = sumaOdchylen / (listaProcesorow3.size() * (czas + 1));

	    if (Proces.listaWszystkichProcesow3.isEmpty() == true && czyKoniec == true && czyKoniecOczekujacych == true) {
		srednieObciazenie=srednieObciazenie*1000;
		srednieObciazenie=Math.round(srednieObciazenie);
		srednieObciazenie=srednieObciazenie/1000;
		
		
		srednieOdchylenie=srednieOdchylenie*1000;
		srednieOdchylenie=Math.round(srednieOdchylenie);
		srednieOdchylenie=srednieOdchylenie/1000;
		
		System.out.println();
		System.out.println("Algorytm Trzeci");
		System.out.println("Srednie obciazenie :" + srednieObciazenie + "%");
		System.out.println("Srednie odchylenie :" + srednieOdchylenie + "%");
		System.out.println("Suma zapytan :" + sumaZapytan + ", Suma zapytan przy oddawaniu :"
			+ sumaZapytanOddane);
		System.out.println("Suma migracji :" + sumaMigracji + ", Suma migracji przy oddawaniu :"
			+ sumaMigracjiOddane);
		zapytaniaTrzeci=sumaZapytan;
	    }

	    czas++;

	}
    }

    void drukujListeProcesow() {
	for (int i = 0; i < listaProcesorow.size(); i++) {
	    System.out.println(i + 1 + " Procesor " + listaProcesorow.get(i).listaProcesow);
	    System.out.println(listaProcesorow.get(i).aktualneObciazenie + "%");
	}
    }

    void drukujListeProcesow2() {
	for (int i = 0; i < listaProcesorow2.size(); i++) {
	    System.out.println(i + 1 + " Procesor " + listaProcesorow2.get(i).listaProcesow2);
	    System.out.println(listaProcesorow2.get(i).aktualneObciazenie + "%");
	}
    }

    void drukujListeProcesow3() {
	for (int i = 0; i < listaProcesorow3.size(); i++) {
	    System.out.println(i + 1 + " Procesor " + listaProcesorow3.get(i).listaProcesow3);
	    System.out.println(listaProcesorow3.get(i).aktualneObciazenie + "%");
	}
    }

    void drukujListeOczekujacychProcesow() {
	for (int i = 0; i < listaProcesorow.size(); i++) {
	    System.out.println(i + 1 + " Procesor " + listaProcesorow.get(i).listaOczekujacychProcesow);
	}
    }
    
    void ktoryWiecejZapytan(){
	if(zapytaniaDrugi>zapytaniaTrzeci)
	    System.out.println("Wiecej zapytan ma algorytm drugi");
	else
	    System.out.println("Wiecej zapytan ma algorytm trzeci");
    }

    public static void main(String[] args) throws CloneNotSupportedException {
	Algorytmy algorytm = new Algorytmy();

	algorytm.tworzenieProcesow(20000, 50, 20, 100, 7);
	algorytm.tworzenieProcesorow(60, 80);
	Collections.sort(Proces.listaWszystkichProcesow);
	Collections.sort(Proces.listaWszystkichProcesow2);
	Collections.sort(Proces.listaWszystkichProcesow3);
	algorytm.AlgorytmPierwszy(5);
	algorytm.AlgorytmDrugi();
	algorytm.AlgorytmTrzeci(4);
//	System.out.println();
//	algorytm.ktoryWiecejZapytan();
	

    }
}
