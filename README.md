// [CHANGE-APP-1] Secure Token Validation and Attachment in HTTP Requests
app.config(function ($httpProvider) {
    $httpProvider.interceptors.push(function ($q, $window) {
        return {
            request: function (config) {
                // [CHANGE-APP-2] Retrieve token from localStorage
                var token = $window.localStorage.getItem('token');

                if (token) {
                    try {
                        // [CHANGE-APP-3] Decode the token payload for validation
                        var payload = JSON.parse(atob(token.split('.')[1]));
                        var currentTime = Math.floor(Date.now() / 1000);

                        // [CHANGE-APP-4] Check if the token is expired
                        if (payload.exp && payload.exp < currentTime) {
                            console.warn('Token has expired'); // Log expiration
                            $window.localStorage.removeItem('token'); // Remove expired token
                        } else {
                            // [CHANGE-APP-5] Attach valid Bearer token to headers
                            config.headers.Authorization = 'Bearer ' + token;
                        }
                    } catch (e) {
                        // [CHANGE-APP-6] Handle invalid token format
                        console.error('Invalid token format:', e.message);
                        $window.localStorage.removeItem('token');
                    }
                }

                return config;
            },
            responseError: function (rejection) {
                // [CHANGE-APP-7] Handle Unauthorized (401) errors
                if (rejection.status === 401) {
                    console.warn('Unauthorized access - Token may be invalid or expired');
                    $window.localStorage.removeItem('token'); // Clear invalid token
                }
                return $q.reject(rejection);
            }
        };
    });
});