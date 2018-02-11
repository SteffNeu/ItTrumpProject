<html>

	<head>
		<!-- Web page title -->
    	<title>Top Trumps</title>

    	<!-- Import JQuery, as it provides functions you will probably find useful (see https://jquery.com/) -->
    	<script src="https://code.jquery.com/jquery-2.1.1.js"></script>
    	<script src="https://code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
    	<link rel="stylesheet" href="https://code.jquery.com/ui/1.11.1/themes/flick/jquery-ui.css">

		<!-- Optional Styling of the Website, for the demo I used Bootstrap (see https://getbootstrap.com/docs/4.0/getting-started/introduction/) -->
		<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/TREC_IS/bootstrap.min.css">
    	<script src="http://dcs.gla.ac.uk/~richardm/vex.combined.min.js"></script>
    	<script>vex.defaultOptions.className = 'vex-theme-os';</script>
    	<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/assets/stylesheets/vex.css"/>
    	<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/assets/stylesheets/vex-theme-os.css"/>
    	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">


		<style>

			body{
			    background-image: url('https://i.guim.co.uk/img/media/d13fb05c33ace35b0f6fd793170450e9486ff08c/0_0_1917_1073/master/1917.jpg?w=1920&q=55&auto=format&usm=12&fit=max&s=5cc3db4ad82231178efba9aa916a4910');
				background-size: cover;
				background-repeat: no-repeat;
			}

			table{
			    width: 500px;
			    border-spacing: 20px;
			    margin: auto;
			    border: solid 1px black;
			    padding: 10px;
					background-color: whitesmoke;
					margin-bottom: 25px;
			}

			th{
			    font-weight: bold;
			    font-size: 20px;
			    text-align: left;
			    padding: 10px;
			}

			td{
			    font-size: 20px;
			    text-align: left;
			    padding: 10px;
			}

			tr:hover{
			    background-color: #6699ff;

			}
			form{
			    text-align: center;
			}


		</style>

	</head>

    <body onload="initalize()"> <!-- Call the initalize method when the page loads -->

    <div class ="jumbotron jumbotron-fluid">
			<style>
			.jumbotron {
				background: #6699ff;
				color: white;
				text-align: center;
				margin-top: 25px;
  			padding: 1.5em 2em;
				/*max-width: 700px;*/
				margin-right: 25px;
				margin-left: 25px;
				margin-bottom: 25px;
			}
			.jumbotron h1 {
				/*font-size: 40px;*/
				font-size: 2.6em;
				text-shadow: 2px 2px #b3b3ff;
			}
			.jumbotron p {
				color: white;
				font-size: 1.2em;
			}
			.btn-primary{
				margin-bottom: 1px;
			}
			.btn-primary:hover{
				background-color: #4d79ff;
			}
			</style>
			<div class = "container">
				<h1>Top Trump Game Statistics</h1>
			</div>
		</div>
		<div class="container">
			<style>
				.container div{
						width: 700px;
						border: 10px solid #6699ff;
						padding: 30px;
						margin: auto;
						margin-bottom: 20px;
						background-color: whitesmoke;
				}
			</style>
			<!-- table for the statistics-->
			<table>
 				<tr>
  				  	<th>Games played</th>
					<td id="games"></td>
  				</tr>
  				<tr>
    				<th>Computer wins</th>
    				<td id="aiWins"></td>
  				</tr>
  				<tr>
    				<th>Human wins</th>
	    			<td id="humanWins"></td>
  				</tr>
  				 <tr>
    				<th>Average draws</th>
	    			<td id="avgDraws"></td>
  				</tr>
  				 <tr>
    				<th>Most rounds in a game</th>
	    			<td id="maxRounds"></td>
  				</tr>
			</table>

		</div>

		<p align="center">
			<a class="btn btn-primary btn-lg" href="http://localhost:7777/toptrumps" role="button">Return to Selection Page</a>
		</p>

		<script type="text/javascript">

			// Method that is called on page load
			function initalize() {
				getStatistics();
			}
			// This is a reusable method for creating a CORS request. Do not edit this.
			function createCORSRequest(method, url) {
  				var xhr = new XMLHttpRequest();
  				if ("withCredentials" in xhr) {

    				// Check if the XMLHttpRequest object has a "withCredentials" property.
    				// "withCredentials" only exists on XMLHTTPRequest2 objects.
    				xhr.open(method, url, true);

  				} else if (typeof XDomainRequest != "undefined") {

    				// Otherwise, check if XDomainRequest.
    				// XDomainRequest only exists in IE, and is IE's way of making CORS requests.
    				xhr = new XDomainRequest();
    				xhr.open(method, url);

 				 } else {

    				// Otherwise, CORS is not supported by the browser.
    				xhr = null;

  				 }
  				 return xhr;
			}

		</script>


		<script type="text/javascript">

			//retrieves the information from the database and writes them into a table
			function getStatistics() {

				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/stats"); // Request type and URL

				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
					var values = JSON.parse(responseText) //turn response into an object
					//extract information from object and write them to the table
					document.getElementById("games").innerHTML = values.games;
					document.getElementById("aiWins").innerHTML = values.aiWins;
					document.getElementById("humanWins").innerHTML = values.humanWins;
					document.getElementById("avgDraws").innerHTML = values.avgDraws;
					document.getElementById("maxRounds").innerHTML = values.maxRounds;

				};

				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();
			}



		</script>

		</body>
</html>
