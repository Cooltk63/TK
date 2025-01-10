<td>
    <input type="text" id="tab1Title{{row.SRNO}}" name="NEW_TITLE"
           class="form-control"
           ng-model="row.NEW_TITLE"
           ng-blur="validateInput(row)" <!-- Trigger validation on blur -->
           value="{{row.NEW_TITLE}}" maxlength="99">
</td>


$scope.validateInput = function(row) {
    const regex = /^[a-zA-Z0-9\s]*$/;  // Regex to allow alphanumeric characters and spaces

    // Check if input matches the regex
    if (!regex.test(row.NEW_TITLE)) {
        alert('Special characters are not allowed');
        row.NEW_TITLE = '';  // Clear input value
    } else {
        // Enable buttons or other actions
        angular.element('button').prop('disabled', false);
    }
};