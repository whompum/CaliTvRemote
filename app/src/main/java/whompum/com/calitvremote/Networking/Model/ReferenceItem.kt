package whompum.com.calitvremote.Networking.Model


/**
 * Mutable class representing a Nosql resource
 *
 * This class can take the responsibility of only representing itself,
 * XOR representing children as well. It's also capable of detecting any
 * changes that occurred within it, since this class is mutable.
 *
 */
open class ReferenceItem(val parent: String, reference: String, var value: String = ""): Reference(reference){

    private val children = HashSet<ReferenceItem>()
    var initializeValue = value

    fun hasChildren() = children.size > 0

    /*
     * Returns true if IT, or any of its childrens value has changed.
     */
    fun hasChanged(): Boolean{

        val valueChanged = (initializeValue != value)

        val childrenChanged = (children.find { it.hasChanged() } != null)

        return valueChanged || childrenChanged
    }

    fun findChildBy(ref: String) = children.find { it.reference == ref }

    fun hasChild(ref: String) = findChildBy(ref) != null

    fun getChildren() = children

    fun addChild(child: ReferenceItem){
        if(!children.contains(child))
              children.add(child)
    }

}