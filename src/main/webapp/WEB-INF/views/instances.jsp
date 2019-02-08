<%--
  Created by IntelliJ IDEA.
  User: ME
  Date: 10/23/2017
  Time: 3:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp" %>

<script>

    var keycloak = Keycloak({
            realm: 'demo',
            url: 'https://accounts.pol.ge/auth',
            clientId: 'justice'
        });
        
        keycloak.init({ onLoad: 'login-required' }).success(function(authenticated) {
            console.log('authenticated')
        }).error(function() {
            alert('failed to initialize');
        });

        keycloak.updateToken(5).success(function(refreshed) {
        if (refreshed) {
            console.log('Token was successfully refreshed');
        } else {
          console.log('Token is still valid');
        }
    }).error(function() {
        console.log('Failed to refresh the token, or the session has expired');
    });


    app.controller("angController", function ($scope, $http, $filter) {

        $scope.loadMainData = function () {
            function getMainData(res) {
                $scope.list = res.data;
            }
            if(keycloak.idTokenParsed == null || keycloak.idTokenParsed == undefined || keycloak.idTokenParsed.preferred_username == null || keycloak.idTokenParsed.preferred_username == undefined){
    	        setTimeout(function () {
        	        $scope.$apply(function(){
                        ajaxCallWithHeaders($http, "courtInstances/get-courtinstances", null, getMainData,null, keycloak.idTokenParsed.preferred_username,keycloak.resourceAccess['justice-service'].roles,keycloak.idTokenParsed.name);
        	        });
        	    }, 1000);
            }else{
    	        ajaxCallWithHeaders($http, "courtInstances/get-courtinstances", null, getMainData,null, keycloak.idTokenParsed.preferred_username,keycloak.resourceAccess['justice-service'].roles,keycloak.idTokenParsed.name);
            }
        }

        $scope.loadMainData();

        $scope.remove = function (id) {
            if (confirm("დარწმუნებული ხართ რომ გსურთ წაშლა?")) {
                if (id != undefined) {
                    function resFnc(res) {
                        if (res.errorCode == 0) {
                            successMsg('ოპერაცია დასრულდა წარმატებით');
                            $scope.loadMainData();
                        }
                    }

                    ajaxCall($http, "courtInstances/delete-courtinstance?id=" + id, null, resFnc);
                }
            }
        };

        $scope.edit = function (id) {
            if (id != undefined) {
                var selected = $filter('filter')($scope.list, {instanceId: id}, true);
                $scope.request = selected[0];
            }
        };

        $scope.init = function () {
            $scope.request = null;
        };

        $scope.save = function () {
            function resFunc(res) {
                if (res.errorCode == 0) {
                    successMsg('ოპერაცია დასრულდა წარმატებით');
                    $scope.loadMainData();
                    closeModal('editModal');
                }
            }

            ajaxCall($http, "courtInstances/save-courtinstance", angular.toJson({
                id: $scope.request.instanceId,
                name: $scope.request.name
            }), resFunc);
        };

    });
</script>


<div class="modal fade bs-example-modal-lg" id="editModal" role="dialog" aria-labelledby="editModalLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="editModalLabel">შეიტანეთ ინფორმაცია</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <form class="form-horizontal" name="myForm">
                        <div class="form-group col-sm-10 ">
                            <label class="control-label col-sm-3">სასამართლო ინსტანცია</label>
                            <div class="col-sm-9">
                                <input type="text" ng-model="request.name"
                                       class="form-control input-sm">
                            </div>
                        </div>
                        <div class="form-group col-sm-10"></div>
                        <div class="form-group col-sm-10"></div>
                        <div class="form-group col-sm-12 text-center">
                            <a class="btn btn-app" ng-click="save()">
                                <i class="fa fa-save"></i> შენახვა
                            </a>
                        </div>

                    </form>
                </div>
            </div>
        </div>
    </div>
</div>


<div class="row">
    <div class="col-xs-10 col-xs-offset-1">
        <div class="box">
            <div class="box-header">
                <div class="col-md-2">
                    <c:if test="<%= isAdmin %>">
                        <button type="button" class="btn btn-block btn-primary btn-md" ng-click="init()"
                                data-toggle="modal"
                                data-target="#editModal">
                            <i class="fa fa-plus" aria-hidden="true"></i> &nbsp;
                            დამატება
                        </button>
                    </c:if>
                </div>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
                <table class="table table-bordered table-hover">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>სახელი</th>
                        <c:if test="<%= isAdmin %>">
                            <th class="col-md-3 text-center">Action</th>
                        </c:if>
                    </tr>
                    </thead>
                    <tbody>
                    <tr ng-repeat="r in list">
                        <td>{{r.instanceId}}</td>
                        <td>{{r.name}}</td>
                        <c:if test="<%= isAdmin %>">
                            <td class="text-center">
                                <a ng-click="edit(r.instanceId)" data-toggle="modal" data-target="#editModal"
                                   class="btn btn-xs">
                                    <i class="fa fa-pencil"></i>&nbsp;შეცვლა
                                </a>&nbsp;|&nbsp;
                                <a ng-click="remove(r.instanceId)" class="btn btn-xs">
                                    <i class="fa fa-trash-o"></i>&nbsp;წაშლა
                                </a>
                            </td>
                        </c:if>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<%@include file="footer.jsp" %>