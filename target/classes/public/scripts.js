let baseUrl = "http://localhost:8080";

async function login() {
    let email = document.getElementById('email').value;
    let password = document.getElementById('pass').value;

    let user = {
        email: email,
        pass: password
    };

    let userJson = JSON.stringify(user);

    let res = await fetch(
        `${baseUrl}/login`,
        {
            method: 'POST',
            header: { 'Content-Type': 'application/json' },
            body: userJson
        }
    );
    let resJson = await res.json()
        .then((resp) => {
            sessionStorage.setItem("user", JSON.stringify(resp));
            if (resp.isFinanceManager) {
                window.location.assign("managerhomepage.html");
            } else {
                window.location.assign("userhomepage.html");
            }
        })
        .catch((error) => {
            console.log(error);
            alert("Invalid credentials");
        });
}

function diffWeeks(dt2, dt1) {
    let diff =(dt2 - dt1) / 1000;
    diff /= (60 * 60 * 24 * 7);
    return Math.abs(Math.round(diff));
 }

async function submitReimbursementForm() {
    let rType = document.getElementById("rType").value,
        cost = document.getElementById("cost").value,
        gFormat = document.getElementById("gFormat").value,
        gPass = document.getElementById("gPass").value,
        location = document.getElementById("location").value,
        startDate = document.getElementById("startDate").value,
        endDate = document.getElementById("endDate").value,
        startTime = document.getElementById("startTime").value,
        endTime = document.getElementById("endTime").value,
        desc = document.getElementById("description").value;

    if (!checkInput(rType, cost, gFormat, gPass, location, startDate, endDate, startTime, endTime)) {
        return;
    }

    if (gPass == "") {
        console.log("Passing grade set to default (C)");
        gPass = "C";
    } else {
        gPass = gPass.toUpperCase();
    }
    
    let status = "Submitted";

    startDate = new Date(startDate);
    endDate = new Date(endDate);

    startTime += ":00";
    endTime += ":00";

    cost = parseFloat(cost).toFixed(2);

    if (diffWeeks(startDate.getTime(), new Date().getTime()) < 2) {
        console.log("Request marked as urgent");
        status = "Urgent";
    }

    let reimbursement = {
        status: status,
        description: desc,
        rCost: cost,
        rLocation: location,
        startDate: startDate,
        endDate: endDate,
        startTime: startTime,
        endTime: endTime,
        rType: rType,
        gradeFormat: gFormat,
        passingGrade: gPass
    };

    console.log(reimbursement);

    let user = JSON.parse(sessionStorage.getItem("user"));
    let rJson = JSON.stringify(reimbursement);

    let res = await fetch(
        `${baseUrl}/users/${user.uid}/reimbursements`,
        {
            method: 'POST',
            header: { 'Content-Type': 'application/json' },
            body: rJson
        }
    );
    let resJson = await res.json()
        .then((resp) => {
            console.log(resp);
        })
        .catch((error) => {
            console.log(error);
        });
}

function checkInput(rType, cost, gFormat, gPass, location, startDate, endDate, startTime, endTime) {
    if (rType == "" || cost == "" || gFormat == "" || location == "" || startDate == "" || 
    endDate == "" || startTime == "" || endTime == "") {
        alert("Need values for all required fields");
        return false;
    }

    if (parseInt(cost) <= 0) {
        alert("Cost of class must be greater than 0");
        return false;
    }
    
    let currentDate =  new Date();
    startDate = new Date(startDate);
    endDate = new Date(endDate);
    startDate.setMinutes(startDate.getMinutes() + startDate.getTimezoneOffset());
    endDate.setMinutes(endDate.getMinutes() + endDate.getTimezoneOffset());

    if (diffWeeks(startDate.getTime(), currentDate.getTime()) < 1) {
        alert("Start date must be one week in future");
        return false;
    }

    if (startDate >= endDate) {
        alert("End date must be after start date");
        return false;
    }

    if (gPass != "") {
        if (gPass.length > 1) {
            alert("Passing grade must be single letter");
            return false;
        }

        if (gPass.length == 1 && (gPass != "A" && gPass != "B" && gPass != "C" && gPass != "D" 
        && gPass != "F")) {
            alert("Please input valid letter grade or leave blank");
            return false;
        }
    }

    return true;
}

async function getReimbursementsForUser() {
    let user = JSON.parse(sessionStorage.getItem("user"));
    console.log(user);

    let res = await fetch(
        `${baseUrl}/users/${user.uid}/reimbursements`,
        {
            method: 'GET',
            header: { 'Content-Type': 'application/json' }
        }
    );
    let resJson = await res.json()
        .then((resp) => {
            console.log(resp);
            let table = document.getElementById("rTable");
            for (r of resp) {
                let row = table.insertRow(-1);
                let c0 = row.insertCell(0);
                let c1 = row.insertCell(1);
                let c2 = row.insertCell(2);
                let c3 = row.insertCell(3);
                let c4 = row.insertCell(4);
                let c5 = row.insertCell(5);
                let c6 = row.insertCell(6);
                let c7 = row.insertCell(7);
                let c8 = row.insertCell(8);
                let c9 = row.insertCell(9);
                let c10 = row.insertCell(10);
                let c11 = row.insertCell(11);
                let c12  = row.insertCell(12);
                let c13 = row.insertCell(13);
                let c14 = row.insertCell(14);
                
                c0.innerHTML = r.rId;
                c1.innerHTML = user.firstName + " " + user.lastName;
                c2.innerHTML = r.status;
                c3.innerHTML = r.rType;
                c4.innerHTML = r.description;
                c5.innerHTML = parseFloat(r.rCost).toFixed(2);
                c6.innerHTML = parseFloat(r.reimbursementAmount).toFixed(2);
                c7.innerHTML = r.gradeFormat;
                c8.innerHTML = r.passingGrade;
                c9.innerHTML = r.gradeReceived;
                c10.innerHTML = r.presentationSubmitted;
                c11.innerHTML = r.rLocation;
                c12.innerHTML = `${new Date(r.startDate).toDateString()}-${new Date(r.endDate).toDateString()}`;
                c13.innerHTML = r.startTime + "-" + r.endTime;
                if (!user.isFinanceManager) {
                    // Have edit button send to correct page for editing reimbursement
                    c14.innerHTML = "<button>Edit</button>";
                } else {
                    // Have edit button send to correct page for editing reimbursement 
                }
                
            }


        })
        .catch((error) => {
            console.log(error);
        });
}

window.onload = function () {
    if (window.location.href.match("http://localhost:8080/userhomepage.html") != null) {
        getReimbursementsForUser();
    }
    if (window.location.href.match("http://localhost:8080/managerhomepage.html") != null) {
        getAllReimbursements();
    }
}