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
            window.location.assign("homepage.html");
        })
        .catch((error) => {
            console.log(error);
            alert("Invalid credentials");
        });
}

function logout() {
    sessionStorage.removeItem("user");
    window.location.assign("login.html");
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

    if (gPass === "") {
        if (gFormat === "Letter") {
            console.log("Passing grade set to default (C)");
            gPass = "C";
        }
        if (gPass === "Pass/Fail") {
            console.log("Passing grade set to default (Pass)");
            gPass = "Pass";
        } else {
            gPass = "N/A";
        }
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
            window.location.assign("homepage.html");
        })
        .catch((error) => {
            console.log(error);
            alert("Failed to create reimbursement");
        });
}

function checkInput(rType, cost, gFormat, gPass, location, startDate, endDate, startTime, endTime) {
    if (rType === "" || cost === "" || gFormat === "" || location === "" || startDate === "" || 
    endDate === "" || startTime === "" || endTime === "") {
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

    if (gPass !== "") {
        if (gPass.length > 1) {
            alert("Passing grade must be single letter (no plus or minus");
            return false;
        }

        if (gPass.length === 1 && (gPass !== "A" && gPass !== "B" && gPass !== "C" && gPass !== "D" 
        && gPass !== "F")) {
            alert("Please input valid letter grade or leave blank");
            return false;
        }
    }
    return true;
}

async function getAllReimbursements(user) {
    let res = await fetch(
        `${baseUrl}/reimbursements`,
        {
            method: 'GET',
            header: { 'Content-Type': 'application/json' }
        }
    );
    let resJson = await res.json()
        .then((resp) => {
            fillTable(resp, user);
        })
        .catch((error) => {
            console.log(error);
            alert("Failed to get reimbursements");
        });
}

async function getReimbursementsForUser(user) {
    let res = await fetch(
        `${baseUrl}/users/${user.uid}/reimbursements`,
        {
            method: 'GET',
            header: { 'Content-Type': 'application/json' }
        }
    );
    let resJson = await res.json()
        .then((resp) => {
            fillTable(resp, user);
        })
        .catch((error) => {
            console.log(error);
            alert("Failed to get reimbursements");
        });
}

function fillTable(resp, user) {
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
        let c15 = row.insertCell(15);
        
        c0.innerHTML = r.rId;
        if (user.financeManager) {
            c1.innerHTML = r.firstName + " " + r.lastName;
        } else {
            c1.innerHTML = user.firstName + " " + user.lastName;
        }
        c2.innerHTML = r.status;
        c3.innerHTML = r.rType;
        c4.innerHTML = r.description;
        c5.innerHTML = "$" + parseFloat(r.rCost).toFixed(2);
        c6.innerHTML = "$" + parseFloat(r.reimbursementAmount).toFixed(2);
        c7.innerHTML = r.gradeFormat;
        c8.innerHTML = r.passingGrade;
        c9.innerHTML = r.gradeReceived;
        c10.innerHTML = r.presentationSubmitted;
        c11.innerHTML = r.rLocation;
        c12.innerHTML = `${new Date(r.startDate).toDateString()}-${new Date(r.endDate).toDateString()}`;
        c13.innerHTML = r.startTime + "-" + r.endTime;
        c14.innerHTML = "<button type='button' onclick='update(this)'>Edit</button>";
        c15.innerHTML = r.userId;
    }
}

function update(cell) {
    let rowNum = cell.closest("tr").rowIndex;
    let user = JSON.parse(sessionStorage.getItem("user"));
    
    let partialReimbursement = {
        rId: document.getElementById("rTable").rows[rowNum].cells[0].innerHTML,
        userId: user.uid,
        status: document.getElementById("rTable").rows[rowNum].cells[2].innerHTML,
        reimbursementAmount: document.getElementById("rTable").rows[rowNum].cells[6].innerHTML,
        gradeFormat: document.getElementById("rTable").rows[rowNum].cells[7].innerHTML,
        passingGrade: document.getElementById("rTable").rows[rowNum].cells[8].innerHTML,
        gradeReceived: document.getElementById("rTable").rows[rowNum].cells[9].innerHTML,
        presentationSubmitted: document.getElementById("rTable").rows[rowNum].cells[10].innerHTML
    }

    sessionStorage.setItem("partialR", JSON.stringify(partialReimbursement));
    window.location.assign("reimbursementupdate.html");
}

async function submitUpdate() {
    let partialR = JSON.parse(sessionStorage.getItem("partialR"));

    let status = getElementById("updateGReceived").value;
    let gReceived = document.getElementById("updateGReceived").value;
    let pSubmitted = document.getElementById("updatePresentation").checked;
    let rAmount = parseFloat(document.getElementById("updateRAmount").value).toFixed(2);
    let gradeFormat = partialR.gradeFormat;
    let gPass = partialR.passingGrade;

    if (pSubmitted === false && gradeFormat === "Presentation" && status === "Approved") {
        alert("Cannot approve request: Presentation must be submitted first");
        return;
    } else if (gReceived === "" && gradeFormat !== "Presentation" && status === "Approved") {
        alert("Cannot approve request: Grade must be submitted first");
        return;
    } else if (gradeFormat === "Pass/Fail" && gReceived !== "Pass" && gReceived !== "Fail") {
        alert("Pass/Fail format requires Grade received should be Pass or Fail");
        return;
    } else if (gradeFormat === "Letter") {
        if (gReceived.length === 1 && (gReceived !== "A" && gReceived !== "B" && gReceived !== "C" 
        && gReceived !== "D" && gReceived !== "F")) {
            alert("Please input valid letter grade");
            return;
        }
        gRecieved = gReceived.toUpperCase();
        if (gReceived.charCodeAt(0) > gPass.charCodeAt(0) && status === "Approved") {
            alert("Cannot approve request: Grade received must be at or above passing grade")
        }
    }

    let newPartialR = {
        status: status,
        gradeReceived: gReceived,
        presentationSubmitted: pSubmitted,
        reimbursementAmount: rAmount
    };

    let rJson = JSON.stringify(newPartialR);
    console.log(rJson);

    let res = await fetch(
        `${baseUrl}/users/${partialR.userId}/reimbursements/${partialR.rId}`,
        {
            method: 'PATCH',
            header: { 'Content-Type': 'application/json' },
            body: rJson
        }
    );

    if (res.status === 204) {
        window.location.assign("homepage.html");
    } else {
        console.log(error);
        alert("Failed to update reimbursement");
    }
}

function sendToForm() {
    window.location.assign("form.html");
}

function cancel() {
    window.location.assign("homepage.html");
}

window.onload = function () {
    if (window.location.href.match("http://localhost:8080/homepage.html") != null) {
        let p = document.getElementById("rAmountLeft");
        let caption = document.getElementById("tableCaption");
        let button = document.getElementById("formButton");
        let user = JSON.parse(sessionStorage.getItem("user"));

        if (user.financeManager) {
            p.style.display = "none";
            caption.innerHTML = "All Reimbursements";
            button.style.display = "none";
            getAllReimbursements(user);
        } else {
            let rAmountLeft = p.innerHTML + "$" + parseFloat(user.availableAmount).toFixed(2);
            p.innerHTML = rAmountLeft;
            getReimbursementsForUser(user);
        }
    }
    if (window.location.href.match("http://localhost:8080/form.html") != null) {
        document.getElementById("gFormat").addEventListener('change', (event) => {
            if (event.target.value === "Letter") {
                document.getElementById("gPassLabel").style.display = "inline";
            } else {
                document.getElementById("gPassLabel").style.display = "none";
            }
        });
    }
    if (window.location.href.match("http://localhost:8080/reimbursementupdate.html") != null) {
        let partialR = JSON.parse(sessionStorage.getItem("partialR"));
        let user = JSON.parse(sessionStorage.getItem("user"));
        document.getElementById("updateStatus").value = partialR.status;
        document.getElementById("updateRAmount").value = partialR.reimbursementAmount.substring(1);
        document.getElementById("updateGReceived").value = partialR.gradeReceived;
        document.getElementById("updatePresentation").checked = partialR.presentationSubmitted;

        if (user.financeManager) {
            document.getElementById("gReceivedLabel").style.display = "none";
            document.getElementById("pSubmittedLabel").style.display = "none";
        } else {
            document.getElementById("uStatusLabel").style.display = "none";
            document.getElementById("rAmountLabel").style.display = "none";
            if (partialR.gradeFormat === "Presentation") {
                document.getElementById("gReceivedLabel").style.display = "none";
            } else {
                document.getElementById("pSubmittedLabel").style.display = "none";
            }
        }
    }
}