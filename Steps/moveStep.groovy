moveStep = stepFactory.createStep("moveStep")

moveStep.run = {
  
  println "moveStep.run"
  
  g.V.each{player ->
    
    player.collision = false
      
    //remove so as to search for other players moving to same spot
    player.seeAllMoves.remove(player.id)
   
    
    Integer[] XYpair = [player.x, player.y]
	
    
	Iterator iter = player.seeAllMoves.entrySet().iterator();
    while (iter.hasNext()) {
        Map.Entry pair = (Map.Entry)iter.next();
    	temp = pair.getValue();
      	if ((temp[0] == XYpair[0]) && (temp[1] == XYpair[1])){
          	player.text = c.get('CollisionText')
  			player.collision = true
      	}
    }
    player.seeAllMoves.put(player.id, XYpair)
    
    
  }
  
  g.V.each{ player -> 
    // if collision revert x and y back to before decision, if no collision make old spot blank
    switch (player.move){     
    	case "Left":
       		if (player.collision){
            	player.x = (player.x + 1) % 20
            }
        	else{
                board[(player.x + 1) % 20][player.y] = "B"
            }
            break
       	case "Up":
       		if (player.collision){
            	player.y = (player.y + 1) % 20
            }
        	else{
                board[player.x][(player.y + 1) % 20] = "B"
            }
            break
     	case "Down":
          	if (player.collision){
              	player.y = (player.y - 1) % 20
            }
        	else{
                board[(player.x) % 20][(player.y - 1) % 20] = "B"
            }
            break
       	case "Right":
       		if (player.collision){
              	player.x = (player.x - 1) % 20
            }
        	else{
                board[(player.x - 1) % 20][player.y] = "B"
            }
            break
  	}
    
    println "test4"
	// if new spot had resource increment score
    if (player.collision == false){
      if (board[player.x][player.y] == "R"){
          player.score++
          nResources--;
      }
      player.text = c.get('ScoreText', player.score)
    }
  }
  
  g.V.each{player ->
    
    board[player.x][player.y] = player.id
    player.private.map = board
  
  }
  println "test5"
  
}

moveStep.done = {
  if (nResources <= 0){
    g.V.each{player ->
    	player.text = c.get('GameOverText', player.score)
 	}
  }
  else{
   	decisionStep.start()
  }
}