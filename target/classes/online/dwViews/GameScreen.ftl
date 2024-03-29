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
		<div style="background-image: url('https://static.gamespot.com/uploads/original/536/5360430/3141756-screenshot0034.jpg'); background-repeat: no-repeat; background-size: cover;">
    <div id="windowView" class="container" style="padding: 20px">
			<div id="startView" class="container">
			<!-- game header -->
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
					<h1>Top Trumps Online</h1>
				</div>
			</div> <!-- end of game header -->

    	<div id="header" class="container" style="background-color: white">
				<p><strong>Please enter the number of players you wish to play against.</strong></p>

				<input type="text" id="numPl" placeholder="1-4" onkeypress="return event.charCode >= 48 && event.charCode <= 57">
				<button id="startBtn" onclick="setNumPl()" class="btn btn-primary">Start Game</button>
				<br />
				<div id="currentPlayers"></div>
				<button id = "returnButton" class="btn btn-primary float-left">Return Home</button>

				<script type="text/javascript">
					document.getElementById("returnButton").onclick = function () {
						location.href = "http://localhost:7777/toptrumps";
					};
				</script>
				<br />
			</div>
		</div>

		<!-- game screen view -->
		<div id="gameView">
			<div class="container" style="margin-bottom:50px">
				<!--ai cards-->

				<div id="aiCardsRow"class="row">

		            <div id="ai1" class="col-lg-3">
		                <div class="card" style="width: 250px" >
		                  <div class="card-header">AI 1 <span id="ai1CardCount" class="badge badge-info">Cards: </span></div>
		                  <div id="ai1CardInfo">
		                  <div class="card-body"><img id="ai1ImgCard" src="" height="200" width="200"></div>
		                  <div class="card-footer">
		                      <div class="container">
		                      	<div id="ai1CardName" style></div>
		                        <div class="btn-group-vertical">
		                            <span id="ai1Cat1" class="label info"></span>
		                            <span id="ai1Cat2" class="label info"></span>
		                            <span id="ai1Cat3" class="label info"></span>
		                            <span id="ai1Cat4" class="label info"></span>
		                            <span id="ai1Cat5" class="label info"></span>
		                        </div>
		                    </div>
		                    </div>
		                  </div>
		                </div>
		            </div>


		            <div id="ai2" class="col-lg-3">
		                <div class="card" style="width: 250px" >
		                  <div class="card-header">AI 2 <span id="ai2CardCount" class="badge badge-info">Cards: </span></div>
		                  <div id="ai2CardInfo">
		                  	<div class="card-body">
		                    	  <img id="ai2ImgCard" src="" height="200" width="200"></div>
		                  	<div class="card-footer">
		                    	<div class="container">
		                      		<div id="ai2CardName"></div>
		                        	<div class="btn-group-vertical">
		                            	<span id="ai2Cat1" class="label info"></span>
			                            <span id="ai2Cat2" class="label info"></span>
			                            <span id="ai2Cat3" class="label info"></span>
			                            <span id="ai2Cat4" class="label info"></span>
		    	                        <span id="ai2Cat5" class="label info"></span>
		        	                </div>
		            	        </div>
		                  	</div>
		                  	</div>
		                </div>
		            </div>

		            <div id="ai3" class="col-lg-3">

		                <div class="card" style="width: 250px" >
		                  <div class="card-header">AI 3 <span id="ai3CardCount" class="badge badge-info">Cards: </span></div>
		                  <div id="ai3CardInfo">
		                  <div class="card-body"><img id="ai3ImgCard" src="" height="200" width="200"></div>
		                 <div class="card-footer">
		                      <div class="container">
		                      	<div id="ai3CardName"></div>
		                        <div class="btn-group-vertical">
		                            <span id="ai3Cat1" class="label info"></span>
		                            <span id="ai3Cat2" class="label info"></span>
		                            <span id="ai3Cat3" class="label info"></span>
		                            <span id="ai3Cat4" class="label info"></span>
		                            <span id="ai3Cat5" class="label info"></span>
		                        </div>
		                    </div>
		                  </div>
		                 </div> 
		                </div>
		            </div>

		            <div id="ai4" class="col-lg-3">

		                <div class="card" style="width: 250px" >
		                  <div class="card-header">AI 4<span id="ai4CardCount" class="badge badge-info">Cards: </span></div>
		                  <div id="ai4CardInfo">
		                  <div class="card-body"><img id="ai4ImgCard" src="" height="200" width="200"></div>
		                  <div class="card-footer">
		                      <div class="container">
		                        <div id="ai4CardName"></div>
		                        <div class="btn-group-vertical">
		                            <span id="ai4Cat1" class="label info"></span>
		                            <span id="ai4Cat2" class="label info"></span>
		                            <span id="ai4Cat3" class="label info"></span>
		                            <span id="ai4Cat4" class="label info"></span>
		                            <span id="ai4Cat5" class="label info"></span>
		                        </div>
		                    </div>
		                  </div>
		                </div>
		                </div>

		            </div>


		        </div> <!-- end of ai player cards-->

			</div>

        <!--player card, statistics-->
        <div id="playerAndStatsRow"class="row">

        	<div id="playerArea" class="col-lg-4">

            	<!--player card-->
                <div id="human" class="card" style="width: 300px" >
                	<div class="card-header">You <span id="humanCardCount" class="badge badge-info">Cards: </span></div>
                	<div class="card-body"><img id="humanImgCard" src="" height="200" width="200" style="justify-content: center"></div>
                	<div class="card-footer">
                    	<div class="container">
                    	<div id="humanCardName"></div>
                    		<div id="playerBtns" class="btn-group-vertical" style="width: inherit">
	                            <button id="humanCat1" type="button" class="btn btn-primary" onclick ="getCategory('1')"></button>
	                            <button id="humanCat2" type="button" class="btn btn-primary" onclick ="getCategory('2')"></button>
	                            <button id="humanCat3" type="button" class="btn btn-primary" onclick ="getCategory('3')"></button>
	                            <button id="humanCat4" type="button" class="btn btn-primary" onclick ="getCategory('4')"></button>
	                            <button id="humanCat5" type="button" class="btn btn-primary" onclick ="getCategory('5')"></button>
	                        </div>
                    	</div>
                  	</div>
                </div> <!-- end of player card -->
            </div> <!-- end of playerarea -->

            <!--stats-->
            <div id="roundStatsArea" class="col-lg-8">
							<!--game log-->
				      <div id="gameLogRow"class="row" style="margin-bottom:25px; background-color:whitesmoke">
				        <div id="gameLogArea" class="col-lg-8">
				          <div class="card">
				          	<div class="card-body">
											<h4>Game Log</h4>
											<p id="GameLogContent"></p>
											<div id="roundresults">
												<div id="ai1Result"></div>
												<div id="ai2Result"></div>
												<div id="ai3Result"></div>		
												<div id="ai4Result"></div>
												<div id="humanResult"></div>
											</div>

							</div>
				          </div>
				        </div>
							</div>
							<div class="row" style="margin-bottom:25px">
				      	<div id="roundBtns" class="col-lg-8">
				          <button id="nextBtn" type="button" class="btn btn-primary clearfloat" style="width:80%" onclick="isHumanPlaying()">Play Round</button>
				          <button id="quitGameBtn" type="button" class="btn btn-primary clearfloat" value="Check"  onclick=window.location.href="http://localhost:7777/toptrumps" style="width: 80%">Quit Game</button>
				        </div>
				      </div>

								<!--table-->
              <div class="container-fluid col-lg-8">
              	<table class="table table-bordered table-sm" align="left">
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
			</div> <!-- end of game view-->


		<script type="text/javascript">

			// Method that is called on page load
			function initalize() {
				//show only the prompt for a player-number
				hideGame();
				disablePlayerChoice();
			}

			//hides the information about the cards
			function hideAIs(){
				document.getElementById("ai1CardInfo").style.visibility = "hidden";
				document.getElementById("ai2CardInfo").style.visibility = "hidden";
				document.getElementById("ai3CardInfo").style.visibility = "hidden";
				document.getElementById("ai4CardInfo").style.visibility = "hidden";
			}
			
			
			//enable the buttons for the player to choose a category
			function enablePlayerChoice(){
				document.getElementById("humanCat1").disabled = false;
				document.getElementById("humanCat2").disabled = false;
				document.getElementById("humanCat3").disabled = false;
				document.getElementById("humanCat4").disabled = false;
				document.getElementById("humanCat5").disabled = false;
			}

			//disable the buttons of the player to stop him from choosing a category
			function disablePlayerChoice(){
				document.getElementById("humanCat1").disabled = true;
				document.getElementById("humanCat2").disabled = true;
				document.getElementById("humanCat3").disabled = true;
				document.getElementById("humanCat4").disabled = true;
				document.getElementById("humanCat5").disabled = true;
			}

			//hide game related areas
			function hideGame(){
				document.getElementById("gameView").style.visibility = "hidden";
				document.getElementById("gameLogRow").style.visibility = "hidden";
				document.getElementById("playerAndStatsRow").style.visibility = "hidden";
				document.getElementById("roundStatsArea").style.visibility = "hidden";
				document.getElementById("gameEnd").style.visibility = "hidden";
			}

			//displays the game elements and hides the header
			function showGame(){
				//hide header
				document.getElementById("header").style.display = "none";

				//show game area
				document.getElementById("gameView").style.visibility = "visible";
				document.getElementById("gameLogRow").style.visibility = "visible";
				document.getElementById("playerAndStatsRow").style.visibility = "visible";
				document.getElementById("roundStatsArea").style.visibility = "visible";
			}

			//get input from textfield and use it to start a new game
			function setNumPl() {
				//get and verify input
				var x = document.getElementById("numPl").value;
				if(x < 1 || x > 4){
					document.getElementById("numPl").value = "";
					document.getElementById("currentPlayers").innerHTML = "Invalid input. Choose a number between 1 and 4."
				}
				else{
				// set up game and update game area
				GameOnline(x);
				// wait for the GameOnline method to finish
				sleep(100).then(() => {
				    setUpGameDisplay();
				})
				}
			}

			//delay the execution of a function to tackle asynchronous behaviour of javascript
			function sleep(time){
				return new Promise((resolve) => setTimeout(resolve, time));
			}

			//update the card count for the players
			function updatePlayers(playerInfos){

				//update the card count for the ai's 
				document.getElementById("humanCardCount").textContent = "Cards: " + playerInfos.humancards;
				for(var i = 1; i <= 5; i++) {
					var aiName = "ai" + i + "cards";
					if (playerInfos.hasOwnProperty(aiName)) {
						document.getElementById("ai" + i + "CardCount").textContent = "Cards: " + playerInfos[aiName];
					}
				}
			}

			//execute rest of the ai rounds after the human was eliminated
			function finishGame(){
				//inform player of his loss and put the game in a hold state
 				document.getElementById("GameLogContent").innerHTML = "You have lost the game. The remaining rounds will now be executed automatically!";
 				document.getElementById("roundresults").innerHTML = "";
 				document.getElementById("nextBtn").disabled = true;
				document.getElementById("quitGameBtn").disabled = true;			
				
				//continue to execute ai rounds	
				executeRoundAI();
			}

			//derive the number of ai's that are to be displayed
			function initGameContent(numPlayers){

				//hide unused ai's
				for(var i = 4 ; i > numPlayers; i--){
					var aiID = "ai"+i
					document.getElementById(aiID).style.visibility = "hidden";
				}
				//display game elements
				showGame()

			}

			//update game log header and check for elimination
			function updateInfo(infos){
			
				//determine the message of the round winner (human or ai)
				if(infos.activePlayer == "human"){
					document.getElementById("GameLogContent").innerHTML = "You won the round";
					document.getElementById("curPlay").innerHTML = "You";
				}
				else{
					document.getElementById("GameLogContent").innerHTML = infos.activePlayer +" won the round";				
					document.getElementById("curPlay").innerHTML = infos.activePlayer;
				}
				
				//inform about a draw if one occurred
				if(infos.communalcardnumber != "0"){
					document.getElementById("GameLogContent").innerHTML = "The last round resulted in a draw!";	
				}
				
				//take the information for the first round in account and display corresponding message
				if(infos.roundnumber == "0"){
					if(infos.activePlayer == "human"){
						document.getElementById("GameLogContent").innerHTML = "You start the game";
					}
					else{
						document.getElementById("GameLogContent").innerHTML = infos.activePlayer + " starts the game";
					}				
				}	
				
				//display roundnumber and number of cards in the communal pile
				document.getElementById("curRound").innerHTML = infos.roundnumber;
				document.getElementById("comPile").innerHTML = infos.communalcardnumber;
				
				//update game log			
				displayLog(infos);
				
				//get number of cards to update the piles of the players			
				getNumCards();
				
				//hide players that were eliminated
				eliminatePlayers();

			}
			
			//display the winning category for every player
			function displayLog(gameInfos){
				var cat = gameInfos.chosenCategory
				//catch category of the first round
				if(cat != 0){
					//loop over players to update the corresponding fields
					for (var player in gameInfos.players){
						curP = gameInfos.players[player]
						if (curP == "human"){
							document.getElementById("humanResult").innerHTML = "You: " + document.getElementById("humanCat"+cat).textContent;
						}
						else {
							document.getElementById(curP+"Result").innerHTML = curP + ": " + document.getElementById(curP+"Cat"+cat).textContent;
						}
 					}
				}	
			}

			//display the results of the game with a new view
			function showGameResults(infos){
			
				//hide game view
				var e = document.getElementById("gameView");
				e.style.display = 'none';

				//show end-screen
				var d = document.getElementById("gameEnd");
				d.style.visibility = 'visible';
				document.getElementById("gameWinner").innerHTML = infos.activePlayer;
				document.getElementById("gameTotalRounds").innerHTML = infos.roundnumber;
				document.getElementById("gameTotalDraws").innerHTML = infos.numofdraws;

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

		<!-- Here are examples of how to call REST API Methods -->
		<script type="text/javascript">

			//get the top card of every player and assign the categories and their respective values to the card display
			function getTopCards() {
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/game/getTopCards"); // Request type and URL

				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
 					//parse responseText into a json-object
 					var topcards = JSON.parse(responseText);


					//loop through the players and update the cards
					for (var i = 0; i < 5 ; i ++) {
						// 0 is the human card
						if (i == 0) {
							document.getElementById( "humanImgCard").src = "http://dcs.gla.ac.uk/~richardm/TopTrumps/" + topcards.human.cardName + ".jpg";
							document.getElementById("humanCardName").innerHTML = "<b>" + topcards.human.cardName + "</b>";
							document.getElementById("humanCat1").innerHTML = topcards.human.category1;
							document.getElementById("humanCat2").innerHTML = topcards.human.category2;
							document.getElementById("humanCat3").innerHTML = topcards.human.category3;
							document.getElementById("humanCat4").innerHTML = topcards.human.category4;
							document.getElementById("humanCat5").innerHTML = topcards.human.category5;
							}
						else {
							var aiName = "ai" + i;
							
							//check if ai exist and if yes update the card
							if (topcards.hasOwnProperty(aiName)){
								document.getElementById(aiName + "ImgCard").src = "http://dcs.gla.ac.uk/~richardm/TopTrumps/" + topcards[aiName].cardName + ".jpg";
								document.getElementById(aiName + "CardName").innerHTML = "<b>" + topcards[aiName].cardName + "</b>";
								document.getElementById(aiName + "Cat1").innerHTML = topcards[aiName].category1;
								document.getElementById(aiName + "Cat2").innerHTML = topcards[aiName].category2;
								document.getElementById(aiName + "Cat3").innerHTML = topcards[aiName].category3;
								document.getElementById(aiName + "Cat4").innerHTML = topcards[aiName].category4;
								document.getElementById(aiName + "Cat5").innerHTML = topcards[aiName].category5;
							}

						}
					}
				};

				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();
			}




			//create and start the game
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

				};

				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();
				
				//after creating the game, set up the game display
				initGameContent(numOfPlayers)
				hideAIs();
				

			}
			
			//set up the game details at the beginning of the game
			function setUpGameDisplay() {

				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/game/setUpGameDisplay"); // Request type and URL+parameters

				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
 					//parse responseText into a json-object
 					var infos = JSON.parse(responseText)

					//update and display the game related infos
 					updateInfo(infos);
 					updatePlayers(infos);
 					getTopCards();

				};

				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();
			}
						


			//checks if the human is still in the game ; the execution of a round starts with the call of this method
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
 					
 					//parse responseText into a json-object
 					var val = JSON.parse(responseText);
 					
 					//check if human is still in the game
					if(val.humanplaying){
						//check if the human is the current player
						isCurrentHuman();
					}
					else {
						//human is out execute ai rounds
						finishGame();
					}
				};
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();
			}


			//checks if it is the humans turn and call the according execute
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
 					
 					//parse responseText into a json-object
 					var value = JSON.parse(responseText)
 					
 					//update the top cards
					getTopCards();

					//decide which execution path is to be taken (human choice or automatic ai)
					if(value.curHuman){
						//enable button on cards and hide ai cards
						hideAIs();
						enablePlayerChoice();
						
						//disable the next button to enforce the choice of a category
						document.getElementById("nextBtn").disabled = true;
					}
					else {
						//turn over the cards and execute the round
						makeCardsVisible();
						executeRoundAI();
					}
				};
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();
			}


			//execute the round of an ai (automated choice of category)
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
 					//parse responseText into a json-object 					
 					var infos = JSON.parse(responseText)
 
 					//if the human is still in, continue with updating the game information 
 					if(infos.humanplaying){
 						sleep(200).then(() => {
				    		updateInfo(infos);
						})
 						
 					}
 					//if the player is out check if the game is still running
 					else{			
 						//game over? redirect to end-screen 
 						if(infos.gameover){
 							showGameResults(infos);
 						}
 						//game on? loop to finish game (which calls this method until infos.gameover evaluates to true)
 						else{      
 							finishGame();
 						}
 					}

				};

				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();
			}

			//execute the round of an ai (execution based on chosen category)
			function executeRoundHuman(category) {
				
				//disable the player buttons so that he can't choose a category if he loses
				disablePlayerChoice();
				
				//enable the next round button
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
 					//parse responseText into a json-object 
 					var infos = JSON.parse(responseText)
 					
 					//turn over the ai cards to be able to see the cards at the end of the round
					makeCardsVisible();

					//if the game is over continue to end-screen, else update game log
					if(infos.gameover){
						showGameResults(infos);
 					}
 					else{
 						sleep(200).then(() => {
				    		updateInfo(infos);
						})
					}
				};

				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();
			}

			//get the number of cards for 
			function getNumCards() {
				
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/game/getNumCards"); // Request type and URL+parameters

				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
 					var infos = JSON.parse(responseText) //parse responseText into a json-object 
 					//pass the card information to updatePlayers to update the card number of piles 
 					updatePlayers(infos);
				};

				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();
			}

			//called when a button is pressed; extracts the chosen category and passes it to the human round execution
			//num is between 1 and 5 and relates to the categories
			function getCategory(num) {

				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/game/getCategory?cat="+num); // Request type and URL+parameters

				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
 					var category = JSON.parse(responseText); //parse responseText into a json-object 
 					//after deriving the category from the number, executeHuman is called with the category
 					executeRoundHuman(category.key);


				};

				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();
			}
			
			//checks if an ai was eliminated and if so, hide it from the game display
			function eliminatePlayers() {

				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/game/eliminate"); // Request type and URL+parameters

				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
 					var toEliminate = JSON.parse(responseText); //parse responseText into a json-object 
 					//check if elimination occurred
 					if(toEliminate.elimination) {
 						//go through eliminated players
 						for (var kill in toEliminate.eliminatedPlayers){
 							if(toEliminate.eliminatedPlayers[kill] != "human"){
 								//notify player of elimination
 								alert("Player " + toEliminate.eliminatedPlayers[kill] + " has been eliminated");
 							}
 							//hide information about eliminated ai
 							document.getElementById(toEliminate.eliminatedPlayers[kill]).style.visibility = "hidden";
 							document.getElementById(toEliminate.eliminatedPlayers[kill]+"CardInfo").style.visibility = "hidden";
 							document.getElementById(toEliminate.eliminatedPlayers[kill]+"Result").style.visibility = "hidden";
 						}
 					}


				};

				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();
			}


			//turn over the card information of ai's that are still in the game
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
					var players = JSON.parse(responseText) //parse responseText into a json-object
					var arrayLength = players.length; //determine number of players

					//iterate over active players, starting from 1 to ignore the human
					for (var i = 1; i < arrayLength; i++) {
						var tmp = players[i];
						//display the information of the ai
						document.getElementById(tmp+"CardInfo").style.visibility = "visible";

					}

				};

				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();
			}



		</script>

		</div> <!-- end window view-->

			<!--once game has ended modal-->
		<div id="gameEnd" class="container justify-content-center" style = "background-color: whitesmoke">
	    <div class="container">
				<style>
					.btn-primary{
						margin-bottom: 1px;
					}
					.btn-primary:hover{
						background-color: #4d79ff;
					}
				</style>
	      <div class="jumbotron vertical-center">
	        <h2 class="container">GAME OVER</h2>
	        <h4>The game has ended</h4>
	      </div>
	      <div class="row">
	        <!--Game end results-->
	        <table align="left">
	          <!--winner-->
	          <tr>
	            <th>Winner	</th>
	            <td id="gameWinner"></td>
	          </tr>
	          <!--Rounds played-->
	          <tr>
	            <th>Rounds	</th>
	            <td id="gameTotalRounds"></td>
	          </tr>
	          <!--Number of draws-->
	          <tr>
	            <th>Draws	</th>
	            <td id="gameTotalDraws"></td>
	          </tr>
	          </table>
	        </div>
	      </div>
	      <br />
	      <div class="container">
	        <div class="row">
	          <div class="col-md-2">
	            <button id="playAgain" type="button" class="btn btn-secondary" data-dismiss="modal" style="width:100%">Play Again</button>
	            <script type="text/javascript">
	              document.getElementById("playAgain").onclick = function () {
	                location.href = "http://localhost:7777/toptrumps/game";
	              };
	            </script>
	          </div>
	          <div class="col-md-2">
	            <button id="viewStats" type="button" class="btn btn-secondary" data-dismiss="modal" style="width:100%">View Game Statistics</button>
	            <script type="text/javascript">
	              document.getElementById("viewStats").onclick = function () {
	                location.href = "http://localhost:7777/toptrumps/stats";
	              };
	            </script>
	          </div>
	          <div class="col-md-2">
	            <button id ="quitEnd" type="button" class="btn btn-secondary" data-dismiss="modal" style="width:100%">Quit</button>
	            <script type="text/javascript">
	              document.getElementById("quitEnd").onclick = function () {
	                location.href = "http://localhost:7777/toptrumps";
	              };
	            </script>
	          </div>
	        </div>
	      </div>
			</div> <!-- end of game end-->

		</div> <!-- ends the background image-->




	</body>
</html>
