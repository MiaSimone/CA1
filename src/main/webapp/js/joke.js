

// -------------------------- ALL JOKES --------------------------------------

let getAllJokesBtn = document.getElementById("getAllJokesBtn");

getAllJokesBtn.addEventListener('click', (event) => {
    event.preventDefault();
    fetchAllJokes();
});

function fetchAllJokes() {
    let url = 'http://localhost:8080/jpareststarter/api/joke/all';
    let allJokes = document.getElementById("allJokes");
    fetch(url)
        .then(res => res.json()) 
        .then(data => {
            let newArray = data.map(x => `<tr><td>${x.theJoke}</td><td>${x.type}</td><td>${x.reference}</td></tr>`);
            allJokes.innerHTML = 
                `<table>
                    <thead><th>The Joke</th><th>Type</th><th>Reference</th></thead>
                    ${newArray.join("")}
                </table>`;
        });
}


// -------------------------- SINGLE JOKE --------------------------------------

let singleJokeInputBtn = document.getElementById("singleJokeInputBtn");

singleJokeInputBtn.addEventListener('click', (event) => {
    event.preventDefault();
    let singleJokeTextInput = document.getElementById("singleJokeTextInput");
    fetchSingleJoke(singleJokeTextInput.value);
});

function fetchSingleJoke(id) {
    let url = 'http://localhost:8080/jpareststarter/api/joke/id/' + id;
    fetch(url)
        .then(res => res.json()) //in flow1, just do it
        .then(data => {
            let singleJoke = document.getElementById("singleJoke");
            singleJoke.innerHTML = renderObjectToHTML(data);
        });
    
}

function renderObjectToHTML(myJokeObj) {
    result = `The Joke: ${myJokeObj.theJoke}<br/>
    Type: ${myJokeObj.type}<br/>
    Reference: ${myJokeObj.reference} <br/>`;
    return result;
}


// -------------------------- RANDOM JOKE --------------------------------------

let getRandomJokeBtn = document.getElementById("getRandomJokeBtn");

getRandomJokeBtn.addEventListener('click', (event) => {
    event.preventDefault();
    fetchRandomJoke();
});


function fetchRandomJoke() {
    let url = 'http://localhost:8080/jpareststarter/api/joke/random';
    let randomJoke = document.getElementById("tbody");
    fetch(url)
            .then(res => res.json())
            .then(data => {
                let randomJoke = document.getElementById("randomJoke");
                randomJoke.innerHTML = renderObjectToHTML(data);
    })
}