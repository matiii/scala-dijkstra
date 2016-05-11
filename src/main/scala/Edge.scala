/**
 * Created by mmazur01 on 2015-04-20.
 */
class Edge(val parent: Vertex, val v: Vertex, val length: Int){
  override def equals(other: Any) = {
    var result = false
    if(other.isInstanceOf[Edge]){
      val e = other.asInstanceOf[Edge]
      result = e.parent.number == parent.number && e.v.number == v.number
    }
    result
  }
}
