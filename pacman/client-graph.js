// client-graph.js
function Graph(clientId, parentElement) {
  var container = document.getElementById('graph')
  var canvas = document.createElement('canvas')
  var ctx = canvas.getContext('2d')
  container.appendChild(canvas)
  canvas.width = 645
  canvas.height = 642
  
  var board = []
  var neighbors = []
  
  function loop (timestamp) {
    
    render()
    requestAnimationFrame(loop)
  }
  
  function render () {
    var topLX = 50
    var topLY = 50
    ctx.clearRect(topLX, topLY, canvas.width, canvas.height)
    ctx.fillStyle = 'lightsteelblue'
    ctx.fillRect(topLX, topLY, 500, 500)
    var w = 500
    var h = 500
    var step = 25
    ctx.fillStyle = 'darkblue'
    for (var x=topLX + step ; x<= w + step;x+=step) {
      for (var y = topLY + step; y <= h + step; y+= step){
      	ctx.fillRect(x,y,5,5);
   	  }
    }
    
    var playerIcon = new Image(20, 20);
    var resourceIcon = new Image(15, 15);
    var ghostIcon = new Image(20, 20);
    playerIcon.src = '/images/36';
    resourceIcon.src = '/images/38';
    ghostIcon.src = '/images/39';
    if (!board || !neighbors){
      return
    }
    for (var i = 0; i < board.length; i++) {
      for (var j = 0; j < board[0].length; j++){
        if (board[i][j] === clientId){
			var iconX = 55 + i*step;
        	var iconY = 52 + j*step;
          	ctx.drawImage(playerIcon, iconX, iconY);
          	//ctx.fillText("P", iconX, iconY)
        }
      	else if (board[i][j] === "R"){
          	var resourceX = 58 + i*step;
            var resourceY = 55 + j*step;
          	ctx.drawImage(resourceIcon, resourceX, resourceY);
          	//ctx.fillText("R", resourceX, resourceY);
      	}
       	else if (neighbors.includes(board[i][j])){
			var neighborX = 55 + i*step;
            var neighborY = 52 + j*step;
          	//ctx.fillText("O", neighborX, neighborY);
          	ctx.drawImage(ghostIcon, neighborX, neighborY);
        }
      }
    }
  }
  
  requestAnimationFrame(loop)
  
  this.updateGraph = function (newGraph) {
    console.log('newGraph', newGraph);
    console.log('clientId', clientId);
    console.log('parentElement', parentElement);
    if(!newGraph.nodes){
      return
    }
    
  
    neighbors = new Array(newGraph.nodes.length - 1)
   
    for (var i = 0, n = 0; i < newGraph.nodes.length; i++) {
      if (newGraph.nodes[i].id === clientId) {
        board = newGraph.nodes[i].map
      }
      else{
		neighbors[n] = newGraph.nodes[i].id
        n++
      }
    }
  }

}