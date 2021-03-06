(function() {
    'use strict';

    angular
        .module('shiftworkApp')
        .controller('EmployeeDetailController', EmployeeDetailController);

    EmployeeDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'entity', 'Employee', 'Contract', 'EmployeeDayOffRequest', 'EmployeeDayOnRequest', 'EmployeeShiftOffRequest', 'EmployeeShiftOnRequest', 'EmployeeLeaveAbsence'];

    function EmployeeDetailController($scope, $rootScope, $stateParams, entity, Employee, Contract, EmployeeDayOffRequest, EmployeeDayOnRequest, EmployeeShiftOffRequest, EmployeeShiftOnRequest, EmployeeLeaveAbsence) {
        var vm = this;
        vm.employee = entity;
        
        var unsubscribe = $rootScope.$on('shiftworkApp:employeeUpdate', function(event, result) {
            vm.employee = result;
        });
        $scope.$on('$destroy', unsubscribe);

    }
})();
