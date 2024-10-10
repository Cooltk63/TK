<table id="myTable">
    <tr>
        <td><input type="text" id="input1" name="field1" value="Editable Field"></td>
    </tr>
    <tr>
        <td><input type="text" id="input2" name="field2" value="Editable Field"></td>
    </tr>
    <tr>
        <td><input type="text" id="input3" name="field3" value="Editable Field"></td>
    </tr>
    <!-- Continue for inputs up to input12 -->
</table>

<!-- jQuery Library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<script>
    // Make input2 and input4 read-only
    $('#input2, #input4').attr('readonly', true);
</script>


<script>
    // Loop through input IDs 2 to 4 and make them read-only
    for (let i = 2; i <= 4; i++) {
        $('#input' + i).attr('readonly', true); // Targets input2, input3, and input4
    }
</script>