EX8pme9taApNvzZj_ne5cWYBI1kwc-vNvFNbNADqS1DauQ?e=RNsL2f&wdOrigin=TEAMS-MAGLEV.teams_ns.rwc&wdExp=TEAMS-TREATMENT&wdhostclicktime=1741861268042&web=1:2 Refused to connect to 'blob:https://crsuat.info.sbi/51f99a00-f68f-4161-a4b9-446732c6fbde' because it violates the following Content Security Policy directive: "default-src 'self'". Note that 'connect-src' was not explicitly set, so 'default-


This is the heraders i have added 

add_header X-XSS-Protection "1; mode=block";
add_header X-Frame-Options "DENY" always;
add_header X-Content-Type-Options "nosniff" always;
add_header Referrer-Policy "strict-origin-when-cross-origin" always;
add_header Content-Security-Policy "default-src 'self'; script-src 'self' 'unsafe-inline'; style-src 'self' 'unsafe-inline'; font-src 'self'; img-src 'self';" always;
