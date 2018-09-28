moveStep = stepFactory.createStep("moveStep")

moveStep.run = {
  
  println "moveStep.run"
  
  ArrayList<String> collisionList = new ArrayList<String>();
  
  println "test1"
  
  g.V.each{player ->
    
    player.collision = false
    
    XYpair = player.seeAllMoves.get(player.id)
   
    //remove so as to search for other players moving to same spot
    player.seeAllMoves.remove(player.id)
   
	
    
	Iterator iter = player.seeAllMoves.entrySet().iterator();
    while (iter.hasNext()) {
        Map.Entry pair = (Map.Entry)iter.next();
    	temp = pair.getValue();
      	if ((temp[0] == XYpair[0]) && (temp[1] == XYpair[1])){
          	println "test2"
          	player.text = c.get('CollisionText')
  			player.collision = true
          	collisionList.add(player.id)
      	}
    }
    println "test3"
    player.seeAllMoves.put(player.id, XYpair)
    
    
  }
  
  g.V.each{ player ->
    if (!player.collision){
		moveGoal = player.seeAllMoves.get(player.id)
      	// if the goal location isn't blank or resource it is another player
      	// if that other player has a collision then there will be another collision
      	if (board[moveGoal[0]][moveGoal[1]] != "B" && board[moveGoal[0]][moveGoal[1]] != "R"){
      		// check if that player.id is in the collision list
       		if (collisionList.contains(board[moveGoal[0]][moveGoal[1]])){
               	player.text = c.get('CollisionText')
		  		player.collision = true;
            	collisionList.add(player.id)
          	}
       	}
    }
    
  }
      	
  g.V.each{ player -> 
    // if no collision change x and y to proper move and make old spot blank
    switch (player.move){     
    	case "Left":
       		if (!player.collision){
              	board[player.x][player.y] = "B"
            	player.x = (player.x - 1) % 20
              	if (player.x < 0){
					player.x += 20
            	}
            }
            break
       	case "Up":
       		if (!player.collision){
               	board[player.x][player.y] = "B"
            	player.y = (player.y - 1) % 20
              	if (player.y < 0){
					player.y += 20
            	}
            }
            break
     	case "Down":
          	if (!player.collision){
              	board[player.x][player.y] = "B"
              	player.y = (player.y + 1) % 20
              	if (player.y < 0){
					player.y += 20
            	}
            }
            break
       	case "Right":
       		if (!player.collision){
               	board[player.x][player.y] = "B"
              	player.x = (player.x + 1) % 20
              	if (player.x < 0){
					player.x += 20
            	}
            }
            break
  	}
    
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
}

moveStep.done = {
  if (nResources <= 0){
  	highScore = 0
    msg = ""
  	g.V.each{player ->
      msg += "Player " + player.id + ": " + player.score + "\n" 
      if (player.score > highScore){
        highScore = player.score
      }
    }
  
    g.V.each{player ->
    	player.text = c.get('GameOverText', player.score, highScore, msg)
 	}
  }
  else{
   	decisionStep.start()
  }
}