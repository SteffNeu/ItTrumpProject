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

	</head>

    <body onload="initalize()"> <!-- Call the initalize method when the page loads -->
    <!-- TODO get the real picture with not hard coded path in here -->	
    <div style="background-image: url('data:image/jpeg;base64,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')">
    <div id="windowView" class="container" style="padding: 20px">
    	
    	<div class="container" style="background-color: white">

			<p><strong>Please enter the number of players you wish to play against.</strong></p>
		
			<input type="text" id="numPl" placeholder="1-4">
			<button id="startBtn" onclick="setNumPl()">Start Game</button>

			<p id="currentPlayers"></p>
			<br />
				
			<button id = "returnButton" class="float-left submit-button">Beam me up Scotty</button>
			<script type="text/javascript">
				document.getElementById("returnButton").onclick = function () {
					location.href = "http://localhost:7777/toptrumps";
				};
			</script>
<!--			
			<br />
			<button id="executeRound" onclick="executeRoundHuman('Speed')" class="floar-left submit-button">Category</button>
			<p id="category"></p>
			<br /> -->
		</div>
		
		<!-- game header -->
		<div class="container" style="background-color: #999999">
			<h1 id="gameHeader">Top Trumps Online</h1>
		</div>
		
		<!-- game screen view -->
		<div id="gameView">
			<div class="container">
				<!--ai cards-->
				
				<div id="aiCardsRow"class="row">

		            <div id="ai1" class="col-lg-3">
		                <div class="card" style="width: 250px" >
		                  <div class="card-header">AI 1 <span id="ai1CardCount" class="badge badge-info">Cards: </span></div>
		                  <div class="card-body"><img src="plane.png" height="200" width="200"></div>
		                  <div class="card-footer">
		                      <div class="container">
		                        <div class="btn-group-vertical">
		                            <span id="ai1LabelCat1" class="label info">Size - 50</span>
		                            <span id="ai1LabelCat2" class="label info">Speed -50</span>
		                            <span id="ai1LabelCat3" class="label info">Range - 50</span>
		                            <span id="ai1LabelCat4" class="label info">Firepower - 50</span>
		                            <span id="ai1LabelCat5" class="label info">cargo -50</span>
		                        </div>
		                    </div>
		                  </div>
		                </div>
		            </div>
		
		
		            <div id="ai2" class="col-lg-3">
		                <div class="card" style="width: 250px" >
		                  <div class="card-header">AI 2 <span id="ai2CardCount" class="badge badge-info">Cards: </span></div>
		                  <div class="card-body">
		                      <img src="plane.png" height="200" width="200"></div>
		                  <div class="card-footer">
		                      <div class="container">
		                        <div class="btn-group-vertical">
		                            <span id="ai2LabelCat1" class="label info">Size - 50</span>
		                            <span id="ai2LabelCat2" class="label info">Speed -50</span>
		                            <span id="ai2LabelCat3" class="label info">Range - 50</span>
		                            <span id="ai2LabelCat4" class="label info">Firepower - 50</span>
		                            <span id="ai2LabelCat5" class="label info">cargo -50</span>
		                        </div>
		                    </div>
		                  </div>
		                </div>
		            </div>
		
		            <div id="ai3" class="col-lg-3">
		
		                <div class="card" style="width: 250px" >
		                  <div class="card-header">AI 3 <span id="ai3CardCount" class="badge badge-info">Cards: </span></div>
		                  <div class="card-body"><img src="plane.png" height="200" width="200"></div>
		                  <div class="card-footer">
		                      <div class="container">
		                        <div class="btn-group-vertical">
		                            <span id="ai3LabelCat1" class="label info">Size - 50</span>
		                            <span id="ai3LabelCat2" class="label info">Speed -50</span>
		                            <span id="ai3LabelCat3" class="label info">Range - 50</span>
		                            <span id="ai3LabelCat4" class="label info">Firepower - 50</span>
		                            <span id="ai3LabelCat5" class="label info">cargo -50</span>
		                        </div>
		                    </div>
		                  </div>
		                </div>
		            </div>
		
		            <div id="ai4" class="col-lg-3">
		
		                <div class="card" style="width: 250px" >
		                  <div class="card-header">AI 4<span id="ai4CardCount" class="badge badge-info">Cards: </span></div>
		                  <div class="card-body"><img src="plane.png" height="200" width="200"></div>
		                  <div class="card-footer">
		                      <div class="container">
		                        <div class="btn-group-vertical">
		                            <span id="ai4LabelCat1" class="label info">Size - 50</span>
		                            <span id="ai4LabelCat2" class="label info">Speed -50</span>
		                            <span id="ai4LabelCat3" class="label info">Range - 50</span>
		                            <span id="ai4LabelCat4" class="label info">Firepower - 50</span>
		                            <span id="ai4LabelCat5" class="label info">cargo -50</span>
		                        </div>
		                    </div>
		                  </div>
		                </div>
		
		            </div>
		
		
		        </div> <!-- end of ai player cards-->
				
			</div>
		</div>
		
		<!--game log-->
        <div id="gameLogRow"class="row">
            <div id="gameLogArea" class="col-lg-8">
                <div class="card">
                    <div class="card-body">Game Log</div>
                </div>

            </div>

            <div id="roundBtns" class="col-lg-4">
                <button id="nextBtn" type="button" class="btn" style="width:40%" onclick="isHumanPlaying()">Continue</button>
                <button type="quitBtn" class="btn" style="width: 40%">Quit Game</button>

            </div>
        </div>
        
        <!--player card, statistics-->
        <div id="playerAndStatsRow"class="row">

        	<div id="playerArea" class="col-lg-6">
                
            	<!--player card-->
                <div id="human" class="card" style="width: 300px" >
                	<div class="card-header">You<span id="humanCardCount" class="badge badge-info">Cards: </span></div>
                	<div class="card-body"><img src="plane.png" height="200" width="200"></div>
                	<div class="card-footer">
                    	<div class="container">
                    		<div id="playerBtns" class="btn-group-vertical" style="width: inherit">
	                            <button id="btnCat1" type="button" class="btn btn-primary" onclick="executeRoundHuman('Size')">Size -50</button>
	                            <button id="btnCat2" type="button" class="btn btn-primary" onclick="executeRoundHuman('Speed')">Speed - 50</button>
	                            <button id="btnCat3" type="button" class="btn btn-primary" onclick="executeRoundHuman('Range')">Range - 50</button>
	                            <button id="btnCat4" type="button" class="btn btn-primary" onclick="executeRoundHuman('Firepower')">Firepower - 50</button>
	                            <button id="btnCat5" type="button" class="btn btn-primary" onclick="executeRoundHuman('Cargo')">Cargo - 50</button>
	                        </div>
                    	</div>
                  	</div>
                </div> <!-- end of player card -->
            </div> <!-- end of playerarea -->
            
            <!--stats-->
            <div id="roundStatsArea" class="col-lg-4">
                <!--table-->
                <div class="container-fluid">
                	<table class="table table-bordered table-sm">
                    	<tbody style="background-color: white">
                        	<tr>
                            	<td style="font-weight: bold">Current Player</td>
                            	<td id="curPlay">text</td>
                          	</tr>

	                        <tr>
	                            <td style="font-weight: bold" >Round</td>
	                            <td id="curRound">text</td>
	                        </tr>

                          	<tr>
                            	<td style="font-weight: bold">Communal Pile</td>
                            	<td id="comPile">text</td>
                          	</tr>
	                    </tbody>
	                  </table>
                </div>
            </div> <!-- end of rounds stats area -->
        </div> <!-- end of player and stats area -->

						
		<script type="text/javascript">
		
			// Method that is called on page load
			function initalize() {
				hideGame();
				disablePlayerChoice();
				
				
				// --------------------------------------------------------------------------
				// You can call other methods you want to run when the page first loads here
				// --------------------------------------------------------------------------
				
				// For example, lets call our sample methods
				//GameOnline("3");	
			}
			
			//enables the button on the card and hides the ai1 cards
			function enablePlayerChoice(){
				//alert("I'm in enable")
				document.getElementById("ai1").style.visibility = "hidden";
				document.getElementById("ai2").style.visibility = "hidden";
				document.getElementById("ai3").style.visibility = "hidden";
				document.getElementById("ai4").style.visibility = "hidden";			
			
				document.getElementById("btnCat1").disabled = false;
				document.getElementById("btnCat2").disabled = false;	
				document.getElementById("btnCat3").disabled = false;
				document.getElementById("btnCat4").disabled = false;
				document.getElementById("btnCat5").disabled = false;						
			}
			
			function disablePlayerChoice(){
				document.getElementById("btnCat1").disabled = true;
				document.getElementById("btnCat2").disabled = true;	
				document.getElementById("btnCat3").disabled = true;
				document.getElementById("btnCat4").disabled = true;
				document.getElementById("btnCat5").disabled = true;				
			}
			
			function hideGame(){
				document.getElementById("gameView").style.visibility = "hidden";
				document.getElementById("gameLogRow").style.visibility = "hidden";
				document.getElementById("playerAndStatsRow").style.visibility = "hidden";
				document.getElementById("roundStatsArea").style.visibility = "hidden";
			}
			
			function showGame(){
				document.getElementById("gameView").style.visibility = "visible";
				document.getElementById("gameLogRow").style.visibility = "visible";
				document.getElementById("playerAndStatsRow").style.visibility = "visible";
				document.getElementById("roundStatsArea").style.visibility = "visible";
			}
			
			function setNumPl() {
				var x = document.getElementById("numPl").value;
				if(x < 1 || x > 4){
					 document.getElementById("numPl").value = "";
					 document.getElementById("currentPlayers").innerHTML = "Invalid input. Choose a number between 1 and 4."
				}
				else{
					//set up game and call hideContent
					GameOnline(x);
				}
			}
			
			function updatePlayers(playerInfos){
				document.getElementById("curPlay").innerHTML
			}
			
			function finishGame(){
				executeRoundAI();
			}
			
			function initContent(numPlayers){
				document.getElementById("numPl").style.visibility = "hidden";
				document.getElementById("startBtn").style.visibility = "hidden";
				for(var i = 4 ; i > numPlayers; i--){
					var aiID = "ai"+i
					document.getElementById(aiID).style.visibility = "hidden";
				}
				showGame()
				
			}
			
			function updateInfo(infos){
				document.getElementById("curPlay").innerHTML = infos.activePlayer;
				document.getElementById("curRound").innerHTML = infos.roundnumber;
				document.getElementById("comPile").innerHTML = infos.communalcardnumber;
			}
			
			// -----------------------------------------
			// Add your other Javascript methods Here
			// -----------------------------------------
		
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
		
		<!-- Here are examples of how to call REST API Methods -->
		<script type="text/javascript">
		
			// This calls the helloJSONList REST method from TopTrumpsRESTAPI
			function helloJSONList() {
			
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/helloJSONList"); // Request type and URL
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
					alert(responseText); // lets produce an alert
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}
			
			// This calls the helloJSONList REST method from TopTrumpsRESTAPI
			function helloWord(word) {
			
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/helloWord?Word="+word); // Request type and URL+parameters
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
					alert(responseText); // lets produce an alert
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}
			
			// This calls the helloJSONList REST method from TopTrumpsRESTAPI
			function GameOnline(numOfPlayers) {
			
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/game?3="+numOfPlayers); // Request type and URL+parameters
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
					//alert(responseText); // lets produce an alert
					document.getElementById("currentPlayers").innerHTML = responseText;
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
				initContent(numOfPlayers)

			}

			function isHumanPlaying() {

				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/game/isHumanPlaying"); // Request type and URL
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
 					var val = JSON.parse(responseText)
					if(val.humanplaying){
						isCurrentHuman();
					}
					else {
						finishGame();
					}
				};
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();	
			}			
			
			
			
			function isCurrentHuman() {

				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/game/isCurrentHuman"); // Request type and URL
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
 					var value = JSON.parse(responseText)
					if(value.curHuman){
						//enable button on cards
						enablePlayerChoice();
						document.getElementById("nextBtn").disabled = true;
						alert("humans turn")
					}
					else {
						executeRoundAI();
					}
				};
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();	
			}
	
			
			
			
			function executeRoundAI() {

				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/game/executeRoundAI"); // Request type and URL+parameters
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
 					
 					var values = JSON.parse(responseText)
 					if(values.gameover){
 						updateInfo(values)
 						alert("Thank you for playing")
 					}
 					else if(values.humanplaying){
 						alert(responseText);
 						updateInfo(values);
 						//display results
 						//enable button
 					}
 					else{
 						finishGame();
 					}
					
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}
			

			

			
			
			
			function executeRoundHuman(category) {

				disablePlayerChoice();
				document.getElementById("nextBtn").disabled = false;
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/game/executeRoundHuman?category="+category); // Request type and URL+parameters
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
 					var infos = JSON.parse(responseText)
					alert(responseText)
					makeCardsVisible();
					updateInfo(infos);
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();	
				
			
				
					
			}		
			
			function makeCardsVisible() {
			
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/game/getActivePlayers"); // Request type and URL
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
					alert(responseText); // lets produce an alert
					var players = JSON.parse(responseText)
					var arrayLength = players.length;
					
					//rethink if we can come into a scenario where we want to unhide ai's after human is out of the game
					for (var i = 1; i < arrayLength; i++) {
						alert("\""+players[i]+"\"");
						var tmp = players[i];
						document.getElementById(tmp).style.visibility = "visible";
						
					}
											
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}			
				
			

		</script>
		
		</div> <!-- end window view-->
		
		</div> <!-- ends the background image-->
	
		<!--once game has ended modal-->
		<div id="gameEnd" class="container">
  			<h2>The game has ended</h2>

			<!-- Button to Open the Modal -->
			<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">Game results</button>

  			<!-- The Modal -->
  			<div class="modal fade" id="myModal">
    			<div class="modal-dialog modal-dialog-centered">
      				<div class="modal-content">

        				<!-- Modal Header -->
				        <div class="modal-header">
				         	<h4 class="modal-title">GAME OVER</h4>
				        	<button type="button" class="close" data-dismiss="modal">&times;</button>
				        </div>

        				<!-- Modal body -->
        				<div class="modal-body">

         					<!--Game end results-->
            				<table align="left">
				
				            	<!--winner-->
				            	<tr>
				                	<th>Winner</th>
				                	<td>###</td>
				              	</tr>
				              	<tr>
				
				            	<!--Rounds played-->
				                	<th>Rounds</th>
				                	<td>###</td>
				              	</tr>
				              	<tr>

					            <!--Number of draws-->
					                <th>Draws</th>
					                <td>###</td>
					            </tr>
					        </table>
					    </div>

				        <!-- Modal footer -->
				        <div class="modal-footer">
				            <button id="playAgain" type="button" class="btn btn-secondary" data-dismiss="modal">Play Again</button>
				            <script type="text/javascript">
								document.getElementById("playAgain").onclick = function () {
									location.href = "http://localhost:7777/toptrumps/game";
								};
							</script>
				            <button id="viewStats" type="button" class="btn btn-secondary" data-dismiss="modal">View Game Statistics</button>
				            <script type="text/javascript">
								document.getElementById("viewStats").onclick = function () {
									location.href = "http://localhost:7777/toptrumps/stats";
								};
							</script>
				            <button id ="quitEnd" type="button" class="btn btn-secondary" data-dismiss="modal">Quit</button>
				            <script type="text/javascript">
								document.getElementById("quitEnd").onclick = function () {
									location.href = "http://localhost:7777/toptrumps";
								};
							</script>
				        </div>

      				</div>
    			</div>
  			</div>
		</div> <!-- end of the modal -->
	</div> <!-- end of game end-->
				
	</body>
</html>