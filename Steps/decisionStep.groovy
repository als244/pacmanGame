decisionStep = stepFactory.createStep("decisionStep")

decisionStep.run = {
  println "decisionStep.run"
  
  LinkedHashMap<String, Integer[]> allPlayerGoals = new LinkedHashMap<String, Integer[]>()
 
  g.V.each { player->
    
  	a.add(player,
        [name: "Left", result: {player.x = (player.x - 1) % 20
                                player.move = "Left"
                                Integer[] XYpair = [player.x % 20, player.y % 20]
                                allPlayerGoals.put(player.id, XYpair)
                                player.text = c.get('DecisionText', player.score, player.move)}],
        [name: "Up", result: { player.y = (player.y - 1) % 20
                              	player.move = "Up"
                              	Integer[] XYpair = [player.x % 20, player.y % 20]
                                allPlayerGoals.put(player.id, XYpair)
                                player.text = c.get('DecisionText', player.score, player.move)}],
        [name: "Down", result: { player.y = (player.y + 1) % 20
                                player.move = "Down"
                                Integer[] XYpair = [player.x % 20, player.y % 20]
                                allPlayerGoals.put(player.id, XYpair)
                                player.text = c.get('DecisionText', player.score, player.move)}],
        [name: "Right", result: { player.x = (player.x + 1) % 20
                                  player.move = "Right"
                                Integer[] XYpair = [player.x % 20, player.y % 20]
                                allPlayerGoals.put(player.id, XYpair)
                                player.text = c.get('DecisionText', player.score, player.move)}]
  	)
   	
  }
  
  g.V.each { player ->
    player.seeAllMoves = allPlayerGoals
  }
}

decisionStep.done = {
  println "decisionStep.done"
  moveStep.start()
}