SELECT \n" +
            "    STND_ASTS_NAME_OF_BORROWER,\n" +
            "    STND_ASTS_INFRA_NON_INFRA,\n" +
            "    COALESCE(\n" +
            "        CASE \n" +
            "            WHEN STND_ASTS_INFRA_NON_INFRA = 'FOR INFRA' THEN STND_ASTS_INFRA_WITHIN2YRS \n" +
            "            ELSE STND_ASTS_NONINFRA_WITHIN1YR \n" +
            "        END, \n" +
            "        'NULL'\n" +
            "    ) AS STND_ASTS_WITHIN_1_OR_2_YRS,\n" +
            "    COALESCE(\n" +
            "        CASE \n" +
            "            WHEN STND_ASTS_INFRA_NON_INFRA = 'FOR INFRA' THEN STND_ASTS_INFRA_ACCTS2YRS \n" +
            "            ELSE STND_ASTS_NONINFRA_ACCTS1YR \n" +
            "        END, \n" +
            "        'NULL'\n" +
            "    ) AS STND_ASTS_ACCTS_1_OR_2_YRS,\n" +
            "    STND_ASSETS_SEQ AS ROWSEQ, \n" +
            "    STND_ASSETS_SEQ,\n" +
            "    'false'\n" +
            "FROM \n" +
            "    CRS_STND_ASSETS \n" +
            "WHERE \n" +
            "    REPORT_MASTER_LIST_ID_FK =:submissionId \n" +
            "ORDER BY \n" +
            "    STND_ASSETS_SEQ
