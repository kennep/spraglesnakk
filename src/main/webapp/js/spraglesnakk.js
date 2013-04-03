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
	
	if(!$('#username').val()) {
		$('#username').val(window.prompt('Kva er namet ditt?', 'Anynom feiging'));
	}
});

