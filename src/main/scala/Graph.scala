/**
 * Created by mmazur01 on 2015-04-20.
 */

import scala.collection.mutable
import scala.collection.mutable.{MutableList, PriorityQueue, Map}

class Graph(val end: Int) {


  private val vertices : Array[Vertex] = new Array[Vertex](end)
  private val tasks = Map[Int ,PriorityQueue[Task]]()

  def getVertices = vertices;

  def +(index: Int, v: Vertex): Unit = {
    vertices(index) = v
  }

  def +(parent : Int, v : Int, length: Int): Unit ={
    val v1 = vertices(parent-1)
    val v2 = vertices(v-1)

    v1 addEdge new Edge(v1, v2, length)
  }


  def getPath(): Unit = {

    if (end == 1) {
      println(0)
      println(end)
      return
    }


    val p = PriorityQueue[Task]()

    p ++= processVertex(vertices.head)

    while (!p.isEmpty) {
      val t = p.dequeue
      p ++= processVertex(t.e.v)
    }

    val theBest = tasks.get(end).get.head //.filter(_.lastKey == end).minBy(_.sum)
    println(theBest.sum)
    theBest.getPath
  }


  private def processVertex(v: Vertex): Seq[Task] = {
    var seq: Seq[Task] = Seq()
    for (e <- v.e) {
      val t = processEdge(e)
      if (t.isDefined) seq = seq :+ t.get
    }
    seq
  }

  private def processEdge(e: Edge): Option[Task] = {
    val task = tasks.get(e.parent.number)
    var t: Task = null
    if (task.isDefined) {
      val tk = task.get.head
      t = new Task(e.v.number, e, tk.sum + e.length, tk, null)
    } else {
      if (tasks.isEmpty) {
        t = new Task(e.parent.number, e, 0, null, null)
        tasks += (e.parent.number -> PriorityQueue(t))
        return processEdge(e)
      }

      t = new Task(e.v.number, e, e.length, null ,null)


    }

    if (tasks.contains(e.v.number)) {
      val q = tasks.get(e.v.number).get
      if(q.exists(tk => tk.parent == t.parent)){
        None
      }else{
        q += t
        Some(t)
      }
      None
    } else {
      tasks += (e.v.number -> PriorityQueue[Task](t))
      Some(t)
    }

  }
}
