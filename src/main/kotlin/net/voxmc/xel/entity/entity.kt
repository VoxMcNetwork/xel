package net.voxmc.xel.entity

import net.voxmc.xel.module.XelModule
import kotlin.reflect.KClass

interface XelEntity
{
	val uuid: Long
	
	
	fun loadModule(module: XelModule)
	
	fun killModule(module: KClass<out XelModule>): XelModule?
	
	
	fun <C : XelModule> findModule(module: KClass<C>): C?
	
	fun <C : XelModule> needModule(module: KClass<C>): C
	{
		return requireNotNull(findModule(module))
	}
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

inline fun <reified C : XelModule> XelEntity.needModule(): C
{
	return this.needModule(C::class)
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
	
	override fun <C : XelModule> findModule(module: KClass<C>): C?
	{
		@Suppress("UNCHECKED_CAST")
		return modules[module] as? C?
	}
}