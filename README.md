<td>
    <input type="text" id="tab1Title{{row.SRNO}}" name="NEW_TITLE"
           class="form-control"
           pattern="^[a-zA-Z0-9\s]*$"  <!-- Regex to allow only alphanumeric characters and spaces -->
           oninput="validateInput(this)"  <!-- Calling validateInput function on input change -->
           value="{{row.NEW_TITLE}}" maxlength="99">
</td>


xxx

// Function to validate the input
function validateInput(input) {
    const regex = /^[a-zA-Z0-9\s]*$/;  // Allow alphanumeric characters and spaces
    if (!regex.test(input.value)) {
        input.setCustomValidity('Special characters are not allowed');  // Set custom error message
    } else {
        input.setCustomValidity('');  // Clear error message
    }
}

// Function to prevent interaction if validation fails
document.addEventListener('click', function(e) {
    const inputField = document.querySelector('input[type="text"]:invalid');  // Get invalid input field
    if (inputField) {
        e.preventDefault();  // Prevent click on other elements
        alert('Please fix the input error before proceeding');  // Optionally show a message
    }
});