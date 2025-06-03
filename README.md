Vulnerability
Unauthorized Download

Description
During the assessment  it was observed that the application file is accessible directly without authentication.

Impact
Able to access file directly without authentication can lead to information disclosure.

Recommendation
It is recommended to implement proper session management and authorization controls for downloaded files to ensure that access is restricted to authenticated and authorized users only.


Test steps done for finding vulnaribility
  Step 1: Log in using the User ID 1111111, navigate to the MOC tab, and download the sample file.

   Step 2: After downloading, copy the download link and open it in a new browser or incognito window. You will be able to download the file without authentication.

   Line of code written for download
   <a href="./document/moc.csv" class="btn btn-warning"
											target="_blank" download="moc.csv"><i
											class="fa fa-download"></i> download sample file </a>
									</div>

         Using the Spring MVC 4.3.4 & Jsp with Java -8
