// Validating the FRN already existed in Impanelment or not
            int FRNCount = empanelmentRepository.checkFrnCount(data.get("FRN").toString(),loggeduser, data.get("FINANCIAL_YEAR").toString());
            log.info("FRN Count "+FRNCount);
            if (FRNCount == 0) {
               Process the FRN data for Saving inside Master Tables
               }
               else
               {
               write the text file with frn data and with error why it can't be added to FRN Master Data :: FRN Alredy Empaneld 
               }


        
