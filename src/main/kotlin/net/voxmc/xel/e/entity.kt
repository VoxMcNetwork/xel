package net.voxmc.xel.e

import net.voxmc.xel.c.XelModule
import kotlin.reflect.KClass
import kotlin.reflect.safeCast

interface XelEntity
{
	val uuid: Long
	
	
	fun loadModule(module: XelModule)
	
	fun killModule(module: KClass<out XelModule>): XelModule?
	
	
	fun <C : XelModule> findModule(component: KClass<C>): C?
	
	fun <C : XelModule> needModule(component: KClass<C>): C
	{
		return requireNotNull(findModule(component))
	}
}


inline fun <reified C : XelModule> XelEntity.loadModule(component: C): C
{
	return component.also(this::loadModule)
}

inline fun <reified C : XelModule> XelEntity.loadModule(function: () -> C): C
{
	return function.invoke().also(this::loadModule)
}

inline fun <reified C : XelModule> XelEntity.killModule(): C?
{
	return this.killModule(C::class) as? C?
}

inline fun <reified C : XelModule> XelEntity.findModule(): C?
{
	return this.findModule(C::class)
}


open class XelEntityBase(final override val uuid: Long) : XelEntity
{
	
	private val modules = mutableMapOf<KClass<out XelModule>, XelModule>()
	
	
	override fun loadModule(module: XelModule)
	{
		modules[module::class] = module
	}
	
	override fun killModule(module: KClass<out XelModule>): XelModule?
	{
		return modules.remove(module)
	}
	
	override fun <C : XelModule> findModule(component: KClass<C>): C?
	{
		return component.safeCast(modules[component])
	}
}