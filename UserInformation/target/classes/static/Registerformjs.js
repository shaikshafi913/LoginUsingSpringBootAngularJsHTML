var registerModule = angular.module('registerModule', [ 'ngRoute' ]);
registerModule
		.config(function($routeProvider, $httpProvider) {
			$routeProvider
			.when('/', {
				templateUrl : 'HelloForm.html',
				controller : 'home',

			}).when('/Login', {
				templateUrl : 'Login.html',
				controller : 'studentregisterform',

			}).otherwise({
				redirectTo: '/'
			});

			$httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';

		});

registerModule.controller(
				'studentregisterform',
				function($rootScope, $scope, $http, $location,$window) {

					$scope.authenticationFailStatus = true;
					$scope.error = false;
					$scope.credentials = {};

					$rootScope.login = function() {
						$scope.error = false;
						if (!($scope.credentials.username && $scope.credentials.password)
								|| ($scope.credentials.username == "" || $scope.credentials.password == "")) {
							$scope.authenticationFailStatus = false;
							$scope.error = true;
						} else {
							authenticate($scope.credentials, function() {
								if ($rootScope.authenticated) {
									$window.location.href = '/HelloForm.html';
									$scope.error = false;
								} else {
									//$location.path("/Login");
									$scope.error = "Please enter the correct credentials";
									$scope.credentials = {};
								}
							});
						}
					};

					var authenticate = function(credentials, callback) {
						// Creating the headers
						var headers = credentials ? {
							authorization : "Basic "
									+ btoa(credentials.username + ":"
											+ credentials.password)
						} : {};
						// Making the call
						$http
								.get("/user", {
									headers : headers
								})
								.success(
										function(response) {
//											alert("The credentials are correct");
											if (response) {
												$rootScope.userLoginId = (response.username) ? response.username
														: '';
												// Remembering that page is
												// authenticated.
												$rootScope.authenticated = true;
												$scope.error = false;
												// Call the configuration for
												// home page
											} else {
												$rootScope.authenticated = false;
											}
											callback && callback();
										}).error(function(data) {
									$rootScope.authenticated = false;
									callback && callback();
									$scope.credentials.passWord = "";
								});
					}

					$scope.register = function() {
						var User = $scope.User;
						var userList = [];
						userList.push(User);
						syncUsers(userList, true);
					}

					var headers = {
						"Content-Type" : "applicaton/json"
					};

					function syncUsers(userList, isInsert) {
						if (!isInsert)
							isInsert = false;
						else
							isInsert = true;
						$http.post(
								"usermanagement/admin/sync?insert=" + isInsert,
								userList, headers).success(function(response) {
							// if (data && data.length > 0) {
							srfToasty.info("User details saved successfully");
							// $scope.fetchUsers();
							$scope.clear();
							// }
						});
					}

				});

registerModule.controller('home', function($http, $state) {
	var self = this;
	$http.get('/home').then(function(response) {
		self.greeting = response.data;
	})
});
