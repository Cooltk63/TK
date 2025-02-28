public Map<String, Object> getSaveBySftp(Map<String, Object> map) {
		log.info("Inside SC09DaoImpl getSaveBySftp");
		Map<String, Object> updatedTabData = new HashMap<>();
		String quarterEndDate = (String) map.get("qed");
		log.info("quarterEndDate:-"+quarterEndDate);///// quarterEndDate":"30/06/2024
		String circleCode = (String) map.get("circleCode");
		String reportId = (String) map.get("reportID");
		String reportName = (String) map.get("reportName");
		String yyyy = quarterEndDate.split("/")[2];
		String mm = quarterEndDate.split("/")[1];
		String dd = quarterEndDate.split("/")[0];
		String sessionDate = yyyy + mm + dd;   ///20230930
		String qDate = dd + mm + yyyy;  ////30092023
		int status =0;

		String branchCode = ccdpSftpDao.getBrcodeforsftp(circleCode, reportName);

		log.info("qDate- " + qDate);
		log.info("sessionDate- " + sessionDate);
		log.info("circleCode- " + circleCode);
		log.info("into Dao  reading & inserting file*********");

		//////////////////reading File and Validations///////////////////

		try {
			/*String mainPath = "/media/BS/CCDP/";
			String path = mainPath + qDate + "/SCH9_" + sessionDate + "_" + branchCode + ".txt";*/


			/// below lines for prod
			PropertiesConfiguration config = new PropertiesConfiguration("common.properties");
			String mainPath =  config.getProperty("ReportDirCCDP").toString();
			//String mainPath =  "/ccdp2bsa/" ;

			String path = mainPath + qDate + "/SCH_9_" + sessionDate + "_" + branchCode + ".txt";
			log.info("path" + path);


			List<String> lines = new ArrayList<String>();

			FileReader reader;
			BufferedReader bufferedReader;

			reader = new FileReader(path);
			bufferedReader = new BufferedReader(reader);
			StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				stringBuffer.append(line);
				if (!line.equalsIgnoreCase("")) {
					lines.add(line);
					stringBuffer.append("\n");
				}


			}
			SC09 sc09 = new SC09();
			String first = lines.get(0);
			log.info("first " + first);
			int linesSize = lines.size() - 1;
			String last = lines.get(linesSize);
			log.info("last:-" + last);
			String timeStamp = last.split("\\|")[1];
			log.info("timest:-"+timeStamp);
			int len = first.length();
			log.info("len- " + len);

			log.info("before FOR LOOP");
			for (String mycodes : lines) {
				log.info("mycodes-" + mycodes);

				String[] mycode1 = mycodes.split("\\|");
				log.info("length " + mycode1.length+ "mycode1:_"+Arrays.toString(mycode1));

				if(!(mycode1[0].trim()).equalsIgnoreCase("Generated at")) {
					log.info("into value line ");
					if ((!(mycode1[1].trim().matches("^(?:-?\\d{1,16}\\.\\d{1,2}|-?\\d{1,16})$"))) ||
							(!(mycode1[2].trim().matches("^(?:-?\\d{1,16}\\.\\d{1,2}|-?\\d{1,16})$"))) ||
							(!(mycode1[3].trim().matches("^(?:-?\\d{1,16}\\.\\d{1,2}|-?\\d{1,16})$"))) ||
							(!(mycode1[4].trim().matches("^(?:-?\\d{1,16}\\.\\d{1,2}|-?\\d{1,16})$")))
					) {
						log.info("into regex ");

						//cnt to check data exist or not
						int cnt=ccdpSftpDao.getCountdata(circleCode,quarterEndDate,reportName);
						String prevtime= ccdpSftpDao.getCCDPTimeStamp(circleCode,quarterEndDate,reportName);
						if(cnt >=1 ){
							updatedTabData.put("message", FETCH_DATA + prevtime);
							updatedTabData.put("fileAndDataStatus", 3);
							updatedTabData.put("status", true);
						}else{
							updatedTabData.put("message", "The data generated at " + timeStamp + " has invalid data. Kindly regenerate files at CCDP and try again");
							updatedTabData.put("fileAndDataStatus", 2);
							updatedTabData.put("status", false);
						}
						return updatedTabData;
					}
				}

				if (mycode1.length == 5  ) {
					if ((mycode1[0].trim()).equalsIgnoreCase("A11")) {
						sc09.setFacility_Standard_1(mycode1[1].trim());
						sc09.setFacility_SubStandard_1(mycode1[2].trim());
						sc09.setFacility_Doubtful_1(mycode1[3].trim());
						sc09.setFacility_Loss_1(mycode1[4].trim());
					} else if ((mycode1[0].trim()).equalsIgnoreCase("A12")) {
						sc09.setFacility_Standard_2(mycode1[1].trim());
						sc09.setFacility_SubStandard_2(mycode1[2].trim());
						sc09.setFacility_Doubtful_2(mycode1[3].trim());
						sc09.setFacility_Loss_2(mycode1[4].trim());
					} else if ((mycode1[0].trim()).equalsIgnoreCase("A13")) {
						sc09.setFacility_Standard_3(mycode1[1].trim());
						sc09.setFacility_SubStandard_3(mycode1[2].trim());
						sc09.setFacility_Doubtful_3(mycode1[3].trim());
						sc09.setFacility_Loss_3(mycode1[4].trim());
					} else if ((mycode1[0].trim()).equalsIgnoreCase("A21")) {
						sc09.setSecurity_Standard_1(mycode1[1].trim());
						sc09.setSecurity_SubStandard_1(mycode1[2].trim());
						sc09.setSecurity_Doubtful_1(mycode1[3].trim());
						sc09.setSecurity_Loss_1(mycode1[4].trim());
					} else if ((mycode1[0].trim()).equalsIgnoreCase("A22")) {
						sc09.setSecurity_Standard_2(mycode1[1].trim());
						sc09.setSecurity_SubStandard_2(mycode1[2].trim());
						sc09.setSecurity_Doubtful_2(mycode1[3].trim());
						sc09.setSecurity_Loss_2(mycode1[4].trim());
					} else if ((mycode1[0].trim()).equalsIgnoreCase("A23")) {
						sc09.setSecurity_Standard_3(mycode1[1].trim());
						sc09.setSecurity_SubStandard_3(mycode1[2].trim());
						sc09.setSecurity_Doubtful_3(mycode1[3].trim());
						sc09.setSecurity_Loss_3(mycode1[4].trim());
					} else if ((mycode1[0].trim()).equalsIgnoreCase("A31")) {
						sc09.setSector_Standard_a1(mycode1[1].trim());
						sc09.setSector_SubStandard_a1(mycode1[2].trim());
						sc09.setSector_Doubtful_a1(mycode1[3].trim());
						sc09.setSector_Loss_a1(mycode1[4].trim());
					} else if ((mycode1[0].trim()).equalsIgnoreCase("A32")) {
						sc09.setSector_Standard_a2(mycode1[1].trim());
						sc09.setSector_SubStandard_a2(mycode1[2].trim());
						sc09.setSector_Doubtful_a2(mycode1[3].trim());
						sc09.setSector_Loss_a2(mycode1[4].trim());
					} else if ((mycode1[0].trim()).equalsIgnoreCase("A33")) {
						sc09.setSector_Standard_a3(mycode1[1].trim());
						sc09.setSector_SubStandard_a3(mycode1[2].trim());
						sc09.setSector_Doubtful_a3(mycode1[3].trim());
						sc09.setSector_Loss_a3(mycode1[4].trim());
					} else if ((mycode1[0].trim()).equalsIgnoreCase("A34")) {
						sc09.setSector_Standard_a4(mycode1[1].trim());
						sc09.setSector_SubStandard_a4(mycode1[2].trim());
						sc09.setSector_Doubtful_a4(mycode1[3].trim());
						sc09.setSector_Loss_a4(mycode1[4].trim());
					}else if((mycode1[0].trim()).equalsIgnoreCase("Generated at")){

						sc09.setCcdpFiletimeStamp(mycode1[1].trim());
					}

					log.info("sc09:-"+sc09+"   size:-"+sc09.getSector_SubStandard_a1());
					log.info("timestamp:-"+sc09.getCcdpFiletimeStamp());
					status=1;
				}else if(mycodes.contains("Generated at")){
					log.info("into if gen");
					status=1;

				}else{
					log.info( "invalid data");
					status=0;
				}

			}
			log.info("status:-"+status);
			int updatetime = ccdpSftpDao.updateCCDPFiletime ( timeStamp,circleCode,quarterEndDate,reportName);
			if(status==1) {
				log.info("sc09:-" + sc09 + "   size:-" + sc09.getSector_SubStandard_a1());
				updatedTabData.put("sc09Data", sc09);
				updatedTabData.put("message", FETCH_DATA + timeStamp);
				updatedTabData.put("status", true);
				updatedTabData.put("fileAndDataStatus", 1);
			}else{


				int cnt=ccdpSftpDao.getCountdata(circleCode,quarterEndDate,reportName);
				String prevtime= ccdpSftpDao.getCCDPTimeStamp(circleCode,quarterEndDate,reportName);
				if(cnt>=1){
					log.info("into sts 3");
					updatedTabData.put("message", FETCH_DATA + prevtime);
					updatedTabData.put("fileAndDataStatus", 3);
					updatedTabData.put("status",true);
				}else{
					log.info("into sts 2");
					updatedTabData.put("message","The data generated at "+timeStamp+" has invalid data. Kindly regenerate files at CCDP and try again");
					updatedTabData.put("fileAndDataStatus", 2);
					updatedTabData.put("status",false);
				}

			}

			return updatedTabData;
		} catch (IOException e) {
			updatedTabData.put("message","The data generated has invalid data. Kindly regenerate files at CCDP and try again");
			updatedTabData.put("fileAndDataStatus", 2);
			updatedTabData.put("status",false);
			return updatedTabData;
			//throw new RuntimeException(e);

		}catch(Exception e){
			updatedTabData.put("message","The data generated has invalid data. Kindly regenerate files at CCDP and try again");
			updatedTabData.put("fileAndDataStatus", 2);
			updatedTabData.put("status",false);
			return updatedTabData;
		}
    }
