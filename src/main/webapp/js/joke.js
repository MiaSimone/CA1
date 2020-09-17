/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
document.getElementById("tbody").innerHTML = fetchSingleJoke(id);

let singleJokeInputBtn = document.getElementById("singleJokeInputBtn");
singleJokeInputBtn.addEventListener('click', (event) => {
    event.preventDefault();
    let singleJokeTextInput = document.getElementById("singleJokeTextInput");
    fetchSingleJoke(singleJokeTextInput.value);
});



document.getElementById("tbody").innerHTML = fetchAllJokes();

let getAllJokesBtn = document.getElementById("getAllJokesBtn");
getAllJokesBtn.addEventListener('click', (event) => {
    event.preventDefault();
    fetchAllJokes();
});

// 3.1: Her fetches en enkelt person ud fra deres id (som json):

function fetchSingleJoke(id) {
    let url = 'http://localhost:8080/jpareststarter/api/joke/id' + id;
    fetch(url)
        .then(res => res.json()) //in flow1, just do it
        .then(data => {
            let singleJoke = document.getElementById("singleJoke");
            singleJoke.innerHTML = renderObjectToHTML(data);
        });
}

function fetchAllJokes() {
    
    let url = 'http://localhost:8080/jpareststarter/api/joke/all';
    let allJokes = document.getElementById("tbody");
    fetch(url)
            .then(res => res.json())
            .then(data => {
                let newArray = data.map(x => `<tr><td>${x.theJoke}</td><td>${x.type}</td><td>${x.reference}</td></tr>`);
                allJokes.innerHTML = newArray;
    });
    
    
    
}



