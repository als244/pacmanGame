moveStep = stepFactory.createStep("moveStep")

moveStep.run = {
  println "moveStep.run"
  g.V.each{player -> 
    if (board[player.x][player.y] == "R"){
      player.score++
      nResources--;
    }
    
	player.text = c.get('ScoreText', player.score)
    
    switch (player.move){
    	case "Left":
    		board[(player.x + 1) % 20][player.y] = "B"
    		break
    	case "Up":
    		board[player.x][(player.y + 1) % 20] = "B"
    		break
    	case "Down":
    		board[player.x][(player.y - 1) % 20] = "B"
    		break
    	case "Right":
    		board[(player.x - 1) % 20][player.y] = "B"
    		break
    }
    
    board[player.x][player.y] = player.id.toInteger()
    player.map = board
  }
  
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