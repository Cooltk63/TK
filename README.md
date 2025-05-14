const generateQEDOptions = () => {
  const currentDate = new Date();
  const currentYear = currentDate.getFullYear();
  const currentMonth = currentDate.getMonth();
  const currentQuarter = currentMonth < 3 ? 3 : currentMonth < 6 ? 0 : currentMonth < 9 ? 1 : 2;
  const maxCurrentQuarter = currentQuarter - 1;
  const isQ1 = currentMonth < 3;
  const startYear = 2024;
  const endYear = isQ1 ? currentYear : currentYear + 1;

  const quarters = [
    { label: "Q1 (April-June)", month: 5, day: 30 },
    { label: "Q2 (July-September)", month: 8, day: 30 },
    { label: "Q3 (October-December)", month: 11, day: 31 },
    { label: "Q4 (January-March)", month: 2, day: 31 },
  ];

  const options = [];

  for (let year = startYear; year < endYear; year++) {
    quarters.forEach((quarter, index) => {
      if (year === endYear - 1 && index >= maxCurrentQuarter) return;

      const fyStartYear = year;
      const fyEndYear = year + 1;
      const endDate = new Date(
        quarter.month === 2 ? fyEndYear : fyStartYear,
        quarter.month,
        quarter.day
      );

      const formattedDate = `${endDate.getDate().toString().padStart(2, '0')}/${(endDate.getMonth() + 1).toString().padStart(2, '0')}/${endDate.getFullYear()}`;
      options.push({ value: formattedDate, label: `${formattedDate} ${quarter.label}` });
    });
  }

  return options;
};