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
				alert(strings['errorFormat']);
				$('.errorintroduced').show();
				event.preventDefault();
			}

			if (ValidateDate(dtValDis)) {
				$('.errordiscontinued').hide();
			} else {
				alert(strings['errorFormat']);
				$('.errordiscontinued').show();
				event.preventDefault();
			}
		});

	function ValidateDate(dtValue) {
		if (dtValue == "") {
			return true;
		}
		var dtRegex = new RegExp("^(19|20)[0-9][0-9](-)((0[1-9])|(1[0-2]))(-)((0[1-9])|([1-2][0-9])|(3[0-1]))(T|\\s)(([0-1][0-9])|(2[0-3])):([0-5][0-9])");
		return dtRegex.test(dtValue);
	}
});
