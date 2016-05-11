import scala.collection.mutable.{ListBuffer}

/**
 * Created by mmazur01 on 2015-04-20.
 */
class Vertex(val number: Int) {

  private val edges: ListBuffer[Edge] = ListBuffer()

  def addEdge(e: Edge): Unit ={
    val edgeOption = edges.find(ed => ed.equals(e))
    if(edgeOption.isDefined && edgeOption.get.length > e.length){
      edges -= edgeOption.get
    }
    edges += e
  }

  def e : ListBuffer[Edge] = edges

}
