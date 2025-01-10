const roleMapping = {
    '52': { role: 'Circle Checker', state: 'circlechecker_module.module' },
    '51': { role: 'Circle Maker', state: 'circle_module.module' },
    '53': { role: 'Circle Approver', state: 'circle_auditor.home' },
    '61': { role: 'FRT Maker', state: 'frt_maker.home' },
    '62': { role: 'FRT Checker', state: 'frt_admin.home' },
    '71': { role: 'Super Maker', state: 'super_maker.home' },
    '72': { role: 'Super Checker', state: 'super_admin.home' },
    '81': { role: 'gitc User', state: 'gitc_user.home' },
    '91': { role: 'ifrs User', state: 'ifrs_user.home' }
};

if (app.user.status === 'A' && roleMapping[app.user.capacity]) {
    const { role, state } = roleMapping[app.user.capacity];
    Idle.watch();
    $scope.started = true;
    app.isUserLoggedIn = true;
    app.user.role = role;
    var user = AES256.encrypt(JSON.stringify(app.user));
    AuthService.createJWTToken(user, app.user.token);
    AuthService.setCredentials();
    $rootScope.$broadcast(AUTH_EVENTS.loginSuccess);
    $state.go(state);
} else {
    Auth.logout();
    $location.path('/login');
}