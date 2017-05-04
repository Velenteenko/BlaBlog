angular.module('ngBoilerplate.account',['ui.router'])
  .config(function($stateProvider) {
    $stateProvider.state('login', {
        url: '/login',
        views : {
            'main' : {
                templateUrl : 'account/login.tpl.html',
                controller: 'LoginCtrl'
            }
        },
        data : {pageTitle : 'Login'}
    })
        .state('register', {
            url: '/register',
            views: {
                'main': {
                    templateUrl: 'account/register.tpl.html',
                    controller : 'RegistrationCtrl'
                }
            },
            data: {pageTitle: 'Registration'}
        }
        );
})
    .factory('sessionService', function () {
        var session = {};

        session.login = function (data) {

            alert('User logged in with Name:' + data.name +' Pass: '+data.password);

            localStorage.setItem("session",data);
        };
        
        session.logout = function () {
          localStorage.removeItem("session");
        };

        session.isLoggedIn = function () {
            return localStorage.getItem("session") !== null;
        };

        return session;
    })
    .controller('LoginCtrl', function($scope, sessionService, $state) {
        $scope.login = function() {
            sessionService.login($scope.account);
            $state.go("home");
        };
    })
    .controller('RegistrationCtrl', function($scope, sessionService, $state) {
        $scope.registration = function() {
            sessionService.login($scope.reg);
            $state.go("about");
        };
    });