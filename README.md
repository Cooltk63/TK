 // Generate the salt and store it in the users cache
        String salt = RandomStringUtils.random(20, 0, 0, true, true, null, new SecureRandom());
        csrfPreventionSaltCache.put(salt, Boolean.TRUE);

        // Add the salt to the current request so it can be used
        // by the page rendered in this request
        httpRes.setHeader("csrfPreventionSalt",salt);

        httpReq.getSession().setAttribute("csrfPreventionSalt", salt);
