List<List<String>> finalList = new ArrayList<>();
        List<String> rowData=new ArrayList<>();

        rowData.add(0,"");
        rowData.add(1,"");
        rowData.add(2,"");
        rowData.add(3,"");
        rowData.add(4,"");
        rowData.add(5,"");
        rowData.add(6,"");
        rowData.add(7, "");
        rowData.add(8, "");
        rowData.add(9,"false");

        for(int j=0; j<7;j++)
        {
                {
                    rowData.set(7, String.valueOf(j));
                    rowData.set(8, String.valueOf(j));

                }
            finalList.add(j, rowData);
        }

        System.out.println("Value of finalList :"+finalList);

    }


    I had this list i wanted the row.Data list 7th & 8th Position index set for each list from 1 to 7 so give me the for loop code as i requested
