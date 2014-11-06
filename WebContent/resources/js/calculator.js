$(function() {
	var outputText = "";
	var descriptionText = "";

	var refreshOutputs = function() {
		console.log(outputText);
		$("#outputText").val(outputText);
		$("#descriptionText").html(descriptionText);
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
		outputText += "(";
		descriptionText = "";
		refreshOutputs();
	});
	$("#rb").click(function() {
		outputText += ")";
		descriptionText = "";
		refreshOutputs();
	});
	$("#backspace").click(function() {
		outputText += "backspace";
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
		outputText += " pow(";
		descriptionText = "<b>pow(base, exponent)</b>: Calculate <b><i>base</i></b> to the power of <b><i>exponent</i></b>";
		refreshOutputs();
	});
	$("#fact").click(function() {
		outputText += " fact(";
		descriptionText = "<b>fact(number)</b>: Calculate factorial of a number";
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
	$("#root").click(function() {
		outputText += " root(";
		descriptionText = "<b>root(number, degree)</b>: Calculate <b><i>degree</i></b>-th root of a <b><i>number</i></b>";
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
		outputText += " - ";
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
		outputText += ", ";
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
	$("#calculate").click(function() {
		outputText += "calculate";
		refreshOutputs();
	});
})