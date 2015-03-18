$(document).ready(
	function() {
		$('.errorintroduced').hide();
		$('.errordiscontinued').hide();
		$('#btnSubmit').click(function(event) {
			var dtValIn = $('#introduced').val();
			var dtValDis = $('#discontinued').val();

			if (ValidateDate(dtValIn)) {
				$('.errorintroduced').hide();
			} else {
				alert(strings['errorFormatIntro']);
				$('.errorintroduced').show();
				event.preventDefault();
			}

			if (ValidateDate(dtValDis)) {
				$('.errordiscontinued').hide();
			} else {
				alert(strings['errorFormatDisc']);
				$('.errordiscontinued').show();
				event.preventDefault();
			}
		});

	function ValidateDate(dtValue) {
		if (dtValue == "") {
			return true;
		}
		if (dtValue == null) {
			return true;
		}
		var dtRegex = new RegExp(strings['regex']);
		return dtRegex.test(dtValue);
	}
});
