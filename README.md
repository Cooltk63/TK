 private boolean updateMasterList(Map<String, Object> payload)
    {
        log.info("inside updateMasterList");
        Map<String, Object> data = (Map<String, Object>) payload.get("data");
        Map<String, Object> loginuserData = (Map<String, Object>) payload.get("user");

        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        int userId=Integer.parseInt(loginuserData.get("userId").toString());
        MasterTemplate entity = new MasterTemplate();

        try {
            entity.setMastertemplatedata(data.get("MASTER_TEMPLATE").toString());
            entity.setUpdatedby(userId);
            entity.setUpdatedtime(formatter.format(currentDateTime).toString());
            entity.setTemplateflag("F");

            MasterTemplate saved=masterTemplateRepository.save(entity);

//            log.info("Returned Saved Result ::"+saved.getMastertemplateid());
        } catch (Exception e) {
            log.error("Exception occured while saving MasterTemplate");
        }

        return false;
    }
