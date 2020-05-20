package net.voxmc.xel.e

import net.voxmc.xel.c.XelModule
import kotlin.reflect.KClass

abstract class Filter : (XelEntity) -> Boolean
{
	abstract override fun invoke(entity: XelEntity): Boolean
}


object Finder
{
	fun all(vararg modules: KClass<out XelModule>): Filter
	{
		return object : Filter()
		{
			override fun invoke(entity: XelEntity): Boolean
			{
				return modules.all { module -> entity.findModule(module) != null }
			}
		}
	}
	
	fun any(vararg modules: KClass<out XelModule>): Filter
	{
		return object : Filter()
		{
			override fun invoke(entity: XelEntity): Boolean
			{
				return modules.any { module -> entity.findModule(module) != null }
			}
		}
	}
}