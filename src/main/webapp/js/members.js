
document.getElementById("tbody").innerHTML = fetchAllPersons();

let getAllMembersBtn = document.getElementById("getAllMembersBtn");
getAllMembersBtn.addEventListener('click', (event) => {
    event.preventDefault();
    fetchAllPersons();
});

// 3: Her fetches alle person fra api'et (som json) og laves til html:

function fetchAllPersons() {
    let url = 'http://localhost:8080/jpareststarter/api/members/all';
    let allMembers = document.getElementById("tbody");
    fetch(url)
        .then(res => res.json()) //in flow1, just do it
        .then(data => {
            let newArray = data.map(x => `<tr><td>${x.name}</td><td>${x.studentId}</td><td>${x.favoriteColor}</td></tr>`);
            allMembers.innerHTML = newArray;
   
        });
}  










