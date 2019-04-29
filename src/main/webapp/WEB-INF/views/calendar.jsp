<%--
  Created by IntelliJ IDEA.
  User: ME
  Date: 10/23/2017
  Time: 3:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp" %>
<link href='resources/calendar/core/main.css' rel='stylesheet'/>
<link href='resources/calendar/daygrid/main.css' rel='stylesheet'/>
<link href='resources/calendar/timegrid/main.css' rel='stylesheet'/>
<link href='resources/calendar/list/main.css' rel='stylesheet'/>

<script src='resources/calendar/core/main.js'></script>
<script src='resources/calendar/core/ka.js'></script>
<script src='resources/calendar/daygrid/main.js'></script>
<script src='resources/calendar/timegrid/main.js'></script>
<script src='resources/calendar/list/main.js'></script>
<script src='resources/calendar/interaction/main.js'></script>


<script>

    // var keycloak = Keycloak({
    //         realm: 'icmia',
    //         url: 'https://accounts.pol.ge/auth',
    //         clientId: 'justice'
    //     });
    //
    //     keycloak.init({ onLoad: 'login-required' }).success(function(authenticated) {
    //         console.log('authenticated')
    //     }).error(function() {
    //         alert('failed to initialize');
    //     });
    //
    //     keycloak.updateToken(5).success(function(refreshed) {
    //     if (refreshed) {
    //         console.log('Token was successfully refreshed');
    //     } else {
    //       console.log('Token is still valid');
    //     }
    // }).error(function() {
    //     console.log('Failed to refresh the token, or the session has expired');
    // });

    var keycloak = {};
    keycloak.resourceAccess = {};
    keycloak.resourceAccess['justice-service'] = {};
    keycloak.resourceAccess['justice-service'].roles = ["JUSTICE_SIP_ADMIN", "JUSTICE_ADMIN", "JUSTICE_OPERATOR", "JUSTICE_SIP_OPERATOR"];
    keycloak.idTokenParsed = {
        acr: "1",
        aud: "justice",
        auth_time: 1553603458,
        azp: "justice",
        exp: 1553604658,
        family_name: "ნაჭყებია",
        given_name: "ალეკო",
        iat: 1553603458,
        iss: "https://accounts.pol.ge/auth/realms/icmia",
        jti: "5b74457f-9562-4e50-bc4e-3bc9a89e9b5e",
        name: "aleko nachyebia",
        nbf: 0,
        nonce: "8aa09bad-8a6f-44a3-819f-8c486b8b815d",
        preferred_username: "nachyebiaa",
        session_state: "b3be4d2e-25a2-424d-99ef-14ab80afd658",
        sub: "f:0221ff4d-ecf3-4c15-9ab7-483b870a7df4:75809",
        typ: "ID"
    };


    app.controller("angController", function ($scope, $http, $filter) {

        $scope.loadMainData = function () {
            function getMainData(res) {
                $scope.list = res.data;
            }

            if (keycloak.idTokenParsed == null || keycloak.idTokenParsed == undefined || keycloak.idTokenParsed.preferred_username == null || keycloak.idTokenParsed.preferred_username == undefined) {
                setTimeout(function () {
                    $scope.$apply(function () {
                        ajaxCallWithHeaders($http, "boards/get-boards", null, getMainData, null, keycloak.idTokenParsed.preferred_username, keycloak.resourceAccess['justice-service'].roles, keycloak.idTokenParsed.name);
                    });
                }, 1000);
            } else {
                ajaxCallWithHeaders($http, "boards/get-boards", null, getMainData, null, keycloak.idTokenParsed.preferred_username, keycloak.resourceAccess['justice-service'].roles, keycloak.idTokenParsed.name);
            }
        }

        $scope.loadMainData();


    });

    document.addEventListener('DOMContentLoaded', function () {
        var calendarEl = document.getElementById('calendar');

        var calendar = new FullCalendar.Calendar(calendarEl, {
            plugins: ['interaction', 'dayGrid', 'timeGrid', 'list'],
            header: {
                left: 'prev,next today',
                center: 'title',
                right: 'dayGridMonth,timeGridWeek,timeGridDay,listMonth'
            },
            defaultDate: new Date().toJSON().slice(0, 10).replace(/-/g, '-'),
            navLinks: true,
            locale: 'ka',
            events: [
                {
                    title: 'All Day Event',
                    start: '2019-04-01'
                },
                // {
                //     title: 'Long Event',
                //     start: '2019-04-07',
                //     end: '2019-04-10'
                // },
                {
                    title: 'Meeting',
                    start: '2019-04-12T10:30:00'
                },
                {
                    title: '1',
                    start: '2019-04-12T12:00:00'
                },
                {
                    title: '2',
                    start: '2019-04-12T14:30:00'
                },
                {
                    title: '3',
                    start: '2019-04-12T17:30:00'
                },
                {
                    title: '4',
                    start: '2019-04-12T20:00:00'
                }
            ]
        });

        calendar.render();
    });
</script>

<div id='calendar'></div>

<%@include file="footer.jsp" %>