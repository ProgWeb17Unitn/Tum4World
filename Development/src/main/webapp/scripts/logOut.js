window.addEventListener('load', changecolor);

function changecolor(){
    // dato che base.js si occupa di cambiare il colore di header e footer in base al tipo di utente, sfrutto
    // il valore di colore "recuperato" da essa, aggiungo un piccolo delay per far in modo che il background color
    // venga settato, Nb: non ho bisogno di verificare la sua esistenza, perchè in caso non venga recuperato
    // il tipo di utente viene visualizzato il colore di default

    setTimeout(()=> {
        var rettangolo = document.getElementsByClassName("logOutRectangle")[0];
        var color=theme.backgroundColor;

        // scelgo la sfumatura in base al valore del tema
        var sfumatura= '#3B8EA5';
        if(color ==='#5f6b8d'){
            sfumatura='#8391bb'
        }
        else if(color==='#502477'){
            sfumatura='#8844c9'
        }

        rettangolo.style.backgroundColor = color;
        rettangolo.style.border="2px "+color;
        rettangolo.style.boxShadow="2px 3px 4px "+sfumatura;
    },200);
}