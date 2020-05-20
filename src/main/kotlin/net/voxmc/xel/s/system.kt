package net.voxmc.xel.s

import net.voxmc.xel.XelEngine
import net.voxmc.xel.base.Named

interface XelSystem : Named
{
	override val name: String
	
	@Throws(SystemTickException::class)
	fun tickSystem(engine: XelEngine, delta: Float)
}

open class XelSystemBase(final override val name: String) : XelSystem
{
	override fun tickSystem(engine: XelEngine, delta: Float)
	{
		throw SystemTickException("Unimplemented System: $name")
	}
}


data class SystemTickException(override val message: String)
	: RuntimeException(message)