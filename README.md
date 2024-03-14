Singleton:
-pentru a ma asigura ca exista o singura instanta a aplicatiei la un moment dat(o singura versiune care contine informatii despre toti userii) cu care useri pot interationa printr-un unic punct de acces oriunde s-ar afla si asfel asigurandu-se sincronizarea datelor 

Factory method:
-pentru a asigura o interfata comuna tuturor itemelor din portofoliul unui user
-pentru a putea cupride toate tipurile de iteme intr-o singura colectie asupra careia pot face prelucari(cu care pot opera) fara necesitatea de a sti tipul concet al itemelor
-pentru a putea extinde cu usurinta pe viitor gama de iteme din portofloliu compatibile cu aplicatia doar creand alte clase factory fara a fi necesare alte modificari in restul functiilor care fac prelucrari asupra portofoliului si altor tipuri de iteme

Iterator:
-accesarea itemelor doar de o anumita categorie din portofoliu
-foarte utila pentru orice viitoare funtionalitati care vizeza procesari doar pe o anumita categorie de obiecte
-operatii custom pe colectie precum getStock care returneza Stockul cerut si creaza inainte obiectul cautat daca nu exista daja
-se pot inmplementa usor alti iteratori asupra protofoliului dupa diverse criteri


Decorator:
-modificarea tipurilor de utilizatori ai aplicatie(prin acordarea de beneficii sau penalitati) fara a altera informatiile deja existente ale userilor
-alternativa la mostenire
-lasa posibilitatea de a anula modificarile prin simpla revenire la obiectul delegat
-se pot crea usor alti decoratorii care pot aduce introduce alte tipuri de useri care

Facade:
-ofera o singura interfata pentru a folosi toate functionalitatile aplicatiei fara a fi necesare alte prelucrari
-ascunde complexitatea aplicatiei si algoritmii ei interni