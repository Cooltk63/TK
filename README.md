AtomicInteger index = new AtomicInteger(0);

datalist.stream().forEach((c) -> {
                            log.info("into for each Tab 111 >> " + c);
                            Rw01 saveR01 = new Rw01();
                            Rw01 aa = rw01Repository.findByPlSuplDateAndPlSuplBranchAndPlSuplId(quarterEndDate, loginUserData.get("branch_code"), c.get(6));
                            log.info("aa Tab 111 -" + aa);

                            
                            int forEachIndex = index.getAndIncrement();
                            if (aa == null) {
                                System.out.println("Inside Ifff...11111");
                                rw01.setPlSuplId(c.get(6));
                                rw01.setPlSuplBranch(loginUserData.get("branch_code"));
                                rw01.setPlSuplCy(c.get(4));
                                rw01.setPlSuplDate(quarterEndDate);
                                rw01.setPlSuplPy(c.get(0));
                                rw01.setReportMasterListIdFk(String.valueOf(data.get("submissionId")));
                                // rw01.setPlSuplDetails(headingValuesSrNoArr[index.getAndIncrement()] + " " + headingValuesParticularsArr[index.getAndIncrement()]);
                                rw01.setPlSuplDetails(headingValuesSrNoArr[forEachIndex] + " " + headingValuesParticularsArr[forEachIndex]);

                                log.info("rw01.submissionId:-" + rw01.getReportMasterListIdFk());
                                saveR01 = rw01Repository.save(rw01);
                            } else {
                                System.out.println("Inside lese ..22222");
                                log.info("into elseeeee Tab 111");
                                aa.setPlSuplPy(c.get(0));
                                System.out.println("Inside lese ..3333");
                                aa.setPlSuplCy(c.get(4));

                                System.out.println("Inside lese ..index" + index);

                                // aa.setPlSuplDetails(headingValuesSrNoArr[index.getAndIncrement()] + " " + headingValuesParticularsArr[index.getAndIncrement()]);
                                aa.setPlSuplDetails(headingValuesSrNoArr[forEachIndex] + " " + headingValuesParticularsArr[forEachIndex]);


                                System.out.println("Inside lese ..44444");
                                log.info("final aa" + aa);
                                saveR01 = rw01Repository.save(aa);
                            }

                            // index.getAndIncrement();
                            // forEachIndex++;

                });


i have initilized atomic initeger to 0
but on first occurance of index.getAndIncrement() it is printing 1 instead of 0
