$(document).ready(function() {
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
		$('#username').val(defaultUsername);
	}
	$('#chatHistory').append('<p>Du er no logga inn som <b>' + username + '</b>.</p>');
});

