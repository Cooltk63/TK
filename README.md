// 🔥🔥🔥 [SECURE-MAIN-1] ENHANCED TOKEN VALIDATION DURING APP INITIALIZATION 🔥🔥🔥
if (Auth.isLoggedIn()) {
    var token = AuthToken.getToken();
    if (token) {
        try {
            var payload = JSON.parse(atob(token.split('.')[1]));  // 🔑 DECODE TOKEN PAYLOAD
            var currentTime = Math.floor(Date.now() / 1000);

            if (payload.exp && payload.exp < currentTime) {       // ⏳ CHECK IF TOKEN EXPIRED
                console.warn('Token expired during initialization.');
                Auth.logout();  // 🚪 LOGOUT IF TOKEN IS EXPIRED
            }
        } catch (e) {
            console.error('Invalid token detected:', e.message);  // ❗ HANDLE INVALID TOKENS
            Auth.logout();
        }
    }
}

xxxx

// 🔥🔥🔥 [SECURE-MAIN-2] VALIDATE THE TOKEN AFTER LOGIN 🔥🔥🔥
var token = data.token;
try {
    var payload = JSON.parse(atob(token.split('.')[1]));  // 🔑 DECODE TOKEN PAYLOAD
    var currentTime = Math.floor(Date.now() / 1000);

    if (payload.exp && payload.exp < currentTime) {       // ⏳ CHECK IF TOKEN EXPIRED
        console.warn('Received expired token during login.');
        Auth.logout();  // 🚪 LOGOUT IF EXPIRED
    } else {
        app.user.token = token;                           // ✅ STORE ONLY VALID TOKENS
        AuthService.createJWTToken(app.user, token);
        AuthService.setCredentials();
    }
} catch (e) {
    console.error('Invalid token during login:', e.message);  // ❗ HANDLE INVALID TOKENS
    Auth.logout();
}


xxxx

// 🔥🔥🔥 [SECURE-MAIN-3] PERIODIC SESSION CHECK WITH TOKEN VALIDATION 🔥🔥🔥
app.checkSession = function() {
    if (Auth.isLoggedIn()) {
        interval = $interval(function() {
            var token = AuthToken.getToken();
            if (token) {
                try {
                    var payload = JSON.parse(atob(token.split('.')[1]));  // 🔑 DECODE TOKEN PAYLOAD
                    var currentTime = Math.floor(Date.now() / 1000);

                    if (payload.exp && payload.exp < currentTime) {       // ⏳ CHECK IF TOKEN EXPIRED
                        console.warn('Session expired. Logging out.');
                        showModal(1);  // 🚨 SHOW SESSION TIMEOUT WARNING
                        $interval.cancel(interval);
                    }
                } catch (e) {
                    console.error('Invalid token detected during session check:', e.message);  // ❗ HANDLE INVALID TOKENS
                    Auth.logout();
                }
            }
        }, 5000);  // 🔄 CHECK EVERY 5 SECONDS
    }
};