String insertQuery = "Insert into ifrs_group_change_request(NEW_TITLE, NEW_CATEGORY, NEW_SUBCATEGORY,ACTION,PFID,FK_SRNO) VALUES (?, ?, ?, ?, ?, ?)";

                GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
                log.info("a");
                jdbcTemplate.update(conn -> {
                    String[] generatedColumns = {"ID"};
                    PreparedStatement prepareStatement = conn.prepareStatement(insertQuery, generatedColumns);
                    prepareStatement.setString(1, (String) jsonObjData.get("TITLE"));
                    prepareStatement.setString(2, (String) jsonObjData.get("CATEGORY"));
                    prepareStatement.setString(3, (String) jsonObjData.get("SUB_CATEGORY"));
                    prepareStatement.setString(4, "modify");
                    prepareStatement.setString(5, (String) jsonObjData.get("userId"));
                    prepareStatement.setString(6, (String) jsonObjData.get("SRNO"));
                    return prepareStatement;
                }, generatedKeyHolder);

                log.info("b");

                log.info("key generated" + generatedKeyHolder.getKey());

                return Objects.requireNonNull(generatedKeyHolder.getKey()).intValue();
            } catch (DataAccessException e) {
                log.info("inside catch block");
                log.info("Data access Exception occurred" + e.getMessage());
                return 0;
            }
