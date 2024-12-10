 // Setting Entity Data for Query
            Workflow entity=setEntity("Rejected",data,loginUserData);

            This the code method with above provided line

            // For Setting Entity Values to Bean Object
   private Workflow setEntity(String status,Map<String,Object>data,Map<String,String>loginUserData)
   {
       Workflow entity=new Workflow();
       int submissionId=Integer.parseInt(data.get("submissionId").toString());


       SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
       Date date=new Date();
       dateFormat.format(date);
       Date currentDate=new Date();
       try {
            currentDate=dateFormat.parse(dateFormat.format(date));
       } catch (ParseException e) {
           throw new RuntimeException(e);
       }

       entity.setSubmissionIdFK(submissionId);
       entity.setPendingWith(" ");
       entity.setLastAction(" ");
       entity.setStatus(status);
       entity.setActionBy(loginUserData.get("pf_number"));
       entity.setRemarks(" ");
       entity.setLastActionDate(currentDate);
       return entity;
   }

