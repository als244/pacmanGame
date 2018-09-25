def afterChoice(player, goalList){
  Integer[] XYpair = [player.x % 20, player.y % 20]
  goalList.put(player.id, XYpair)
  player.text = c.get('DecisionText', player.score, player.move)
}

def afterCollision(player){
  player.text = c.get('CollisionText')
  player.collision = true
  switch (player.move){     
  	case "Left":
      	player.x = (player.x + 1) % 20
        break
   	case "Up":
        player.y = (player.y + 1) % 20
        break
   	case "Down":
        player.y = (player.y - 1) % 20
        break
  	case "Right":
        player.x = (player.x - 1) % 20
       	break
 }
}