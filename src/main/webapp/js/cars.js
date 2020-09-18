
document.getElementById("carsTable").innerHTML = fetchAllCars();

let switchbutton = true;
let getAllCarsBtn = document.getElementById("getAllCarsBtn");
getAllCarsBtn.addEventListener('click', (event) => {
    event.preventDefault();
    fetchAllCars();
});

//let sortByManufacturerHead = document.getElementById("sortByManufacturerHead");
//sortByManufacturerHead.addEventListener('click', (event) => {
//    event.preventDefault();
//    sortTable(0);
//});

// 3: Her fetches alle biler fra api'et (som json) og laves til html:

function fetchAllCars() {
    let url = 'http://localhost:8080/jpareststarter/api/cars/all';
    let allCars = document.getElementById("carsTable");
    fetch(url)
            .then(res => res.json())
            .then(data => {
                let newArray = data.map(x => `<tr><td>${x.manufacturer}</td><td>${x.year}</td><td>${x.model}</td><td>${x.price}</td><td>${x.quantity}</td></tr>`);
                allCars.innerHTML =
                    `<table id="carsTable">
                        <thead><th onclick="sortTable(0)">Manufacturer</th><th onclick="sortTable(1)">Year</th><th onclick="sortTable(2)">Model</th><th onclick="sortTable(3)">Price</th><th onclick="sortTable(4)">Quantity</th></thead>
                        ${newArray.join("")}
                    </table>`;
            });
}

//function sortTable(n) {
//  var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
//  table = document.getElementById("carsTable");
//  switching = true;
//  // Set the sorting direction to ascending:
//  dir = "asc";
//  /* Make a loop that will continue until
//  no switching has been done: */
//  while (switching) {
//    // Start by saying: no switching is done:
//    switching = false;
//    rows = table.rows;
//    /* Loop through all table rows (except the
//    first, which contains table headers): */
//    for (i = 1; i < (rows.length - 1); i++) {
//      // Start by saying there should be no switching:
//      shouldSwitch = false;
//      /* Get the two elements you want to compare,
//      one from current row and one from the next: */
//      x = rows[i].getElementsByTagName("TD")[n];
//      y = rows[i + 1].getElementsByTagName("TD")[n];
//      /* Check if the two rows should switch place,
//      based on the direction, asc or desc: */
//      if (dir == "asc") {
//        if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
//          // If so, mark as a switch and break the loop:
//          shouldSwitch = true;
//          break;
//        }
//      } else if (dir == "desc") {
//        if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
//          // If so, mark as a switch and break the loop:
//          shouldSwitch = true;
//          break;
//        }
//      }
//    }
//    if (shouldSwitch) {
//      /* If a switch has been marked, make the switch
//      and mark that a switch has been done: */
//      rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
//      switching = true;
//      // Each time a switch is done, increase this count by 1:
//      switchcount ++;
//    } else {
//      /* If no switching has been done AND the direction is "asc",
//      set the direction to "desc" and run the while loop again. */
//      if (switchcount == 0 && dir == "asc") {
//        dir = "desc";
//        switching = true;
//      }
//    }
//  }
//}









