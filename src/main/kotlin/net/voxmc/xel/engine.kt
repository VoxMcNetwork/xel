package net.voxmc.xel

import net.voxmc.xel.base.Named
import net.voxmc.xel.e.Filter
import net.voxmc.xel.e.XelEntity
import net.voxmc.xel.e.XelEntityBase
import net.voxmc.xel.s.SystemTickException
import net.voxmc.xel.s.XelSystem

open class XelEngine(final override val name: String) : Named
{
	
	@PublishedApi
	internal val entityStore = mutableMapOf<Any, XelEntity>()
	
	@PublishedApi
	internal val systemStore = mutableMapOf<Any, XelSystem>()
	
	
	fun listEntity(): Collection<XelEntity>
	{
		return entityStore.values
	}
	
	
	fun loadEntity(): XelEntity
	{
		val entity = XelEntityBase(entityStore.size.toLong())
		entityStore[entity.uuid] = entity
		
		return entity
	}
	
	fun killEntity(entity: XelEntity)
	{
		entityStore -= entity.uuid
	}
	
	
	fun findEntity(filter: Filter): Collection<XelEntity>
	{
		return entityStore.values.filter(filter)
	}
	
	
	fun listSystem(): Collection<XelSystem>
	{
		return systemStore.values
	}
	
	
	fun loadSystem(system: XelSystem)
	{
		systemStore[system.name] = system
	}
	
	fun killSystem(system: XelSystem)
	{
		systemStore -= system.name
	}
	
	
	fun tickEngine(delta: Float)
	{
		systemStore.values.forEach()
		{ system ->
			try
			{
				system.tickSystem(this, delta)
			}
			catch (ex: SystemTickException)
			{
				// ignore for now!
			}
		}
	}
	
}


inline fun <reified E : XelEntity> XelEngine.loadEntity(function: (Long) -> E): E
{
	val entity = function(entityStore.size.toLong())
	entityStore[entity.uuid] = entity
	
	return entity
}

inline fun <reified S : XelSystem> XelEngine.loadSystem(function: () -> S) : S
{
	val system = function.invoke()
	systemStore[system.name] = system
	
	return system
}