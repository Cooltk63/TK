    @Query(nativeQuery = true, value = "SELECT COALESCE(\n" +
            "    (SELECT MASTER_TEMPLATE \n" +
            "       FROM IAM_MASTER_TEMPLATE \n" +
            "      WHERE MASTER_ID = (SELECT MAX(MASTER_ID) \n" +
            "                           FROM IAM_MASTER_TEMPLATE) \n" +
            "        AND UPDATED_BY = :userId ),\n" +
            "    (SELECT MASTER_TEMPLATE \n" +
            "       FROM IAM_MASTER_TEMPLATE \n" +
            "      WHERE TEMPLATE_FLAG = 'D' \n" +
            "      FETCH FIRST 1 ROWS ONLY)\n" +
            ") AS MASTER_TEMPLATE\n" +
            "FROM dual")
    String gettemplate(@Param("userId")String userId);

    ORA-01722: invalid number
01722. 00000 -  "invalid number"
*Cause:    The specified number was invalid.
*Action:   Specify a valid number.
