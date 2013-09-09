$(document).ready(function() {
    "use strict";

	$('#showhide').click(function() {
		if(!$('#presentation').hasClass('full')) {
			$('#presentation,#history').addClass('full');
			$('#showhide i').removeClass('icon-chevron-up');
			$('#showhide i').addClass('icon-chevron-down');
		} else {
			$('#presentation,#history').removeClass('full');
			$('#showhide i').addClass('icon-chevron-up');
			$('#showhide i').removeClass('icon-chevron-down');
		}
	});

	var username = $('#username').val();
	if(!username) {
		var defaultUsername = 'Anonym feiging';
		username = window.prompt('Kva er namet ditt?', defaultUsername);
		if(!username) {
			username = defaultUsername;
		}
		$('#username').val(username);
	}
	$('#chatHistory').append('<p>Du er no logga inn som <b>' + username + '</b>.</p>');

    // Atmosphere initialization
    var request = { url: 'chat',
        contentType: 'application/json',
        trackMessageLength: true,
        shared: true,
        transport: 'websocket',
        fallbackTransport: 'long-polling'};

    request.onOpen = function(response) {
    	$('#chatHistory').append('<p>Kopla til med <b>' + response.transport + '</b>.</p>');
    };

    request.onTransportFailure = function(errorMsg, request) {
    	$('#chatHistory').append('<p>Feil: <b>' + errorMsg + '</b>.</p>');
        if (window.EventSource) {
           request.fallbackTransport = "sse";
        }
    };

    request.onMessage = function (response) {
        var message = response.responseBody;
        var json;
        try {
            json = jQuery.parseJSON(message);
        } catch(e) {
            $('#chatHistory').append('<p>Fikk noko som ikkje såg ut som JSON: ' + message + '</p>');
            return;
        }
        $('#chatHistory').append('<p><b>' + json.username + ' sa:</b> ' + json.message + '</p>');
    };

    var subscription = $.atmosphere.subscribe(request);

    $('#send').click(function(e) {
        subscription.push(jQuery.stringifyJSON({'username': username, 'message': $('#message').val()}));
        $('#message').val('');
        return false;
    });

});

