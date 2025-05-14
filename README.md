IFRSArchives.handleQedDropdown = function () {
        const dropdown = document.getElementById('selectQED');
        const currentDate = new Date();
        const currentYear = currentDate.getFullYear();
        const currentMonth = currentDate.getMonth();
        const currentQuarter = currentMonth < 3 ? 3 : currentMonth < 6 ? 0 : currentMonth < 9 ? 1 : 2;
        const maxCurrentQuarter = currentQuarter - 1;
        const isQ1 = currentMonth < 3; // Q1 of a financial year ends in March
        let startYear = 2024;

        // End year is the financial year that is currently active
        const endYear = isQ1 ? currentYear : currentYear + 1;

        const quarters = [
            { label: "Q1 (April-June)", month: 5, day: 30 },
            { label: "Q2 (July-September)", month: 8, day: 30 },
            { label: "Q3 (October-December)", month: 11, day: 31 },
            { label: "Q4 (January-March)", month: 2, day: 31 },
        ];

        for (let year = startYear; year < endYear; year++) {
            quarters.forEach((quarter, index) => {
                // Stop adding if we've passed the previous quarter in the current financial year
                if(year === endYear - 1 && index >= maxCurrentQuarter) return;
                const fyStartYear = year;
                const fyEndYear = year + 1;
                const endDate = new Date(
                    quarter.month === 2 ? fyEndYear : fyStartYear,
                    quarter.month,
                    quarter.day
                );

                const formattedDate = `${endDate.getDate().toString().padStart(2, '0')}/${(endDate.getMonth() + 1).toString().padStart(2, '0')}/${endDate.getFullYear()}`;
                console.log(formattedDate);
                const option = document.createElement('option');
                option.value = formattedDate;
                option.textContent = `${formattedDate} ${quarter.label}`;
                dropdown.appendChild(option);
            });
        }
    };
