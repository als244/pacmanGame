

initStep = stepFactory.createStep("initStep")

initStep.run = {
  
  board = new String[20][20];
  nResources = 0;

	for (int i = 0; i < 20; i++){
  		for (int j = 0; j < 20; j++){
    		if (r.nextDouble() < probResource){
    			board[i][j] = "R"
              	nResources++;
   			 }		
    		else{
				board[i][j] = "B"
    		}
  		}
	}

  println "initStep.run"
  g.random(0.6)
  g.V.each { player->
  	
    
    player.x = r.nextInt(20)
    player.y = r.nextInt(20)
  	player.move = ""
    player.score = 0;
    board[player.x][player.y] = player.id
    player.private.map = board
   
    
  }
  
}


initStep.done = {
  println "initStep.done"
  decisionStep.start()
}