decisionStep = stepFactory.createStep("decisionStep")

decisionStep.run = {
  println "decisionStep.run"
  g.V.each { player->
  	a.add(player,
        [name: "Left", result: {player.x = (player.x - 1) % 20
                                player.move = "Left"
                               	textAfterChoice(player)}],
        [name: "Up", result: { player.y = (player.y - 1) % 20
                             	player.move = "Up"
                             	textAfterChoice(player)}],
        [name: "Down", result: { player.y = (player.y + 1) % 20
                                player.move = "Down"
                               textAfterChoice(player)}],
        [name: "Right", result: { player.x = (player.x + 1) % 20
                                 player.move = "Right"
                                textAfterChoice(player)}]
  	)
    
    
  }
  
 
  
  
}

decisionStep.done = {
  println "decisionStep.done"
  moveStep.start()
  
}