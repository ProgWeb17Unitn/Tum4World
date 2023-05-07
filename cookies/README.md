# Documentazione 

## Jsp Setup
1. **Tutte** Le pagine  (JSP) dovranno contenere  **all'inizio** 1 elemento per la gestione dei cookies:
    <br></br>
    `<%@ page session="false" %>`
    <br></br>
    _In questo modo viene impedita la creazione delle session da parte di JSP_
<br></br><br></br>



## Session Manager


1. La prima azione svolta è controllare se la session sia già presente:
  ` HttpSession session = request.getSession(false);`
    1. L'attributo false è utilizzato per non creare subito la session in caso non sia presente
   2. 
2. Caso in cui la session **non** è presente, ciò può essere dovuto a 3 opzioni:
   1. Primo accesso alla pagina
   2. Utente che ha i cookies disabilitati ed ha fatto un "salto" tra le pagine (es tornare indietro)
   3. Sessione scaduta
   