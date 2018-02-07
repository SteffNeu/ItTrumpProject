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
	<body>
		<style>
		body{
		  	background-image: url('https://static.gamespot.com/uploads/original/536/5360430/3141756-screenshot0034.jpg');
		    background-position: center center;
		    background-repeat:  no-repeat;
		    background-attachment: fixed;
		    background-size:  cover;
			}
			h3 {
			    /*text-align: center;*/
			    font-size: 24px;
			    font-weight: bold;
			}
			p {
			    font-size: 16px;
			}
		</style>
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
				<h1>TOP TRUMPS ONLINE</h1>
				<p>Welcome to Top Trumps Online. We are providing for you a fun environment to play a little game with your favorite Star Wars ships.</p>
				<p>Please Select an option: </p>
				<p><a class="btn btn-primary btn-lg" href="http://localhost:7777/toptrumps/game" role="button">Start a new Game</a>
					<a class="btn btn-primary btn-lg" href="http://localhost:7777/toptrumps/stats" role="button">View Game Statistics</a></p>
			</div>
		</div>

		<div class="container">
			<style>
				.container div{
					width: auto;
					padding: 15px;
					margin: 25px;
					background-color: whitesmoke;
				}
			</style>
			<div class="row">
				<div class="col-lg-7">
					<h3><span>	Game Rules	</span></h3>
					<p>
						Select how many players you wish to play against.
						<br />
						On your turn, select a category from your current card. The higher the value the better.
						If your category value is higher than all other players' for that category, collect all cards played this round.
						When a draw occurs, cards from this round remain in a communal pile and the next winner collects them all.
						The player who manages to collect all 40 cards and eliminate all other players, wins.
						<br /><br />
						<strong>Good luck and have fun!</strong>
					</p>
				</div>

				<div class="col-lg-3">

							<img src="https://lumiere-a.akamaihd.net/v1/images/Star-Destroyer_ab6b94bb.jpeg?region=0%2C50%2C1600%2C800" height="200" width="200">
				</div>
								<!--<img src="dcs.gla.ac.uk/~richardm/TopTrumps/Avenger.jpg" height="200" width="200"></div>-->
      </div>
		</div>

	</body>
</html>
