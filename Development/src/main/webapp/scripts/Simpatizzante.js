var value=0;
function handleScroll() {
    if(value===0){
        theme.switch("Simpatizzante");
        value++;
    }
    baseOnscroll();
}
window.addEventListener("scroll", handleScroll);
