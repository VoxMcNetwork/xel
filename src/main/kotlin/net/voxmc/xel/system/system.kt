package net.voxmc.xel.system

import net.voxmc.xel.XelEngine

interface XelSystem
{
	val name: String
	
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