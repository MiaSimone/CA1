

let getAllPersonsBtn = document.getElementById("getAllPersonsBtn");

getAllPersonsBtn.addEventListener('click', (event) => {
    event.preventDefault();
    fetchAllPersons();
});

// 3.2 Her trækkes data ud af et person-objekt og laves til html:

function renderObjectToHTML(MembersObj) {
    result = `Name: ${MembersObj.name}<br/>
    Student Id: ${MembersObj.studentId}<br/><br/>
    Favorite Color: ${MembersObj.favoriteColor} <br/>`
    return result;
}

// 3: Her fetches alle person fra api'et (som json) og laves til html:

function fetchAllPersons() {
    let url = 'http://localhost:8080/jpareststarter/api/members/all';
    let allMembers = document.getElementById("allMembers");
    fetch(url)
        .then(res => res.json()) //in flow1, just do it
        .then(data => {
            let newArray = data.map(x => `<tr><td>${x.name}</td><td>${x.studentId}</td><td>${x.favoriteColor}</td></tr>`)
            allMembers.innerHTML =
                `<table>
                    <thead><th>Name</th><th>Student ID</th><th>Favorite Color</th></thead>
                    ${newArray.join("")}
                </table>`
        });
}


