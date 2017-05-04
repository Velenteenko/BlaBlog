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
    .controller('LoginCtrl', function($scope) {
        $scope.login = function() {
            alert('User logged in with Name:' + $scope.account.name +' Pass: '+$scope.account.password);
        };
    })
    .controller('RegistrationCtrl', function($scope) {
        $scope.registration = function() {
            alert('User registered with Name:' + $scope.reg.name +' Pass: '+$scope.reg.password);
        };
    });