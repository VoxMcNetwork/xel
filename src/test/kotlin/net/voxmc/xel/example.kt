package net.voxmc.xel

import net.voxmc.xel.module.XelModuleBase
import net.voxmc.xel.entity.findModule
import net.voxmc.xel.entity.killModule
import net.voxmc.xel.entity.loadModule
import net.voxmc.xel.system.XelSystemBase

fun main()
{
	val engine = Xel.makeEngine("test")
	engine.loadSystem(::Growth)
	
	val entity = engine.loadEntity()
	entity.loadModule(::Height)
	
	repeat(10)
	{
		engine.tickEngine(1.0F)
	}
	
	val height = entity.killModule<Height>() ?: return
	println("Final Height: ${height.value}")
}


class Height : XelModuleBase("height")
{
	var value = 0
	var delta = 1
}

class Growth : XelSystemBase("growth")
{
	override fun tickSystem(engine: XelEngine, delta: Float)
	{
		engine.listEntity().forEach()
		{ entity ->
			
			val height = entity.findModule<Height>() ?: return@forEach
			height.value += (delta * height.delta).toInt()
			
		}
	}
}