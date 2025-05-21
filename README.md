This is the function inside Utilities.java file 
public static Object deserialize(String path) throws IOException, ClassNotFoundException {
		ObjectInputStream objInpStream =  new ObjectInputStream(new FileInputStream(new File(path)));
		return objInpStream.readObject();
	}


Line of code where it calling from
String[] RelatedFiles = getReports(printObjFile.get(fileCount).toString(), "jrprint");
            jPrintList.clear();
            if (RelatedFiles.length > 0) {
                for (int idx = 0; idx < RelatedFiles.length; idx++) {
                    JasperPrint jrprint = (JasperPrint) Utilities.deserialize(RelatedFiles[idx]);
                    jPrintMap.put(new Integer(idx), jrprint);
                    jPrintList.add(jPrintMap.get(new Integer(idx)));
                }


                
