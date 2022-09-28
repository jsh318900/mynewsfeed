
function twitchLogin() {
	const loginRequest = new XMLHttpRequest();
	loginRequest.onreadystatechange = () => {
		if(loginRequest.readyState == 4) {
			if (loginRequest.status == 302) {
				window.location.href = loginRequest.response.getResponseHeader("Location");
			} else {
				alert("Error requesting login for twitch");
			}
		}
	}
	loginRequest.open("GET", document.location.href + "/twitch/login");
	loginRequest.send();
}

async function checkSession() {
	
}
