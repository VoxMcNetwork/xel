package net.voxmc.xel.entity

import net.voxmc.xel.module.XelModule
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