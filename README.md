Vulnerability Description in Detail :It was observed in application attacker can able to modifies the parameters in client-server request to manupulate application behavior.

Likely Impact:  An attacker might use this to perform unauthorized access to sensitive data. Unauthorized modification of data. Like changing the IDs or Price parameters in payment request.

Recommendation:It is recommended to implement validate all inputs on the server side to ensure data integrity.
Use strict type checking and enforce data format constraints. 

