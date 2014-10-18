var keyAllowed = {};
var action = "";
function sendEvent(key, type) {
	switch (key) {
	case 37:
		action = "left";
		break;
	case 38:
		action = "forward";
		break;
	case 39:
		action = "right";
		break;
	case 40:
		action = "back";
		break;
	default:
		action = "";
		break;
	}
	if (action !== "") {
		$.post("/robot/direction/" + action + "/"
				+ type);
	}
}
function keyUp(event) {
	$('#msg-keyup').html('keyup(), keyCode = ' + event.keyCode);
	keyAllowed[event.keyCode] = true;
	sendEvent(event.keyCode, "stop");
}

function keyDown(event) {
	if (keyAllowed[event.keyCode] === false) {
		console.log('preventing ' + event.keyCode);
	} else {
		$('#msg-keydown').html('keydown, keyCode = ' + event.keyCode);
		keyAllowed[event.keyCode] = false;
		sendEvent(event.keyCode, "start");
	}
}
$(document).keydown(keyDown);
$(document).keyup(keyUp);
