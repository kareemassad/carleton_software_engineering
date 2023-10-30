// spa.js
function addBuddy(addressBookId, name, phoneNumber) {
    if (!addressBookId) {
        console.error('addressBookId is null or undefined');
        return;
    }
    const buddyInfo = { name: name, phoneNumber: phoneNumber };
    fetch(`/api/addressBooks/${addressBookId}/addBuddy`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(buddyInfo)
    })
        .then(response => response.json())
        .then(data => {
            refreshBuddyList(addressBookId);
        })
        .catch(error => console.error('Error:', error));
}

function refreshBuddyList(addressBookId) {
    if (!addressBookId) {
        console.error('addressBookId is null or undefined');
        return;
    }
    fetch(`/api/addressBooks/${addressBookId}/view`)
        .then(response => response.json())
        .then(data => {
            if (!data || !data.buddies) {
                console.error('Unexpected data received:', data);
                return;
            }
            const tableBody = document.getElementById('buddy-table-body');
            tableBody.innerHTML = '';
            data.buddies.forEach(buddy => {
                const row = tableBody.insertRow();
                row.insertCell(0).innerText = buddy.id;
                row.insertCell(1).innerText = buddy.name;
                row.insertCell(2).innerText = buddy.phoneNumber;
            });
        })
        .catch(error => console.error('Error:', error));
}

function initialize() {
    const addressBookId = document.body.getAttribute('data-address-book-id');
    const addButton = document.getElementById('add-buddy-button');
    console.log(addButton);  // This should log the element, or 'null' if it's not found
    addButton.addEventListener('click', function(event) {
        event.preventDefault();
        const name = document.getElementById('buddy-name').value;
        const phone = document.getElementById('buddy-phone').value;
        addBuddy(addressBookId, name, phone);
    });
}

window.onload = initialize;

// spa.js
let addressBookId = null;

document.getElementById('create-address-book-button').addEventListener('click', function() {
    fetch('/api/createAddressBook', { method: 'POST' })
        .then(response => response.json())
        .then(data => {
            addressBookId = data.id;
            document.getElementById('create-address-book-section').style.display = 'none';
            document.getElementById('add-buddy-section').style.display = 'block';
            document.getElementById('buddies-table').style.display = 'block';
        })
        .catch(error => console.error('Error:', error));
});

document.getElementById('add-buddy-button').addEventListener('click', function() {
    const name = document.getElementById('buddy-name').value;
    const phoneNumber = document.getElementById('buddy-phone').value;
    const buddyInfo = { name: name, phoneNumber: phoneNumber };
    fetch(`/api/addressBooks/${addressBookId}/addBuddy`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(buddyInfo)
    })
        .then(response => response.json())
        .then(data => {
            const tableBody = document.getElementById('buddy-table-body');
            const row = tableBody.insertRow();
            row.insertCell(0).innerText = data.id;
            row.insertCell(1).innerText = data.name;
            row.insertCell(2).innerText = data.phoneNumber;
        })
        .catch(error => console.error('Error:', error));
});
