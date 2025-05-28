



   Main Function/ Class 

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.log4j.Logger;
import com.tcs.controllers.MakerController;

public class SFTPThreadPool {
	
	static Logger log = Logger.getLogger(MakerController.class.getName());
	
	private static ExecutorService executor = Executors.newFixedThreadPool(5);

	public static void callSFTPWorker(String pathOfMoc,String mocFileName,String errorFileName) {
		//log.info("SFTP work started for "+ pathOfMoc);
		Runnable worker = new SFTPRunnable(pathOfMoc, mocFileName, errorFileName);
		executor.execute(worker);
		//executor.shutdown();
		if(!executor.isTerminated()){
//			System.out.println("Work Finished");
		}
	}

}

Class code where it used

 
 // Write Error file.
			if (!errorList.isEmpty()) {
				writeMOCFile(false, pathOfMoc, errorList, mocFileName, errorFileName);
				//log.info("Error File Written " + errorFileName);
			}

			//log.info("*********Thread called for SFTP****");
			SFTPThreadPool.callSFTPWorker(pathOfMoc, mocFileName, errorFileName);

			if (errorCount == 0 && insertedCount > 0) {
				displayMessage = insertedCount + " MOC entries accepted and pending with Checker.";
			} else if (errorCount > 0) {
				displayMessage =
						"CSV cann't be uploaded. Check the Error File. Make corrections and upload again.";
			}

   Likely Impact
The method sftpFile() in CriMarrSerivceImpl.java calls sleep()  on line 74. Thread management in a web application is forbidden in some circumstances and is always highly error prone.


Recommendation : Avoid managing threads directly from within the web application. Instead use standards such as message driven beans and the EJB timer service that are provided by the application container.
