decisionStep = stepFactory.createStep("decisionStep")

decisionStep.run = {
  println "decisionStep.run"
  
  LinkedHashMap<String, Integer[]> allPlayerGoals = new LinkedHashMap<String, Integer[]>()
 
  g.V.each { player->
    
  	a.add(player,
        [name: "Left", result: {player.move = "Left"
                                Integer[] XYpair = [(player.x - 1) % 20, player.y % 20]
                                allPlayerGoals.put(player.id, XYpair)
                                player.text = c.get('DecisionText', player.score, player.move)}],
        [name: "Up", result: {player.move = "Up"
                              	Integer[] XYpair = [player.x % 20, (player.y - 1) % 20]
                                allPlayerGoals.put(player.id, XYpair)
                                player.text = c.get('DecisionText', player.score, player.move)}],
        [name: "Down", result: {player.move = "Down"
                                Integer[] XYpair = [player.x % 20, (player.y + 1) % 20]
                                allPlayerGoals.put(player.id, XYpair)
                                player.text = c.get('DecisionText', player.score, player.move)}],
        [name: "Right", result: {player.move = "Right"
                                Integer[] XYpair = [(player.x + 1) % 20, player.y % 20]
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