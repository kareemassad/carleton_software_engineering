// spa.js
function addBuddy(addressBookId, name, phoneNumber) {
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
    fetch(`/api/addressBooks/${addressBookId}/view`)
        .then(response => response.json())
        .then(data => {
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
    document.getElementById('add-buddy-button').addEventListener('click', function(event) {
        event.preventDefault();
        const name = document.getElementById('buddy-name').value;
        const phone = document.getElementById('buddy-phone').value;
        addBuddy(addressBookId, name, phone);
    });
}

window.onload = initialize;