SELECT\n" +
            "nvl(to_number(cont_liab_prov_lst_yr), 0) opening," +
            "nvl(to_number(cont_liab_prov_add), 0) additions," +
            "nvl(to_number(cont_liab_prov_used), 0) ggg," +
            "nvl(to_number(cont_liab_prov_unused), 0) reversals " +
            "FROM " +
            "crs_cont_liab_prov\n" +
            "WHERE\n" +
            "cont_liab_prov_branch = :branch_code\n" +
            "AND cont_liab_prov_date = to_date(:quarterEndDate,'dd/mm/yyyy')\n" +
            "AND cont_liab_prov_itemid = '18' 

            I need the  "nvl(to_number(cont_liab_prov_used), 0) ggg, +   "nvl(to_number(cont_liab_prov_unused), 0) reversals " + columns additioin in separate column as name sumofUnsed.
