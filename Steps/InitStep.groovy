

initStep = stepFactory.createStep("initStep")

initStep.run = {
  
  board = new String[20][20];
  nResources = 0;

	for (int i = 0; i < 20; i++){
  		for (int j = 0; j < 20; j++){
          	// where parameter probResource should go
    		if (r.nextDouble() < probResource){
    			board[i][j] = "R"
              	nResources++;
   			 }		
    		else{
				board[i][j] = "B"
    		}
  		}
	}

  LinkedHashMap<String, Integer> allPlayerXs = new LinkedHashMap<String, Integer>()
  LinkedHashMap<String, Integer> allPlayerYs = new LinkedHashMap<String, Integer>()
  
 
  
  println "initStep.run"
  // where parameter connectivity should go
  g.random(connectivity)
  g.V.each { player->
  	
    newX = r.nextInt(20)
    newY = r.nextInt(20)
    while (allPlayerXs.containsValue(newX) && allPlayerYs.containsValue(newY)){
		newX = r.nextInt(20)
      	newY = r.nextInt(20)
    }
    
    allPlayerXs.put(player.id, newX)
    allPlayerYs.put(player.id, newY)

    player.x = newX
    player.y = newY
  	player.move = ""
    player.score = 0;
    if (board[player.x][player.y] == "R"){
      nResources--;
    }
    board[player.x][player.y] = player.id
    player.private.map = board
   
    
  }
  
}


initStep.done = {
  println "initStep.done"
  decisionStep.start()
}