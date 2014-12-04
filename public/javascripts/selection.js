
function select() {
    document.getElementById('start-game').disabled=false;




    switch(this.id.toString()) {
        case"Bob":document.getElementById('description').value='hello';
                  document.getElementById('Bob').style.border = "2px solid red";
        case"Alice":;
    }
}