$(function() {
	var outputText = "";
	var descriptionText = "";

	var refreshOutputs = function() {
//		console.log(outputText);
		outputText = String(outputText).replace(/\s{2,}/g, " ");
		$("#outputText").val(outputText);
		$("#descriptionText").html(descriptionText);
	}

	var isOperator = function(s) {
		return (s == "+" || s == "-" || s == "*" || s == "/" || s == "^");
	}

	$("#asin").click(function() {
		outputText += " asin(";
		descriptionText = "<b>asin(number)</b>: Calculate arcsine of a number"
		refreshOutputs();
	});
	$("#sin").click(function() {
		outputText += " sin(";
		descriptionText = "<b>sin(number)</b>: Calculate sine of a number"
		refreshOutputs();
	});
	$("#lb").click(function() {
		outputText += " (";
		descriptionText = "";
		refreshOutputs();
	});
	$("#rb").click(function() {
		outputText += ") ";
		descriptionText = "";
		refreshOutputs();
	});
	$("#backspace").click(function() {

		var i = outputText.trim().length - 1;
		var inp = outputText;
		var prevTokenStart = -1;

		while (i >= 0) {
			if (inp.charAt(i) == " "){
				prevTokenStart = i;
				break;
			}
			i--;
		}

		inp = inp.substring(0, prevTokenStart + 1);

		outputText = inp;
		descriptionText = "";
		refreshOutputs();
	});
	$("#clear").click(function() {
		outputText = "";
		descriptionText = "";
		refreshOutputs();
	});
	$("#add").click(function() {
		outputText += " + ";
		descriptionText = "";
		refreshOutputs();
	});
	$("#acos").click(function() {
		outputText += " acos(";
		descriptionText = "<b>acos(number)</b>: Calculate arccos of a number";
		refreshOutputs();
	});
	$("#cos").click(function() {
		outputText += " cos(";
		descriptionText = "<b>cos(number)</b>: Calculate cos of a number";
		refreshOutputs();
	});
	$("#exp").click(function() {
		outputText += " ^ ";
		descriptionText = "";
		refreshOutputs();
	});
	$("#power_ten").click(function() {
		outputText += " 10 ^ ";
		descriptionText = "";
		refreshOutputs();
	});
	$("#_7").click(function() {
		outputText += "7";
		descriptionText = "";
		refreshOutputs();
	});
	$("#_8").click(function() {
		outputText += "8";
		descriptionText = "";
		refreshOutputs();
	});
	$("#_9").click(function() {
		outputText += "9";
		descriptionText = "";
		refreshOutputs();
	});
	$("#div").click(function() {
		outputText += " / ";
		descriptionText = "";
		refreshOutputs();
	});
	$("#atan").click(function() {
		outputText += " atan(";
		descriptionText = "<b>atan(number)</b>: Calculate arctan of a number";
		refreshOutputs();
	});
	$("#tan").click(function() {
		outputText += " tan(";
		descriptionText = "<b>tan(number)</b>: Calculate tan of a number";
		refreshOutputs();
	});
	$("#sqrt").click(function() {
		outputText += " sqrt(";
		descriptionText = "<b>sqrt(number)</b>: Calculate square root of a number";
		refreshOutputs();
	});
	$("#cbrt").click(function() {
		outputText += " cbrt(";
		descriptionText = "<b>cbrt(number)</b>: Calculate cube root of a number";
		refreshOutputs();
	});
	$("#_4").click(function() {
		outputText += "4";
		descriptionText = "";
		refreshOutputs();
	});
	$("#_5").click(function() {
		outputText += "5";
		descriptionText = "";
		refreshOutputs();
	});
	$("#_6").click(function() {
		outputText += "6";
		descriptionText = "";
		refreshOutputs();
	});
	$("#mult").click(function() {
		outputText += " x ";
		descriptionText = "";
		refreshOutputs();
	});
	$("#round").click(function() {
		outputText += " round(";
		descriptionText = "<b>round(number)</b>: Calculate rounding of a number";
		refreshOutputs();
	});
	$("#mod").click(function() {
		outputText += " % ";
		descriptionText = "";
		refreshOutputs();
	});
	$("#log").click(function() {
		outputText += " log(";
		descriptionText = "<b>log(number)</b>: Calculate log of a number";
		refreshOutputs();
	});
	$("#pi").click(function() {
		outputText += " pi ";
		descriptionText = "";
		refreshOutputs();
	});
	$("#_1").click(function() {
		outputText += "1";
		descriptionText = "";
		refreshOutputs();
	});
	$("#_2").click(function() {
		outputText += "2";
		descriptionText = "";
		refreshOutputs();
	});
	$("#_3").click(function() {
		outputText += "3";
		descriptionText = "";
		refreshOutputs();
	});
	$("#substract").click(function() {
		inp = $('#outputText').val().trim();
		var prevIsOperator = false;

		if (inp.length == 0) prevIsOperator = true;
		switch (inp.charAt(inp.length - 1)) {
			case '+': case '-': case 'x': case '/': case '%': case '^': case '(':
				prevIsOperator = true; break;
		}

		if (prevIsOperator) {
			outputText += "-";
		} else {
			outputText += " - ";
		}
		descriptionText = "";
		refreshOutputs();
	});
	$("#ceil").click(function() {
		outputText += " ceil(";
		descriptionText = "<b>ceil(number)</b>: Calculate ceil of a number";
		refreshOutputs();
	});
	$("#floor").click(function() {
		outputText += " floor(";
		descriptionText = "<b>floor(number)</b>: Calculate floor of a number";
		refreshOutputs();
	});
	$("#ln").click(function() {
		outputText += " ln(";
		descriptionText = "<b>ln(number)</b>: Calculate log of a number";
		refreshOutputs();
	});
	$("#e").click(function() {
		outputText += " e ";
		descriptionText = "";
		refreshOutputs();
	});
	$("#comma").click(function() {
		outputText += ",";
		descriptionText = "";
		refreshOutputs();
	});
	$("#_0").click(function() {
		outputText += "0";
		descriptionText = "";
		refreshOutputs();
	});
	$("#dot").click(function() {
		outputText += ".";
		descriptionText = "";
		refreshOutputs();
	});

	$("#btnClear").click(function() {
		$('#resultLogs').val('');
	});

	var prependLog = function(log) {
		$('#resultLogs').val(log + '\n' + $('#resultLogs').val());
	}

	$("#calculate").click(function() {
		var inp = $("#outputText").val();
		prependLog("");
		prependLog("Q: " + inp.trim());

		inp = inp.replace(/ x /g, " * ");
		inp = inp.trim();
		inp = inp.replace(/\s{2,}/g, " ");
//		console.log(inp);

		spacePos = inp.indexOf(" ");
		while (spacePos != -1) {
			if (spacePos > 0 && spacePos < (inp.length - 1) &&
					inp.charAt(spacePos-1) != "(" && !isOperator(inp.charAt(spacePos-1)) &&
					inp.charAt(spacePos+1) != ")" && !isOperator(inp.charAt(spacePos+1))) {
				inp = inp.substring(0, spacePos) + "*" + inp.substring(spacePos+1);
//				console.log(inp);
			}
			spacePos = inp.indexOf(" ", spacePos + 1);
		}

		inp = inp.replace(/\(/g, " ( ");
		inp = inp.replace(/\)/g, " ) ");
		inp = inp.replace(/\*/g, " * ");
		inp = inp.replace(/,/g, " , ");
//		console.log(inp);
		descriptionText = "";

		try {
			var isRadian = $('#radOpt').prop("checked");
			$.post('rest/evaluator/calculate', {exp: inp, isRadian: isRadian}, function(data) {
				prependLog("A: " + data.trim());
				outputText = data;

				refreshOutputs();
			});
		} catch (err) {
			outputText = "Invalid";

			refreshOutputs();
		}
	});

	$(document).keypress(function(event) {
		c = event.which;
		if (c >= 48 && c <= 57) {
			$("#_" + (c-48)).click();
		} else {
			switch (c) {
				case 42: $('#mult').click(); break;
				case 45: $('#substract').click(); break;
				case 43: $('#add').click(); break;
				case 94: $('#exp').click(); break;
				case 47: $('#div').click(); break;
				case 37: $('#mod').click(); break;
				case 46: $('#dot').click(); break;
				case 40: $('#lb').click(); break;
				case 41: $('#rb').click(); break;

				case 115: $('#sin').click(); break;
				case 99: $('#cos').click(); break;
				case 116: $('#tan').click(); break;
				case 83: $('#asin').click(); break;
				case 67: $('#acos').click(); break;
				case 84: $('#atan').click(); break;
			}
		}
	});

	$(document).keyup(function(event) {
		switch (event.which) {
			case 27: $('#clear').click(); break;
			case 13: $('#calculate').click(); break;
			case 77: $('#mod').click(); break;
			case 70: $('#floor').click(); break;
			case 82: $('#round').click(); break;
			case 73: $('#ceil').click(); break;
			case 76: $('#log').click(); break;
			case 78: $('#ln').click(); break;
			case 80: $('#pi').click(); break;
			case 69: $('#e').click(); break;
		}
	});

	$(document).keydown(function(event){
		switch (event.which) {
			case 8: $('#backspace').click(); event.preventDefault(); break;
		}
	});

	$('button').focus(function(){
		$(this).blur();
	});
})