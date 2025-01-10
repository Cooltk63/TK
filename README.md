// Function to validate the input
function validateInput(input) {
    const regex = /^[a-zA-Z0-9\s]*$/;  // Regex to allow only alphanumeric characters and spaces
    const errorMessage = 'Special characters are not allowed';

    if (!regex.test(input.value)) {
        input.setCustomValidity(errorMessage);  // Set custom error message
    } else {
        input.setCustomValidity('');  // Clear error message
    }

    // Disable other actions if the input is invalid
    managePageInteraction(input);
}

// Function to disable interactions with other elements if the input is invalid
function managePageInteraction(input) {
    const isInvalid = input.validity.invalid;

    // Disable the submit button or any other action if input is invalid
    const buttons = document.querySelectorAll('button, input[type="button"], input[type="submit"]');
    buttons.forEach(button => {
        if (isInvalid) {
            button.disabled = true;  // Disable button if input is invalid
        } else {
            button.disabled = false;  // Enable button if input is valid
        }
    });

    // Optionally, disable other form elements like links
    const clickableElements = document.querySelectorAll('a, button, input');
    clickableElements.forEach(element => {
        if (isInvalid) {
            element.setAttribute('disabled', 'true');  // Disable clickable elements
        } else {
            element.removeAttribute('disabled');
        }
    });
}

// Event listener to prevent interactions when the page is invalid
document.addEventListener('click', function(e) {
    const invalidInput = document.querySelector('input[type="text"]:invalid');
    if (invalidInput) {
        e.preventDefault();  // Prevent click actions
        alert('Please fix the input error before proceeding');
    }
});