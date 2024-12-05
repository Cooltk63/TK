int[] index = {0}; // Mutable counter

datalist.stream().forEach(c -> {
    log.info("Processing item: " + c);

    int forEachIndex = index[0]; // Access the current index value

    if (rw01Repository.findByPlSuplDateAndPlSuplBranchAndPlSuplId(
        quarterEndDate, loginUserData.get("branch_code"), c.get(6)) == null) {
        rw01.setPlSuplDetails(headingValuesSrNoArr[forEachIndex] + " " + headingValuesParticularsArr[forEachIndex]);
        rw01Repository.save(rw01);
    } else {
        Rw01 aa = ...; // Update logic here
        aa.setPlSuplDetails(headingValuesSrNoArr[forEachIndex] + " " + headingValuesParticularsArr[forEachIndex]);
        rw01Repository.save(aa);
    }

    index[0]++; // Increment index
});