/**
 * Created by mmazur01 on 2015-04-20.
 */

object Program extends App {

  val settings = scala.io.StdIn.readLine()

  val vertices: Int = settings.split(" ")(0).toInt
  val edges: Int = settings.split(" ")(1).toInt

  val g = new Graph(vertices)

//Firts part
  (1 to vertices).par.foreach( i => g + (i-1,new Vertex(i))) //add new vertex to array by index

//Second part
  for (i <- 1 to edges) {
    val data = scala.io.StdIn.readLine()
    val d = data.split(" ")
    val v1 = d(0).toInt
    val v2 = d(1).toInt
    val length = d(2).toInt
    g+(v1, v2, length)
}

  g.getPath()

}
