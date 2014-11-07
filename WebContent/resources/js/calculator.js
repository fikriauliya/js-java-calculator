$(function() {
	var outputText = "";
	var descriptionText = "";

	var refreshOutputs = function() {
		console.log(outputText);
		$("#outputText").val(outputText);
		$("#descriptionText").html(descriptionText);
	}

	var fact = function(number) {
		if (number == 0) return 0;
		if (number < 0) return -1 * fact(Math.abs(number));

		var res = 1;
		for (var i = 2;i<=number;i++){
			res = res * i;
		}
		return res;
	}

	var root = function(number, degree) {
		return Math.pow(number, 1.0/(degree*1.0));
	}

	var log = function(number) {
		return Math.log(number) / Math.log(10);
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
		outputText += " ^ ";
		descriptionText = "";
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
		var inp = $("#outputText").val();
		
		//replace ^ to Math.pow
		var powPos = inp.lastIndexOf(" ^ ");
		while (powPos != -1) {
			var i = powPos - 1;
			var closeTagCount = 0;
			var openTagCount = 0;

			var prevTokenStart = -1;
			var nextTokenEnd = inp.length;

			while (i >= 0) {
				if (inp.charAt(i) == ")") {
					closeTagCount++;
				} else if (inp.charAt(i) == "(") {
					openTagCount++;
				} else if ((inp.charAt(i) == " ") && (openTagCount == closeTagCount)){
					prevTokenStart = i;
					break;
				}
				i--;
			}
			var i = powPos + 3;
			closeTagCount = 0;
			openTagCount = 0;
			while (i < inp.length) {
				if (inp.charAt(i) == ")") {
					closeTagCount++;
				} else if (inp.charAt(i) == "(") {
					openTagCount++;
				} else if ((inp.charAt(i) == " ") && (openTagCount == closeTagCount)){
					nextTokenEnd = i;
					break;
				}
				i++;
			}

			// TODO: Handle invalid tokenPos

			console.log(prevTokenStart);
			console.log(nextTokenEnd);

			inp1 = inp.substring(0, prevTokenStart + 1) + "Math.pow(" + inp.substring(prevTokenStart + 1, powPos) + ",";
			inp2 = inp.substring(powPos + 3, nextTokenEnd) + ")" + inp.substring(nextTokenEnd);
			inp = inp1 + inp2;

			console.log("[" + inp + "]");
			powPos = inp.lastIndexOf(" ^ ");
		}

		inp = inp.replace(/ x /g, " * ");
		inp = inp.replace(/ asin/g, " Math.asin ");
		inp = inp.replace(/ sin/g, " Math.sin ");
		inp = inp.replace(/ acos/g, " Math.acos ");
		inp = inp.replace(/ cos/g, " Math.cos ");
		inp = inp.replace(/ atan/g, " Math.atan ");
		inp = inp.replace(/ tan/g, " Math.tan ");
		inp = inp.replace(/ sqrt/g, " Math.sqrt ");
		inp = inp.replace(/ round/g, " Math.round ");
		inp = inp.replace(/ ceil/g, " Math.ceil ");
		inp = inp.replace(/ floor/g, " Math.floor ");
		inp = inp.replace(/ ln/g, " Math.log ");
		inp = inp.replace(/ e /g, " Math.E ");
		inp = inp.replace(/ pi /g, " Math.PI ");

		console.log(inp);
		descriptionText = "";

		try {
			outputText = eval(inp);
		} catch (err) {
			outputText = "Error";
		}
		refreshOutputs();
	});
})