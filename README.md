Singleton:
-pentru a ma asigura ca exista o singura instanta a aplicatiei la un moment dat, o singura versiune care contine informatii despre toti userii si care e disponibila spre intantiere spre toti useri
-sincronizare si integritate

Factory method:
-pentru a asigura o interfata comuna tuturor itemelor din portofoliul unui user
-pentru a putea cupride toate tipurile de iteme intr-o singura colectie asupra careia pot face prelucari(cu care pot opera) fara necesitatea de a sti tipul concet al itemelor
-pentru a putea extinde cu usurinta pe viitor gama de iteme din portofloliu compatibile cu aplicatia doar creand alte clase factory fara a fi necesare alte modificari in restul functiilor care fac prelucrari asupra portofoliului si altor tipuri de iteme

Iterator:
-accesarea itemelor doar de o anumita categorie din portofoliu
-foarte utila pentru orice viitoare funtionalitati care vizeaza procesari doar pe o anumita categorie de obiecte(aplicarea de bonusuri, dividente)
-operatii custom pe colectie precum getStock care returneza Stockul cautat, iar daca userul nu a mai avut pana acum actiuni la resp. companie, intai e introdusa in potofoliu sau
-se pot inmplementa usor alti iteratori asupra protofoliului dupa diverse criteri
ex iteratori: afisarea actiniunile pe care care au crescut in valoare/ti-au adus profit sau in care merita sa investesti


Decorator:
-modificarea tipurilor de utilizatori ai aplicatie(prin acordarea de beneficii sau penalitati) fara a altera informatiile deja existente ale userilor
-alternativa la mostenire
-lasa posibilitatea de a anula modificarile prin simpla revenire la obiectul delegat
-se pot crea usor alti decoratorii care pot aduce introduce alte tipuri de useri care

Facade:
-ofera utilizatorilor o singura interfata pentru a accesa toate functionalitatile de care au nevoie fara a fi necesara lucrul cu clasele User sau Items, acestea find necesare doar pentru logica si organizarea interna a aplicatiei.
-ascunde complexitatea aplicatiei si algoritmii ei interni facand experienta user-friendly
-daca useri ar avea acces la date sau metode ale celorlalte clase s-ar crea brese de securitate masive