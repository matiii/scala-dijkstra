import scala.collection.mutable

/**
 * Created by mmazur01 on 2015-04-21.
 */
class Task(val lastKey: Int, val e: Edge, val sum: Int, var parent: Task, var child: Task) extends Ordered[Task] {
  override def compare(that: Task): Int = that.sum compare this.sum


  def getPath: Unit = {
    var t = this
    var c: Task = null

    while (t.parent != null){
      c = t
      t = t.parent
      t.child = c
    }

    while (t != null){
      print(t.lastKey)
      print(" ")
      t = t.child
    }
  }
}
